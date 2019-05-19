package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class UClassExam implements Serializable {
    private Long ceid;
    private Long cid;
    private Long ccid;
    private String examtype;
    private Date createtime;
    private Date examtime;
    private Long uid;
    private String examsite;
    private Integer examnum;
    private Double rateStandard;
    private Integer mark;
    private String examproject;
    private String invigilator;
    private BigDecimal qualifiedrate;
    private String examevent;
    private Long deleteFlg;
    private Long importFlg;
    private VClass vClass;
    private UReviewExam uReviewExam;


    public Long getCeid() {
        return ceid;
    }

    public void setCeid(Long ceid) {
        this.ceid = ceid;
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

    public String getExamtype() {
        return examtype;
    }

    public void setExamtype(String examtype) {
        this.examtype = examtype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getExamtime() {
        return examtime;
    }

    public void setExamtime(Date examtime) {
        this.examtime = examtime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getExamsite() {
        return examsite;
    }

    public void setExamsite(String examsite) {
        this.examsite = examsite;
    }

    public Integer getExamnum() {
        return examnum;
    }

    public void setExamnum(Integer examnum) {
        this.examnum = examnum;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getExamproject() {
        return examproject;
    }

    public void setExamproject(String examproject) {
        this.examproject = examproject;
    }

    public String getInvigilator() {
        return invigilator;
    }

    public void setInvigilator(String invigilator) {
        this.invigilator = invigilator;
    }

    public BigDecimal getQualifiedrate() {
        return qualifiedrate;
    }

    public void setQualifiedrate(BigDecimal qualifiedrate) {
        this.qualifiedrate = qualifiedrate;
    }

    public String getExamevent() {
        return examevent;
    }

    public void setExamevent(String examevent) {
        this.examevent = examevent;
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

    public VClass getvClass() {
        return vClass;
    }

    public void setvClass(VClass vClass) {
        this.vClass = vClass;
    }

    public UReviewExam getuReviewExam() {
        return uReviewExam;
    }

    public void setuReviewExam(UReviewExam uReviewExam) {
        this.uReviewExam = uReviewExam;
    }

    public Double getRateStandard() {
        return rateStandard;
    }

    public void setRateStandard(Double rateStandard) {
        this.rateStandard = rateStandard;
    }
}
