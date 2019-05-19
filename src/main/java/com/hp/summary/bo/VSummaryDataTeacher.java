package com.hp.summary.bo;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;

public class VSummaryDataTeacher implements Serializable {
    private String summaryDate;

    private String companyname;

    private String majortype;

    private String jsname;

    private String summaryAttendance;

    private String summaryExam;

    private String summaryProject;

    private String summarySatisfaction;

    private String reviewProject;

    private String reviewExam;

    private String reviewCompany;

    private String reviewHead;

    private static final long serialVersionUID = 1L;

    @ExcelField(title = "月份", align = 2, sort = 1)
    public String getSummaryDate() {
        return summaryDate;
    }

    public void setSummaryDate(String summaryDate) {
        this.summaryDate = summaryDate;
    }

    @ExcelField(title = "校区", align = 2, sort = 2)
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @ExcelField(title = "专业", align = 2, sort = 3)
    public String getMajortype() {
        return majortype;
    }

    public void setMajortype(String majortype) {
        this.majortype = majortype;
    }

    @ExcelField(title = "讲师", align = 2, sort = 4)
    public String getJsname() {
        return jsname;
    }

    public void setJsname(String jsname) {
        this.jsname = jsname;
    }

    @ExcelField(title = "校区出勤率", align = 2, sort = 5)
    public String getSummaryAttendance() {
        return summaryAttendance;
    }

    public void setSummaryAttendance(String summaryAttendance) {
        this.summaryAttendance = summaryAttendance;
    }

    @ExcelField(title = "校区考试合格率", align = 2, sort = 6)
    public String getSummaryExam() {
        return summaryExam;
    }

    public void setSummaryExam(String summaryExam) {
        this.summaryExam = summaryExam;
    }

    @ExcelField(title = "校区项目完成率", align = 2, sort = 7)
    public String getSummaryProject() {
        return summaryProject;
    }

    public void setSummaryProject(String summaryProject) {
        this.summaryProject = summaryProject;
    }

    @ExcelField(title = "校区满意度", align = 2, sort = 8)
    public String getSummarySatisfaction() {
        return summarySatisfaction;
    }

    public void setSummarySatisfaction(String summarySatisfaction) {
        this.summarySatisfaction = summarySatisfaction;
    }

    @ExcelField(title = "总部审查考试合格率", align = 2, sort = 9)
    public String getReviewExam() {
        return reviewExam;
    }

    public void setReviewExam(String reviewExam) {
        this.reviewExam = reviewExam;
    }

    @ExcelField(title = "总部审查项目完成率", align = 2, sort = 10)
    public String getReviewProject() {
        return reviewProject;
    }

    public void setReviewProject(String reviewProject) {
        this.reviewProject = reviewProject;
    }


    @ExcelField(title = "总部检查", align = 2, sort = 11)
    public String getReviewCompany() {
        return reviewCompany;
    }

    public void setReviewCompany(String reviewCompany) {
        this.reviewCompany = reviewCompany;
    }

    @ExcelField(title = "校区评估", align = 2, sort = 12)
    public String getReviewHead() {
        return reviewHead;
    }

    public void setReviewHead(String reviewHead) {
        this.reviewHead = reviewHead;
    }
}