package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class VReviewCompany implements Serializable {
    private String summaryTime;

    private Long eid;

    private Long score1;

    private Long score2;

    private Long score3;

    private BigDecimal reviewCompany;

    private Long headflg;

    private UEmployee uEmployee;

    private UCompany uCompany;

    private static final long serialVersionUID = 1L;

    public String getSummaryTime() {
        return summaryTime;
    }

    public void setSummaryTime(String summaryTime) {
        this.summaryTime = summaryTime;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getScore1() {
        return score1;
    }

    public void setScore1(Long score1) {
        this.score1 = score1;
    }

    public Long getScore2() {
        return score2;
    }

    public void setScore2(Long score2) {
        this.score2 = score2;
    }

    public Long getScore3() {
        return score3;
    }

    public void setScore3(Long score3) {
        this.score3 = score3;
    }

    public BigDecimal getReviewCompany() {
        return reviewCompany;
    }

    public void setReviewCompany(BigDecimal reviewCompany) {
        this.reviewCompany = reviewCompany;
    }

    public Long getHeadflg() {
        return headflg;
    }

    public void setHeadflg(Long headflg) {
        this.headflg = headflg;
    }

    public UEmployee getuEmployee() {
        return uEmployee;
    }

    public void setuEmployee(UEmployee uEmployee) {
        this.uEmployee = uEmployee;
    }

    public UCompany getuCompany() {
        return uCompany;
    }

    public void setuCompany(UCompany uCompany) {
        this.uCompany = uCompany;
    }
}