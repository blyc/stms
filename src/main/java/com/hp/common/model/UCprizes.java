package com.hp.common.model;

import java.io.Serializable;

public class UCprizes implements Serializable {
    private Long id;

    private String name;

    private String img;

    private Long flg;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getFlg() {
        return flg;
    }

    public void setFlg(Long flg) {
        this.flg = flg;
    }
}