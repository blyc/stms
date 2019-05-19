package com.hp.attendance.bo;

public class Remarksbo {
    private Long caid;
    private Long sid;
    private Long record;
    private String name;
    private String remarks;

    public Long getCaid() {
        return caid;
    }

    public void setCaid(Long caid) {
        this.caid = caid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getRecord() {
        return record;
    }

    public void setRecord(Long record) {
        this.record = record;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
