package com.hp.common.model;

import com.hp.utils.excel.annotation.ExcelField;
import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class UPermission implements Serializable {

    private Long pid;
    private String url;
    private String name;
    private List<URole> uRoles = new LinkedList<URole>();

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @ExcelField(title = "url", type = 1, align = 2, sort = 2)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ExcelField(title = "名称", type = 1, align = 2, sort = 1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<URole> getuRoles() {
        return uRoles;
    }

    public void setuRoles(List<URole> uRoles) {
        this.uRoles = uRoles;
    }

    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}