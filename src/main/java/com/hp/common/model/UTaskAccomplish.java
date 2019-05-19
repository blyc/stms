package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class UTaskAccomplish implements Serializable {
    private Long utid;

    private Long cid;

    private Long ccid;

    private Long sid;

    private Long taskgrade;

    private Date arrangementtime;

    private Long tid;

    private UStudent uStudent;
    private UClass uClass;
    private UTask uTask;
    private UCompany uCompany;


    private static final long serialVersionUID = 1L;

    public Long getUtid() {
        return utid;
    }

    public void setUtid(Long utid) {
        this.utid = utid;
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

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getTaskgrade() {
        return taskgrade;
    }

    public void setTaskgrade(Long taskgrade) {
        this.taskgrade = taskgrade;
    }

    public Date getArrangementtime() {
        return arrangementtime;
    }

    public void setArrangementtime(Date arrangementtime) {
        this.arrangementtime = arrangementtime;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public UStudent getuStudent() {
        return uStudent;
    }

    public void setuStudent(UStudent uStudent) {
        this.uStudent = uStudent;
    }

    public UClass getuClass() {
        return uClass;
    }

    public void setuClass(UClass uClass) {
        this.uClass = uClass;
    }

    public UTask getuTask() {
        return uTask;
    }

    public void setuTask(UTask uTask) {
        this.uTask = uTask;
    }

    public UCompany getuCompany() {
        return uCompany;
    }

    public void setuCompany(UCompany uCompany) {
        this.uCompany = uCompany;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}