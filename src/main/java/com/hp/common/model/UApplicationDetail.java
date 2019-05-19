package com.hp.common.model;

import java.io.Serializable;

public class UApplicationDetail implements Serializable {
    private Long adid;

    private Long mid;

    private Long number;

    private String remarks;

    private String mname;

    private String edition;

    private Long salesprice;

    private Long price;

    private String stage;

    private Long aid;

    private String grade;


    private static final long serialVersionUID = 1L;

    public Long getAdid() {
        return adid;
    }

    public void setAdid(Long adid) {
        this.adid = adid;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Long getSalesprice() {
        return salesprice;
    }

    public void setSalesprice(Long salesprice) {
        this.salesprice = salesprice;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}