package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class VSummaryResult implements Serializable {
    private String summaryDate;

    private Long cid;

    private String companyname;

    private Long jseid;

    private String jsname;

    private String majortype;

    private BigDecimal summaryAttendance;

    private BigDecimal summaryExam;

    private BigDecimal summaryProject;

    private BigDecimal summarySatisfaction;

    private BigDecimal reviewProject;

    private BigDecimal reviewExam;

    private BigDecimal reviewCompany;

    private BigDecimal reviewHead;

    private BigDecimal base;

    private BigDecimal avgBase;

    private Long dayCount;

    private Long subsidy;

    private Long courseSubsidy;

    private BigDecimal diffBase;

    private static final long serialVersionUID = 1L;

    public String getSummaryDate() {
        return summaryDate;
    }

    public void setSummaryDate(String summaryDate) {
        this.summaryDate = summaryDate;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Long getJseid() {
        return jseid;
    }

    public void setJseid(Long jseid) {
        this.jseid = jseid;
    }

    public String getJsname() {
        return jsname;
    }

    public void setJsname(String jsname) {
        this.jsname = jsname;
    }

    public String getMajortype() {
        return majortype;
    }

    public void setMajortype(String majortype) {
        this.majortype = majortype;
    }

    public BigDecimal getSummaryAttendance() {
        return summaryAttendance;
    }

    public void setSummaryAttendance(BigDecimal summaryAttendance) {
        this.summaryAttendance = summaryAttendance;
    }

    public BigDecimal getSummaryExam() {
        return summaryExam;
    }

    public void setSummaryExam(BigDecimal summaryExam) {
        this.summaryExam = summaryExam;
    }

    public BigDecimal getSummaryProject() {
        return summaryProject;
    }

    public void setSummaryProject(BigDecimal summaryProject) {
        this.summaryProject = summaryProject;
    }

    public BigDecimal getSummarySatisfaction() {
        return summarySatisfaction;
    }

    public void setSummarySatisfaction(BigDecimal summarySatisfaction) {
        this.summarySatisfaction = summarySatisfaction;
    }

    public BigDecimal getReviewProject() {
        return reviewProject;
    }

    public void setReviewProject(BigDecimal reviewProject) {
        this.reviewProject = reviewProject;
    }

    public BigDecimal getReviewExam() {
        return reviewExam;
    }

    public void setReviewExam(BigDecimal reviewExam) {
        this.reviewExam = reviewExam;
    }

    public BigDecimal getReviewCompany() {
        return reviewCompany;
    }

    public void setReviewCompany(BigDecimal reviewCompany) {
        this.reviewCompany = reviewCompany;
    }

    public BigDecimal getReviewHead() {
        return reviewHead;
    }

    public void setReviewHead(BigDecimal reviewHead) {
        this.reviewHead = reviewHead;
    }

    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
    }

    public BigDecimal getAvgBase() {
        return avgBase;
    }

    public void setAvgBase(BigDecimal avgBase) {
        this.avgBase = avgBase;
    }

    public Long getDayCount() {
        return dayCount;
    }

    public Long getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(Long subsidy) {
        this.subsidy = subsidy;
    }

    public void setDayCount(Long dayCount) {
        this.dayCount = dayCount;
    }

    public Long getCourseSubsidy() {
        return courseSubsidy;
    }

    public void setCourseSubsidy(Long courseSubsidy) {
        this.courseSubsidy = courseSubsidy;
    }

    public BigDecimal getDiffBase() {
        return diffBase;
    }

    public void setDiffBase(BigDecimal diffBase) {
        this.diffBase = diffBase;
    }
}