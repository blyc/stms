package com.hp.common.model;

import java.util.Date;

public class UTheibrary {
    private Long tid;
    private Long tnumber;
    private Date tdate;
    private Long did;
    private String edition;


    private Udetails udetails;
    private  UMajor uMajor;

    public Long getTid() {
        return tid;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getTnumber() {
        return tnumber;
    }

    public void setTnumber(Long tnumber) {
        this.tnumber = tnumber;
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Udetails getUdetails() {
        return udetails;
    }

    public void setUdetails(Udetails udetails) {
        this.udetails = udetails;
    }

    public UMajor getuMajor() {
        return uMajor;
    }

    public void setuMajor(UMajor uMajor) {
        this.uMajor = uMajor;
    }
}
