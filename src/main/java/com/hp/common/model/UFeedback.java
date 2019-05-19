package com.hp.common.model;

import java.util.Date;


public class UFeedback {
    /* 主键*/
    private Long fid;
     /*教材名称*/
    private String fname;
    /*数量*/
    private Long fnumber;
    /*收到教材申请日期*/
    private Date fdate;
        /*发放日期*/
    private Date grantdate;

    private Long aid;
    /*版本*/

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    private String edition;

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Long getFnumber() {
        return fnumber;
    }

    public void setFnumber(Long fnumber) {
        this.fnumber = fnumber;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public Date getGrantdate() {
        return grantdate;
    }

    public void setGrantdate(Date grantdate) {
        this.grantdate = grantdate;
    }
}
