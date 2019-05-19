package com.hp.common.model;

import java.io.Serializable;


public class VClass implements Serializable {
    private Long cid;

    private Long ccid;

    private String classname;

    private Long mid;

    private Long num;

    private String grade;

    private String companyname;

    private String majorname;

    private Long jseid;

    private String jsname;

    private Long dseid;

    private String dsname;

    private Long bzreid;

    private String bzrname;

    private String stage;

    private static final long serialVersionUID = 1L;

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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
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

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname;
    }

    public Long getJseid() {
        return jseid;
    }

    public void setJseid(Long jseid) {
        this.jseid = jseid;
    }

    public String getJsname() {
        return jsname;
    }

    public void setJsname(String jsname) {
        this.jsname = jsname;
    }

    public Long getDseid() {
        return dseid;
    }

    public void setDseid(Long dseid) {
        this.dseid = dseid;
    }

    public String getDsname() {
        return dsname;
    }

    public void setDsname(String dsname) {
        this.dsname = dsname;
    }

    public Long getBzreid() {
        return bzreid;
    }

    public void setBzreid(Long bzreid) {
        this.bzreid = bzreid;
    }

    public String getBzrname() {
        return bzrname;
    }

    public void setBzrname(String bzrname) {
        this.bzrname = bzrname;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return  classname ;
    }
}