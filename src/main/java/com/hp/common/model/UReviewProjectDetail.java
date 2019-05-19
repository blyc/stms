package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class UReviewProjectDetail implements Serializable {
    private Long rpdid;

    private Long cid;

    private Long ccid;

    private Long sid;

    private BigDecimal reviewGrade;

    private Boolean reviewOk;

    private String remake;

    private Long spid;

    private Long rpid;

    private UStudent uStudent;

    private UStudentProject uStudentProject;

    private static final long serialVersionUID = 1L;

    public Long getRpdid() {
        return rpdid;
    }

    public void setRpdid(Long rpdid) {
        this.rpdid = rpdid;
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

    public BigDecimal getReviewGrade() {
        return reviewGrade;
    }

    public void setReviewGrade(BigDecimal reviewGrade) {
        this.reviewGrade = reviewGrade;
    }

    public Boolean getReviewOk() {
        return reviewOk;
    }

    public void setReviewOk(Boolean reviewOk) {
        this.reviewOk = reviewOk;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public Long getSpid() {
        return spid;
    }

    public void setSpid(Long spid) {
        this.spid = spid;
    }

    public Long getRpid() {
        return rpid;
    }

    public void setRpid(Long rpid) {
        this.rpid = rpid;
    }

    public UStudent getuStudent() {
        return uStudent;
    }

    public void setuStudent(UStudent uStudent) {
        this.uStudent = uStudent;
    }

    public UStudentProject getuStudentProject() {
        return uStudentProject;
    }

    public void setuStudentProject(UStudentProject uStudentProject) {
        this.uStudentProject = uStudentProject;
    }
}