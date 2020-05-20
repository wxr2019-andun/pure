package com.andun.platform.pojo.po;

import java.io.Serializable;

/**
 * Description:
 * GET-version:
 * Date:2020-04-08  14:46
 * Author:wuxinrui
 */

public class UT implements Serializable {
    private static final long serialVersionUID = 1533705682568398666L;
    private String name;
    private String dates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "UT{" +
                "name='" + name + '\'' +
                ", dates='" + dates + '\'' +
                '}';
    }
}
