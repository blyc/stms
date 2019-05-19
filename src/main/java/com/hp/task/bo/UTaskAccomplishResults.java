package com.hp.task.bo;

import java.io.Serializable;

/**
 * Created by Dragon on 2018/4/20 0020.
 */
public class UTaskAccomplishResults implements Serializable {
    private String name;
    private Long taskgrade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTaskgrade() {
        return taskgrade;
    }

    public void setTaskgrade(Long taskgrade) {
        this.taskgrade = taskgrade;
    }
}
