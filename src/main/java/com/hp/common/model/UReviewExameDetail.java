package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class UReviewExameDetail implements Serializable {
    private Long redid;

    private Long cid;

    private Long ccid;

    private Long sid;

    private BigDecimal reviewGrade;

    private Boolean reviewOk;

    private String remake;

    private Long useid;

    private Long reid;

    private UStudent uStudent;

    private UStudentExam uStudentExam;

    private static final long serialVersionUID = 1L;

    public Long getRedid() {
        return redid;
    }

    public void setRedid(Long redid) {
        this.redid = redid;
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

    public Long getUseid() {
        return useid;
    }

    public void setUseid(Long useid) {
        this.useid = useid;
    }

    public Long getReid() {
        return reid;
    }

    public void setReid(Long reid) {
        this.reid = reid;
    }

    public UStudent getuStudent() {
        return uStudent;
    }

    public void setuStudent(UStudent uStudent) {
        this.uStudent = uStudent;
    }

    public UStudentExam getuStudentExam() {
        return uStudentExam;
    }

    public void setuStudentExam(UStudentExam uStudentExam) {
        this.uStudentExam = uStudentExam;
    }
}