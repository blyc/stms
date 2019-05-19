package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UReviewProject implements Serializable {
    private Long rpid;

    private Long cid;

    private Long ccid;

    private Date reviewTime;

    private Long qualifiedNum;

    private Long noqualifiedNum;

    private Long reviewNum;

    private BigDecimal reviewRate;

    private BigDecimal qualified;

    private Long cpid;

    private static final long serialVersionUID = 1L;

    public Long getRpid() {
        return rpid;
    }

    public void setRpid(Long rpid) {
        this.rpid = rpid;
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

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Long getQualifiedNum() {
        return qualifiedNum;
    }

    public void setQualifiedNum(Long qualifiedNum) {
        this.qualifiedNum = qualifiedNum;
    }

    public Long getNoqualifiedNum() {
        return noqualifiedNum;
    }

    public void setNoqualifiedNum(Long noqualifiedNum) {
        this.noqualifiedNum = noqualifiedNum;
    }

    public Long getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(Long reviewNum) {
        this.reviewNum = reviewNum;
    }

    public BigDecimal getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(BigDecimal reviewRate) {
        this.reviewRate = reviewRate;
    }

    public BigDecimal getQualified() {
        return qualified;
    }

    public void setQualified(BigDecimal qualified) {
        this.qualified = qualified;
    }

    public Long getCpid() {
        return cpid;
    }

    public void setCpid(Long cpid) {
        this.cpid = cpid;
    }
}