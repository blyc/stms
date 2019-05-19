package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UClassAttendance implements Serializable {
    private Long caid;

    private Long ccid;

    private String recordTime;

    private String section;

    private Long scounts;

    private Long normalCount;

    private Long lateCount;

    private Long leaveCount;

    private Long truantCount;

    private Long earlyCount;

    private BigDecimal rate;

    private Date createTime;

    private Long createId;

    private Date updateTime;

    private Long updateId;

    private Long arid;

    private VClass vClass;

    private static final long serialVersionUID = 1L;

    public Long getCaid() {
        return caid;
    }

    public void setCaid(Long caid) {
        this.caid = caid;
    }

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Long getScounts() {
        return scounts;
    }

    public void setScounts(Long scounts) {
        this.scounts = scounts;
    }

    public Long getNormalCount() {
        return normalCount;
    }

    public void setNormalCount(Long normalCount) {
        this.normalCount = normalCount;
    }

    public Long getLateCount() {
        return lateCount;
    }

    public void setLateCount(Long lateCount) {
        this.lateCount = lateCount;
    }

    public Long getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(Long leaveCount) {
        this.leaveCount = leaveCount;
    }

    public Long getTruantCount() {
        return truantCount;
    }

    public void setTruantCount(Long truantCount) {
        this.truantCount = truantCount;
    }

    public Long getEarlyCount() {
        return earlyCount;
    }

    public void setEarlyCount(Long earlyCount) {
        this.earlyCount = earlyCount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Long getArid() {
        return arid;
    }

    public void setArid(Long arid) {
        this.arid = arid;
    }

    public VClass getvClass() {
        return vClass;
    }

    public void setvClass(VClass vClass) {
        this.vClass = vClass;
    }
}