package com.andun.platform.config.shiro;

import com.andun.common.util.SessionUtil;
import com.andun.platform.config.service.TokenService;
import com.andun.platform.dao.DaoTest;
import com.andun.platform.pojo.vo.UserInfoVo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Author:wuxinrui
 * Date:2020-03-05  18:03
 * Description:
 */

public class AuthRealm  extends AuthorizingRealm {

    @Autowired
    private DaoTest managerService;


    @Autowired
    private TokenService tokenManager;


    public TokenService getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(TokenService tokenManager) {
        this.tokenManager = tokenManager;
    }


    //获取认证信息    顺序-1
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token中 某数据
        String accountName = (String) token.getPrincipal();

        // 没太懂这里的Optional.
        UserInfoVo userInfoVo =  Optional.ofNullable(managerService.selectManagerByAccount(accountName)).orElseThrow(UnknownAccountException::new);
        //if (!userInfoVo.getLocked()) {  //这里是医生的状态 用布尔值显示
        //    throw new LockedAccountException();
        //}

        //通过shiro session方法 将数据放到session种
        SessionUtil.setSession(userInfoVo);

        //简单身份验证信息
        return new SimpleAuthenticationInfo(accountName, userInfoVo.getPassword(), getName());

    }

    //获取授权信息 顺序-2
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取 session中 用户数据
        UserInfoVo userInfoVo = SessionUtil.getSession();
        //简单的授权信息 类
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //待定
        //info.setRoles(Sets.newHashSet(user.getRoleName()));
        return info;
    }
}
