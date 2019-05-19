package com.hp.common.model;

import java.io.Serializable;

public class UCtype implements Serializable {
    private Long id;

    private String tname;

    private Long tnum;

    private Long tcount;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Long getTnum() {
        return tnum;
    }

    public void setTnum(Long tnum) {
        this.tnum = tnum;
    }

    public Long getTcount() {
        return tcount;
    }

    public void setTcount(Long tcount) {
        this.tcount = tcount;
    }
}