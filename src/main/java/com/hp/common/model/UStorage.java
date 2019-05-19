package com.hp.common.model;

import java.util.Date;


public class UStorage {
    private Long sid;
    private Date sdate;
    private  Long snumber;
    private String sname;
    private Long did;
    private String edition;
    private Udetails udetails;
    private  UMajor uMajor;

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public UMajor getuMajor() {
        return uMajor;
    }

    public void setuMajor(UMajor uMajor) {
        this.uMajor = uMajor;
    }

    public Udetails getUdetails() {
        return udetails;
    }

    public void setUdetails(Udetails udetails) {
        this.udetails = udetails;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Long getSnumber() {
        return snumber;
    }

    public void setSnumber(Long snumber) {
        this.snumber = snumber;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }
}
