package com.hp.common.model;

import java.io.Serializable;


public class UExam implements Serializable {
    private Integer cid;
    private Integer id;
    private Integer sid;
    private Integer written;
    private Integer online;
    private Integer sumgrade;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getWritten() {
        return written;
    }

    public void setWritten(Integer written) {
        this.written = written;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getSumgrade() {
        return sumgrade;
    }

    public void setSumgrade(Integer sumgrade) {
        this.sumgrade = sumgrade;
    }
}
