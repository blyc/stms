package com.hp.common.model;

import java.io.Serializable;

public class UClass implements Serializable {
    private Long ccid;

    private Long mid;

    private Long cid;

    private String id;

    private String name;

    private String stage;

    private String state;

    private Long num;

    private String grade;

    private UCompany uCompany;
    private UMajor uMajor;

    private static final long serialVersionUID = 1L;

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public UCompany getuCompany() {
        return uCompany;
    }

    public void setuCompany(UCompany uCompany) {
        this.uCompany = uCompany;
    }

    public UMajor getuMajor() {
        return uMajor;
    }

    public void setuMajor(UMajor uMajor) {
        this.uMajor = uMajor;
    }
}