package com.hp.task.bo;

import java.io.Serializable;

/**
 * Created by Dragon on 2018/4/19 0019.
 */
public class UTaskAccomplishbo implements Serializable {
     private Long utid;
     private long taskgrade;

    public Long getUtid() {
        return utid;
    }

    public void setUtid(Long utid) {
        this.utid = utid;
    }

    public long getTaskgrade() {
        return taskgrade;
    }

    public void setTaskgrade(long taskgrade) {
        this.taskgrade = taskgrade;
    }
}
