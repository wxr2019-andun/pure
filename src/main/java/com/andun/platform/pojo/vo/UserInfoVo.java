package com.andun.platform.pojo.vo;

import com.andun.platform.config.shiro.StatelessToken;

import java.io.Serializable;

/**
 * Author:wuxinrui
 * Date:2020-03-05  18:09
 * Description:
 */

public class UserInfoVo extends DoctorInfo implements Serializable {
    private static final long serialVersionUID = -8399319405949859492L;
    private StatelessToken statelessToken;
    private String fdfUrl;


    public StatelessToken getStatelessToken() {
        return statelessToken;
    }

    public void setStatelessToken(StatelessToken statelessToken) {
        this.statelessToken = statelessToken;
    }

    public String getFdfUrl() {
        return fdfUrl;
    }

    public void setFdfUrl(String fdfUrl) {
        this.fdfUrl = fdfUrl;
    }
}
