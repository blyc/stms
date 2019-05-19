package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class UInterView implements Serializable {
    private Long iid;

    private Long cid;

    private Long ccid;

    private String motif;

    private Date begintime;

    private Date endtime;

    private Double comrate;

    private String createname;

    private Date createtime;

    private UCompany uCompany;

    private UClass uClass;

    private static final long serialVersionUID = 1L;

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
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

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Double getComrate() {
        return comrate;
    }

    public void setComrate(Double comrate) {
        this.comrate = comrate;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public UCompany getuCompany() {
        return uCompany;
    }

    public void setuCompany(UCompany uCompany) {
        this.uCompany = uCompany;
    }

    public UClass getuClass() {
        return uClass;
    }

    public void setuClass(UClass uClass) {
        this.uClass = uClass;
    }
}