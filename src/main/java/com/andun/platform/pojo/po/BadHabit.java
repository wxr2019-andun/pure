package com.andun.platform.pojo.po;

import java.io.Serializable;

/**
 * Author:wuxinrui
 * Date:2020-02-21  22:31
 * Description:
 */

public class BadHabit implements Serializable {
    private static final long serialVersionUID = -4572403957505890972L;
    private String smoke;
    private String tipsiness;

    @Override
	public String toString() {
		return "BadHabit [smoke=" + smoke + ", tipsiness=" + tipsiness + "]";
	}

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    public String getTipsiness() {
        return tipsiness;
    }

    public void setTipsiness(String tipsiness) {
        this.tipsiness = tipsiness;
    }
}