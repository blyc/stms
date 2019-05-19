package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class UReviewHead implements Serializable {
    private Long urhid;

    private Long cid;

    private Long eid;

    private Date reviewTime;

    private Long qualified;

    private static final long serialVersionUID = 1L;

    public Long getUrhid() {
        return urhid;
    }

    public void setUrhid(Long urhid) {
        this.urhid = urhid;
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

    public Long getQualified() {
        return qualified;
    }

    public void setQualified(Long qualified) {
        this.qualified = qualified;
    }
}