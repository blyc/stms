package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class UClassHour implements Serializable {
    private Long uchid;

    private Long ccid;

    private Long cid;

    private Long eid;

    private Date surveyTime;

    private Long count;

    private Long dayCount;

    private Long createId;

    private Date createTime;

    private Long updateFlg;

    private Long deleteFlg;

    private UEmployee uEmployee;

    private UCompany uCompany;

    private static final long serialVersionUID = 1L;

    public Long getUchid() {
        return uchid;
    }

    public void setUchid(Long uchid) {
        this.uchid = uchid;
    }

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
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

    public Date getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(Date surveyTime) {
        this.surveyTime = surveyTime;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getDayCount() {
        return dayCount;
    }

    public void setDayCount(Long dayCount) {
        this.dayCount = dayCount;
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

    public Long getUpdateFlg() {
        return updateFlg;
    }

    public void setUpdateFlg(Long updateFlg) {
        this.updateFlg = updateFlg;
    }

    public Long getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(Long deleteFlg) {
        this.deleteFlg = deleteFlg;
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