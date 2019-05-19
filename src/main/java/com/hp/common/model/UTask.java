package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class UTask implements Serializable {
    private Long tid;

    private Long cid;

    private Long ccid;

    private Long tasknum;

    private Long uid;

    private Long mark;

    private Date arrangementtime;

    private String tasktype;

    private String taskproject;

    private String homework;

    private VClass vClass;

    private static final long serialVersionUID = 1L;

    public VClass getvClass() {
        return vClass;
    }

    public void setvClass(VClass vClass) {
        this.vClass = vClass;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public Long getTasknum() {
        return tasknum;
    }

    public void setTasknum(Long tasknum) {
        this.tasknum = tasknum;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getMark() {
        return mark;
    }

    public void setMark(Long mark) {
        this.mark = mark;
    }

    public Date getArrangementtime() {
        return arrangementtime;
    }

    public void setArrangementtime(Date arrangementtime) {
        this.arrangementtime = arrangementtime;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public String getTaskproject() {
        return taskproject;
    }

    public void setTaskproject(String taskproject) {
        this.taskproject = taskproject;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }
    
}