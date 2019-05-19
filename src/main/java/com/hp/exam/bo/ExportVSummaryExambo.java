package com.hp.exam.bo;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;

public class ExportVSummaryExambo implements Serializable {
    private String summaryTime;
    private Long ccid;
    private String summaryExam;
    private String companyName;
    private String className;
    private String jsname;
    private String bzrname;

    private static final long serialVersionUID = 1L;

    @ExcelField(title = "月份", align = 2, sort = 1)
    public String getSummaryTime() {
        return summaryTime;
    }

    public void setSummaryTime(String summaryTime) {
        this.summaryTime = summaryTime;
    }

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    @ExcelField(title = "考试合格率", align = 2, sort = 6)
    public String getSummaryExam() {
        return summaryExam;
    }

    public void setSummaryExam(String summaryExam) {
        this.summaryExam = summaryExam;
    }

    @ExcelField(title = "校区", align = 2, sort = 2)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @ExcelField(title = "班级", align = 2, sort = 3)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @ExcelField(title = "讲师", align = 2, sort = 4)
    public String getJsname() {
        return jsname;
    }

    public void setJsname(String jsname) {
        this.jsname = jsname;
    }

    @ExcelField(title = "班主任", align = 2, sort = 5)
    public String getBzrname() {
        return bzrname;
    }

    public void setBzrname(String bzrname) {
        this.bzrname = bzrname;
    }
}