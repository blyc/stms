package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UClassProject implements Serializable {
    private Long cpid;

    private Long cid;

    private Long ccid;

    private String name;

    private String description;

    private Date startTime;

    private Date startEnd;

    private Date createTime;

    private Long createId;

    private Long deleteFlg;

    private Long importFlg;

    private Long flg;

    private BigDecimal squalified;

    private VClass vClass;

    private UReviewProject uReviewProject;

    private static final long serialVersionUID = 1L;

    public Long getCpid() {
        return cpid;
    }

    public void setCpid(Long cpid) {
        this.cpid = cpid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartEnd() {
        return startEnd;
    }

    public void setStartEnd(Date startEnd) {
        this.startEnd = startEnd;
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

    public Long getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(Long deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public Long getImportFlg() {
        return importFlg;
    }

    public void setImportFlg(Long importFlg) {
        this.importFlg = importFlg;
    }

    public Long getFlg() {
        return flg;
    }

    public void setFlg(Long flg) {
        this.flg = flg;
    }

    public BigDecimal getSqualified() {
        return squalified;
    }

    public void setSqualified(BigDecimal squalified) {
        this.squalified = squalified;
    }

    public VClass getvClass() {
        return vClass;
    }

    public void setvClass(VClass vClass) {
        this.vClass = vClass;
    }

    public UReviewProject getuReviewProject() {
        return uReviewProject;
    }

    public void setuReviewProject(UReviewProject uReviewProject) {
        this.uReviewProject = uReviewProject;
    }


}