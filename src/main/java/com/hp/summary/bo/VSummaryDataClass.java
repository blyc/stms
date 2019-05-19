package com.hp.summary.bo;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;

public class VSummaryDataClass implements Serializable {
    private String summaryDate;

    private String classname;

    private String companyname;

    private String jsname;

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

    @ExcelField(title = "班级", align = 2, sort = 4)
    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @ExcelField(title = "校区", align = 2, sort = 2)
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }


    @ExcelField(title = "讲师", align = 2, sort = 3)
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
    public String getSummaryExam() {
        return summaryExam;
    }

    public void setSummaryExam(String summaryExam) {
        this.summaryExam = summaryExam;
    }

    @ExcelField(title = "项目完成率", align = 2, sort = 7)
    public String getSummaryProject() {
        return summaryProject;
    }

    public void setSummaryProject(String summaryProject) {
        this.summaryProject = summaryProject;
    }

    @ExcelField(title = "讲师满意度完成率", align = 2, sort = 8)
    public String getSummarySatisfaction() {
        return summarySatisfaction;
    }

    public void setSummarySatisfaction(String summarySatisfaction) {
        this.summarySatisfaction = summarySatisfaction;
    }
}