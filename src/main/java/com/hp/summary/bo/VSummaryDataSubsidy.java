package com.hp.summary.bo;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * @Author: boy
 * @Date: 2018/12/21
 * @Description:
 */
public class VSummaryDataSubsidy implements Serializable {

    private String summaryDate;

    private String companyname;

    private String majortype;

    private String jsname;

    private String summaryAttendance;

    private String reviewExam;

    private String reviewProject;

    private String summarySatisfaction;

    private String reviewHead;

    private String reviewCompany;

    private String avgBase;

    private String base;

    private String subsidy;

    private String dayCount;

    private String courseSubsidy;

    private String base1;

    private String base2;

    private String diffBase;

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

    @ExcelField(title = "出勤率", align = 2, sort = 5)
    public String getSummaryAttendance() {
        return summaryAttendance;
    }

    public void setSummaryAttendance(String summaryAttendance) {
        this.summaryAttendance = summaryAttendance;
    }

    @ExcelField(title = "考试合格率", align = 2, sort = 6)
    public String getReviewExam() {
        return reviewExam;
    }

    public void setReviewExam(String reviewExam) {
        this.reviewExam = reviewExam;
    }

    @ExcelField(title = "项目提交率", align = 2, sort = 7)
    public String getReviewProject() {
        return reviewProject;
    }

    public void setReviewProject(String reviewProject) {
        this.reviewProject = reviewProject;
    }

    @ExcelField(title = "满意度", align = 2, sort = 8)
    public String getSummarySatisfaction() {
        return summarySatisfaction;
    }

    public void setSummarySatisfaction(String summarySatisfaction) {
        this.summarySatisfaction = summarySatisfaction;
    }

    @ExcelField(title = "总部检查", align = 2, sort = 9)
    public String getReviewHead() {
        return reviewHead;
    }

    public void setReviewHead(String reviewHead) {
        this.reviewHead = reviewHead;
    }

    @ExcelField(title = "校区评估", align = 2, sort = 10)
    public String getReviewCompany() {
        return reviewCompany;
    }

    public void setReviewCompany(String reviewCompany) {
        this.reviewCompany = reviewCompany;
    }

    @ExcelField(title = "绩效结果", align = 2, sort = 11)
    public String getAvgBase() {
        return avgBase;
    }

    public void setAvgBase(String avgBase) {
        this.avgBase = avgBase;
    }

    @ExcelField(title = "考核基数", align = 2, sort = 12)
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @ExcelField(title = "授课津贴", align = 2, sort = 13)
    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    @ExcelField(title = "授课天数", align = 2, sort = 12)
    public String getDayCount() {
        return dayCount;
    }

    public void setDayCount(String dayCount) {
        this.dayCount = dayCount;
    }

    @ExcelField(title = "应发", align = 2, sort = 14)
    public String getCourseSubsidy() {
        return courseSubsidy;
    }

    public void setCourseSubsidy(String courseSubsidy) {
        this.courseSubsidy = courseSubsidy;
    }

    @ExcelField(title = "应扣", align = 2, sort = 15)
    public String getBase1() {
        return base1;
    }

    public void setBase1(String base1) {
        this.base1 = base1;
    }

    @ExcelField(title = "应奖", align = 2, sort = 16)
    public String getBase2() {
        return base2;
    }

    public void setBase2(String base2) {
        this.base2 = base2;
    }

    @ExcelField(title = "实发", align = 2, sort = 17)
    public String getDiffBase() {
        return diffBase;
    }

    public void setDiffBase(String diffBase) {
        this.diffBase = diffBase;
    }
}
