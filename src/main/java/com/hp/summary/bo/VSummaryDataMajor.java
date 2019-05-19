package com.hp.summary.bo;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;

public class VSummaryDataMajor implements Serializable {
    private String summaryDate;

    private String companyname;

    private String majorname;

    private String summaryAttendance;

    private String summaryExam;

    private String summaryProject;

    private String summarySatisfaction;

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
    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname;
    }


    @ExcelField(title = "平均出勤率", align = 2, sort = 4)
    public String getSummaryAttendance() {
        return summaryAttendance;
    }

    public void setSummaryAttendance(String summaryAttendance) {
        this.summaryAttendance = summaryAttendance;
    }

    @ExcelField(title = "平均考试合格率", align = 2, sort = 5)
    public String getSummaryExam() {
        return summaryExam;
    }

    public void setSummaryExam(String summaryExam) {
        this.summaryExam = summaryExam;
    }

    @ExcelField(title = "平均项目完成率", align = 2, sort = 6)
    public String getSummaryProject() {
        return summaryProject;
    }

    public void setSummaryProject(String summaryProject) {
        this.summaryProject = summaryProject;
    }

    @ExcelField(title = "平均满意度", align = 2, sort = 7)
    public String getSummarySatisfaction() {
        return summarySatisfaction;
    }

    public void setSummarySatisfaction(String summarySatisfaction) {
        this.summarySatisfaction = summarySatisfaction;
    }
}