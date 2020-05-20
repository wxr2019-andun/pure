package com.andun.platform.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.andun.common.ApiResult;
import com.andun.common.util.RequestUtil;
import com.andun.platform.config.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
  *@Author:wuxinrui
  *@Description: 访问过滤
  *@Data 15:25 2020/3/6
*/  
public class StatelessAuthcFilter extends AccessControlFilter {

	private final Logger logger = LoggerFactory.getLogger(StatelessAuthcFilter.class);

	@Autowired
	private TokenService tokenManager;

    public TokenService getTokenManager() {
        return tokenManager;
    }
    public void setTokenManager(TokenService tokenManager) {
        this.tokenManager = tokenManager;
    }


    //访问允许
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //获取request请求   并获取request中 参数为account token的数据
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        	String account = RequestUtil.getParam(httpRequest, "account", "");
        	String token = RequestUtil.getParam(httpRequest, "token", "");
        //日志记录request 中请求url 和account
        logger.info("拦截到的url:" + httpRequest.getRequestURL().toString());
        logger.info("拦截到的用户：" + account);

        //request token空判断
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(token)) {
            loginFail(response,"请求头不包含认证信息TOKEN");
            		return false;
            	}
        //获取request 中token数据 赋值到StatelessToken中
        StatelessToken statelessToken = new StatelessToken(account,token);
        boolean tokens = tokenManager.checkToken(statelessToken);
        //request token 验证是否相等
        if(!tokens){
            logger.error(account+"---"+token+":认证失败-数据不符" );
            loginFail(response,"登录失败");
            return false;
        }
        return true;

    }

    //访问被拒绝
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }
    
    //登录时失败处理函数
    public void loginFail(ServletResponse response,String ErrorMsg){
        ApiResult<?> result = ApiResult.getFailure(101, ErrorMsg,null);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //这里用writer？
        PrintWriter out = null;
        JSONObject parseObject = JSONObject.parseObject(JSONObject.toJSONString(result, SerializerFeature.UseISO8601DateFormat));
        parseObject.remove("data");
        try {
            out = response.getWriter();
            logger.debug(result.toString());
            out.append(parseObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }



}
