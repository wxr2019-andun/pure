package com.andun.platform.pojo.vo;

import java.io.Serializable;

/**
 * Description:
 * GET-version:
 * Date:2020-04-26  17:13
 * Author:wuxinrui
 */

public class MQTestVo implements Serializable {
    private static final long serialVersionUID = -1348379631871274530L;
    private String sceneName;
    private String sceneTime;

    public MQTestVo() {
    }

    public MQTestVo(String sceneName, String sceneTime) {
        this.sceneName = sceneName;
        this.sceneTime = sceneTime;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneTime() {
        return sceneTime;
    }

    public void setSceneTime(String sceneTime) {
        this.sceneTime = sceneTime;
    }
}
