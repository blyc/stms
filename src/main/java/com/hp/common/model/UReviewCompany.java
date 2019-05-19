package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UReviewCompany implements Serializable {
    private Long urcid;

    private Long cid;

    private Long eid;

    private Date reviewTime;

    private BigDecimal qualified;

    private Long score1;

    private Long score2;

    private Long score3;

    private Long headflg;

    private static final long serialVersionUID = 1L;

    public Long getUrcid() {
        return urcid;
    }

    public void setUrcid(Long urcid) {
        this.urcid = urcid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public BigDecimal getQualified() {
        return qualified;
    }

    public void setQualified(BigDecimal qualified) {
        this.qualified = qualified;
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

    public Long getHeadflg() {
        return headflg;
    }

    public void setHeadflg(Long headflg) {
        this.headflg = headflg;
    }
}