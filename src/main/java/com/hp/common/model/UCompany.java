package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;


public class UCompany implements Serializable {
    private Long cid;
    private String id;
    private String name;
    private String address;
    private String tel;
    private String describtion;
    private Date founded;
    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public UCompany() {
    }

    public UCompany(Long cid, String id, String name, String address, String tel, String describtion, Date founded, Long count) {
        this.cid = cid;
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.describtion = describtion;
        this.founded = founded;
        this.count = count;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Date getFounded() {
        return founded;
    }

    public void setFounded(Date founded) {
        this.founded = founded;
    }

    @Override
    public String toString() {
        return   name ;
    }
}
