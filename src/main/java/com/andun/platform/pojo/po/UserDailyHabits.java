package com.andun.platform.pojo.po;

import java.io.Serializable;
import java.util.Date;

public class UserDailyHabits implements Serializable {
    private String id;

    private String wearUserId;

     /**
     * 早睡早起
     */
    private Integer keepEarlyHours;

    /**
     * 心情
     */
    private Integer mood;//1郁闷 2一般 3愉快

    /**
     * 好习惯
     */
    private String goodHabit;

    /**
     * 坏习惯
     */

    private String badHabit;

    /**
     * 业务时间=一个人某月某天 常量
     */
    private Date dailyHabitsTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWearUserId() {
        return wearUserId;
    }

    public void setWearUserId(String wearUserId) {
        this.wearUserId = wearUserId == null ? null : wearUserId.trim();
    }

    public Integer getKeepEarlyHours() {
        return keepEarlyHours;
    }

    public void setKeepEarlyHours(Integer keepEarlyHours) {
        this.keepEarlyHours = keepEarlyHours;
    }

    public Integer getMood() {
        return mood;
    }

    public void setMood(Integer mood) {
        this.mood = mood;
    }

    public String getGoodHabit() {
        return goodHabit;
    }

    public void setGoodHabit(String goodHabit) {
        this.goodHabit = goodHabit == null ? null : goodHabit.trim();
    }

    public String getBadHabit() {
        return badHabit;
    }

    public void setBadHabit(String badHabit) {
        this.badHabit = badHabit == null ? null : badHabit.trim();
    }

    public Date getDailyHabitsTime() {
        return dailyHabitsTime;
    }

    public void setDailyHabitsTime(Date dailyHabitsTime) {
        this.dailyHabitsTime = dailyHabitsTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "UserDailyHabits [id=" + id + ", wearUserId=" + wearUserId + ", keepEarlyHours=" + keepEarlyHours
				+ ", mood=" + mood + ", goodHabit=" + goodHabit + ", badHabit=" + badHabit + ", dailyHabitsTime="
				+ dailyHabitsTime + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
}