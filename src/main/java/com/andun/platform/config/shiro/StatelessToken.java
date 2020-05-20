package com.andun.platform.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Author:wuxinrui
 * Date:2020-03-05  17:08
 * Description:
 */

public class StatelessToken implements AuthenticationToken {
    private static final long serialVersionUID = 1L;
    private String account;
    private String token;

    public StatelessToken(String account, String token){
        this.account = account;
        this.token = token;
    }


    @Override
    public Object getPrincipal() {
        return account;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
    public String getAccount() {
        return account;
    }
    public String getToken() {return token;}
}
