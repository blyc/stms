package com.hp.common.model;

import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: boy
 * @Date: 2018/02/06
 * @Description:
 */
public class URole implements Serializable {

    private Long rid;
    private String name;
    private String type;

    private List<UPermission> permissions = new LinkedList<UPermission>();

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public List<UPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<UPermission> permissions) {
        this.permissions = permissions;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}