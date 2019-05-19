package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class UQuestionRelease implements Serializable {

    private Long qrid;

    private Long qbid;

    private String key;

    private String state;

    private Date failureTime;

    private Long ccid;

    private String foldername;

    private Long eid;

    private Long cid;

    private static final long serialVersionUID = 1L;

    public Long getQrid() {
        return qrid;
    }

    public void setQrid(Long qrid) {
        this.qrid = qrid;
    }

    public Long getQbid() {
        return qbid;
    }

    public void setQbid(Long qbid) {
        this.qbid = qbid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(Date failureTime) {
        this.failureTime = failureTime;
    }

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }
}