package com.hp.common.model;

import net.sf.json.JSONObject;

import java.io.Serializable;


public class URolePermission implements Serializable {

    private Long rid;
    private Long pid;
    public URolePermission() {
    }
    public URolePermission(Long rid,Long pid) {
        this.rid = rid;
        this.pid = pid;
    }
    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}