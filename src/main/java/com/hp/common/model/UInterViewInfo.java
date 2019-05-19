package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class UInterViewInfo implements Serializable {
    private Long infoid;

    private Long iid;

    private Long sid;

    private String patriarch;

    private String patriarchtel;

    private Date comdate;

    private String comcontent;

    private String remark;

    private String state;

    private UStudent uStudent;

    private static final long serialVersionUID = 1L;

    public Long getInfoid() {
        return infoid;
    }

    public void setInfoid(Long infoid) {
        this.infoid = infoid;
    }

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getPatriarch() {
        return patriarch;
    }

    public void setPatriarch(String patriarch) {
        this.patriarch = patriarch;
    }

    public String getPatriarchtel() {
        return patriarchtel;
    }

    public void setPatriarchtel(String patriarchtel) {
        this.patriarchtel = patriarchtel;
    }

    public Date getComdate() {
        return comdate;
    }

    public void setComdate(Date comdate) {
        this.comdate = comdate;
    }

    public String getComcontent() {
        return comcontent;
    }

    public void setComcontent(String comcontent) {
        this.comcontent = comcontent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UStudent getuStudent() {
        return uStudent;
    }

    public void setuStudent(UStudent uStudent) {
        this.uStudent = uStudent;
    }
}