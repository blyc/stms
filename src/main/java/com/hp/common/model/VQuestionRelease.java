package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class VQuestionRelease implements Serializable {

    private Long qbid;
    private String imgname;
    private Long mid;
    private String name;
    private Long qrid;
    private Long ccid;
    private String ccname;
    private Long cid;
    private String cname;
    private Long eid;
    private String ename;
    private Date failureTime;
    private String foldername;
    private String key;
    private String state;
    private Long sumbitted;

    private static final long serialVersionUID = 1L;

    public Long getQbid() {
        return qbid;
    }

    public void setQbid(Long qbid) {
        this.qbid = qbid;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQrid() {
        return qrid;
    }

    public void setQrid(Long qrid) {
        this.qrid = qrid;
    }

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public String getCcname() {
        return ccname;
    }

    public void setCcname(String ccname) {
        this.ccname = ccname;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Date getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(Date failureTime) {
        this.failureTime = failureTime;
    }

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
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

    public Long getSumbitted() {
        return sumbitted;
    }

    public void setSumbitted(Long sumbitted) {
        this.sumbitted = sumbitted;
    }
}