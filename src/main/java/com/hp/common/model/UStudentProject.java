package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UStudentProject implements Serializable {
    private Long spid;

    private Long cid;

    private Long ccid;

    private Long sid;

    private BigDecimal rate;

    private Long vsubmit;

    private Long vpass;

    private String vaddr;

    private String vtime;

    private String codeaddr;

    private BigDecimal coderate;

    private String remark;

    private Date createTime;

    private Long createId;

    private Long cpid;

    private UStudent uStudent;

    private UClass uClass;

    private UClassProject uClassProject;

    private static final long serialVersionUID = 1L;

    public Long getSpid() {
        return spid;
    }

    public void setSpid(Long spid) {
        this.spid = spid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Long getVsubmit() {
        return vsubmit;
    }

    public void setVsubmit(Long vsubmit) {
        this.vsubmit = vsubmit;
    }

    public Long getVpass() {
        return vpass;
    }

    public void setVpass(Long vpass) {
        this.vpass = vpass;
    }

    public String getVaddr() {
        return vaddr;
    }

    public void setVaddr(String vaddr) {
        this.vaddr = vaddr;
    }

    public String getVtime() {
        return vtime;
    }

    public void setVtime(String vtime) {
        this.vtime = vtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCodeaddr() {
        return codeaddr;
    }

    public void setCodeaddr(String codeaddr) {
        this.codeaddr = codeaddr;
    }

    public BigDecimal getCoderate() {
        return coderate;
    }

    public void setCoderate(BigDecimal coderate) {
        this.coderate = coderate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getCpid() {
        return cpid;
    }

    public void setCpid(Long cpid) {
        this.cpid = cpid;
    }

    public UStudent getuStudent() {
        return uStudent;
    }

    public void setuStudent(UStudent uStudent) {
        this.uStudent = uStudent;
    }

    public UClass getuClass() {
        return uClass;
    }

    public void setuClass(UClass uClass) {
        this.uClass = uClass;
    }

    public UClassProject getuClassProject() {
        return uClassProject;
    }

    public void setuClassProject(UClassProject uClassProject) {
        this.uClassProject = uClassProject;
    }
}