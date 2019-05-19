package com.hp.common.model;

import java.util.Date;


public class UTheibraryALL {

    private Long tid;
    private Long tnumber;
    private Date tdate;
    private Long did;
    private String edition;

    private String name;

    public Long getTid() {
        return tid;
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

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
