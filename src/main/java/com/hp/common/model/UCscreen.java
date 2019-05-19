package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class UCscreen implements Serializable {
    private Long id;

    private String name;

    private String info;

    private Long showWeight;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getShowWeight() {
        return showWeight;
    }

    public void setShowWeight(Long showWeight) {
        this.showWeight = showWeight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}