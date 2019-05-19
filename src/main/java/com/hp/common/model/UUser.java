package com.hp.common.model;

import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.Date;


public class UUser implements Serializable {
    public static final Long _0 = new Long(0);
    public static final Long _1 = new Long(1);
    private Long uid;
    private String nickname;
    private String email;
    private transient String pswd;
    private Date createTime;
    private Date lastLoginTime;
    private Long status;
    private Long cid;

    public UUser() {}
    public UUser(UUser user) {
        this.uid = user.getUid();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.pswd = user.getPswd();
        this.createTime = user.getCreateTime();
        this.lastLoginTime = user.getLastLoginTime();

    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }



    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}