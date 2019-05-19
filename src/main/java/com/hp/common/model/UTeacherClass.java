package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class UTeacherClass implements Serializable {
    private Long eid;

    private Long cid;

    private Date startTime;

    private Date endTime;

    private String position;

    private static final long serialVersionUID = 1L;

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}