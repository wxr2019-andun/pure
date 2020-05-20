package com.andun.controller;

import com.andun.common.ApiResult;
import com.andun.common.util.TokenUtil;
import com.andun.platform.config.service.TokenService;
import com.andun.platform.config.shiro.StatelessToken;
import com.andun.platform.dao.DaoTest;
import com.andun.platform.pojo.po.DoctorDetailedInfo;
import com.andun.platform.pojo.vo.UserInfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.util.List;

/**
 * Author:wuxinrui
 * Date:2019-09-26  16:31
 * Description:
 */
@Controller
@RequestMapping("/one")
public class ControllerOne {
    @Resource
    DaoTest daoTest;

    @Resource
    private TokenService tokenManager;

    private static final Logger logger = LoggerFactory.getLogger(ControllerOne.class);




    @GetMapping("/logins")
    @ResponseBody
    public ApiResult<?> logins(UserInfoVo userInfoVo) {
        //创建 shiro安全工具
        Subject subject = SecurityUtils.getSubject();
        // 创建token
        UsernamePasswordToken token = new UsernamePasswordToken(userInfoVo.getAccount(),
                TokenUtil.encrypt(userInfoVo.getPassword()));

        //shiro登录-审核账号密码   -返回对应状态码
        try {
            subject.login(token);
        } catch (
                UnknownAccountException e) {
            logger.error("对用户[{}]进行登录验证,验证未通过,用户不存在", userInfoVo.getAccount());
            token.clear();
            return ApiResult.getFailure(401, "验证未通过,用户不存在");
        } catch (
                LockedAccountException lae) {
            logger.error("对用户[{}]进行登录验证,验证未通过,账号已冻结", userInfoVo.getAccount());
            token.clear();
            return ApiResult.getFailure(403, "账号已冻结，请联系客服4008010400");
        } catch (
                ExcessiveAttemptsException e) {
            logger.error("对用户[{}]进行登录验证,验证未通过,错误次数过多", userInfoVo.getAccount());
            token.clear();
            return ApiResult.getFailure(403, "密码不正确");
        } catch (
                AuthenticationException e) {
            logger.error("对用户[{}]进行登录验证,验证未通过,堆栈轨迹如下", userInfoVo.getAccount(), e);
            token.clear();
            return ApiResult.getFailure(403, "密码不正确");
        }

        //redis创建凭证
        StatelessToken statelessToken = this.tokenManager.createToken(userInfoVo.getAccount());
        userInfoVo.setStatelessToken(statelessToken);

        return ApiResult.getSuccess(200, "登录成功", userInfoVo);
    }

    @GetMapping("/logOut")
    @ResponseBody
    public ApiResult<?> logOut(UserInfoVo userInfoVo){
        //获取 AuthenticationToken 对象
        StatelessToken token = this.tokenManager.getToken(userInfoVo.getAccount());
        //不为空 删除redis中token对象
        if (token != null) {
            tokenManager.removeToken(userInfoVo.getAccount());
        }

        SecurityUtils.getSubject().logout();
        logger.info("用户登出");
        return ApiResult.getSuccess(200, "退出成功");
    }


}
