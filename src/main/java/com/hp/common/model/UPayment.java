package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UPayment implements Serializable {
    private Long upid;

    private Long cid;

    private Long ccid;

    private Long num;

    private String stage;

    private BigDecimal payval;

    private Long paynum;

    private Date createTime;

    private VClass vClass;

    private static final long serialVersionUID = 1L;

    public Long getUpid() {
        return upid;
    }

    public void setUpid(Long upid) {
        this.upid = upid;
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

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public BigDecimal getPayval() {
        return payval;
    }

    public void setPayval(BigDecimal payval) {
        this.payval = payval;
    }

    public Long getPaynum() {
        return paynum;
    }

    public void setPaynum(Long paynum) {
        this.paynum = paynum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public VClass getvClass() {
        return vClass;
    }

    public void setvClass(VClass vClass) {
        this.vClass = vClass;
    }
}