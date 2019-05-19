package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class USatisfaction implements Serializable {
    private Long usid;

    private Long ccid;

    private Long cid;

    private Long eid;

    private Date surveyTime;

    private BigDecimal score;

    private String remark;

    private Long createId;

    private Date createTime;

    private Long deleteFlg;

    private VClass vClass;

    private UEmployee uEmployee;

    private static final long serialVersionUID = 1L;

    public Long getUsid() {
        return usid;
    }

    public void setUsid(Long usid) {
        this.usid = usid;
    }

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Date getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(Date surveyTime) {
        this.surveyTime = surveyTime;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(Long deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public VClass getvClass() {
        return vClass;
    }

    public void setvClass(VClass vClass) {
        this.vClass = vClass;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public UEmployee getuEmployee() {
        return uEmployee;
    }

    public void setuEmployee(UEmployee uEmployee) {
        this.uEmployee = uEmployee;
    }
}