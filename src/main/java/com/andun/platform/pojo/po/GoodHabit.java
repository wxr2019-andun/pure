package com.andun.platform.pojo.po;

import java.io.Serializable;

/**
 * Author:wuxinrui
 * Date:2020-02-21  22:40
 * Description:
 */

public class GoodHabit implements Serializable {
    private static final long serialVersionUID = -3550092597071178033L;

    private String drinking;
    private String eat_breakfast;
    private String eat_fruits;
    private String eat_vegetables;
    private String exercise;

    @Override
	public String toString() {
		return "GoodHabit [drinking=" + drinking + ", eat_breakfast=" + eat_breakfast + ", eat_fruits=" + eat_fruits
				+ ", eat_vegetables=" + eat_vegetables + ", exercise=" + exercise + "]";
	}

    public String getDrinking() {
        return drinking;
    }

    public void setDrinking(String drinking) {
        this.drinking = drinking;
    }

    public String getEat_breakfast() {
        return eat_breakfast;
    }

    public void setEat_breakfast(String eat_breakfast) {
        this.eat_breakfast = eat_breakfast;
    }

    public String getEat_fruits() {
        return eat_fruits;
    }

    public void setEat_fruits(String eat_fruits) {
        this.eat_fruits = eat_fruits;
    }

    public String getEat_vegetables() {
        return eat_vegetables;
    }

    public void setEat_vegetables(String eat_vegetables) {
        this.eat_vegetables = eat_vegetables;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
}