package com.andun.platform.pojo.po;

import java.io.Serializable;

/**
 * Description:
 * GET-version:
 * Date:2020-03-30  16:25
 * Author:wuxinrui
 */

public class TestPO implements Serializable {

    private static final long serialVersionUID = 6898446487368857556L;
    private String name;
    private String age;

    @Override
    public String
    toString() {
        return "TestPO{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
