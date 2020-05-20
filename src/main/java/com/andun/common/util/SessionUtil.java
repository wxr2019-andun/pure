package com.andun.common.util;

import com.andun.platform.pojo.vo.UserInfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Author:wuxinrui
 * Date:2020-03-05  18:15
 * Description:
 */

public class SessionUtil {
    /**
     * 用户信息key
     */
    public static final String USER_SESSION_KEY = "USER_SESSION_KEY";//用户会话密钥


    //    使用shiro 获取session 返回用户信息vo
    public static UserInfoVo getSession() {
        Session session = SecurityUtils.getSubject().getSession();
        return (UserInfoVo) session.getAttribute(USER_SESSION_KEY);
    }
    //    使用shiro 获取session 设置用户会话密钥
    public static void setSession(UserInfoVo userVo) {
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(USER_SESSION_KEY, userVo);
    }
}
