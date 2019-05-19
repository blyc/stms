package com.hp.common.model;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by @author GaoJie
 *
 * @date 2019/1/7
 */
public class UStandardImport implements Serializable {

    @ExcelField(title = "月份(例如: 2019-01)", type = 0, align = 2, sort = 1)
    private String standardTime;

    @ExcelField(title = "年级(例如: 18 或 18级)", type = 0, align = 2, sort = 2)
    private String grade;

    @ExcelField(title = "专业(已存在的专业)", type = 0, align = 2, sort = 3)
    private String mid;

    @ExcelField(title = "适用校区(多个校区用-号分隔,例如: 周口校区-平顶山校区 )", type = 0, align = 2, sort = 4)
    private String scope;

    @ExcelField(title = "考试权重(%)", type = 0, align = 2, sort = 5)
    private String weightExam;

    @ExcelField(title = "考试考核标准", type = 0, align = 2, sort = 6)
    private String standardExam;

    @ExcelField(title = "项目权重(%)", type = 0, align = 2, sort = 7)
    private String weightProject;

    @ExcelField(title = "项目考核标准", type = 0, align = 2, sort = 8)
    private String standardProject;

    @ExcelField(title = "出勤权重(%)", type = 0, align = 2, sort = 9)
    private String weightAttendance;

    @ExcelField(title = "出勤考核标准", type = 0, align = 2, sort = 10)
    private String standardAttendance;

    @ExcelField(title = "满意度权重(%)", type = 0, align = 2, sort = 11)
    private String weightSatisfaction;

    @ExcelField(title = "满意度考核标准", type = 0, align = 2, sort = 12)
    private String standardSatisfaction;

    @ExcelField(title = "校区权重(%)", type = 0, align = 2, sort = 13)
    private String weightSchool;

    @ExcelField(title = "校区考核标准", type = 0, align = 2, sort = 14)
    private String standardSchool;

    @ExcelField(title = "总部权重(%)", type = 0, align = 2, sort = 15)
    private String weightHead;

    @ExcelField(title = "总部考核标准", type = 0, align = 2, sort = 16)
    private String standardHead;

    @Override
    public String toString() {
        return "UStandardImport{" +
                "standardTime='" + standardTime + '\'' +
                ", grade='" + grade + '\'' +
                ", majorType='" + mid + '\'' +
                ", weightExam='" + weightExam + '\'' +
                ", standardExam='" + standardExam + '\'' +
                ", weightProject='" + weightProject + '\'' +
                ", standardProject='" + standardProject + '\'' +
                ", weightAttendance='" + weightAttendance + '\'' +
                ", standardAttendance='" + standardAttendance + '\'' +
                ", weightSatisfaction='" + weightSatisfaction + '\'' +
                ", standardSatisfaction='" + standardSatisfaction + '\'' +
                ", weightSchool='" + weightSchool + '\'' +
                ", standardSchool='" + standardSchool + '\'' +
                ", weightHead='" + weightHead + '\'' +
                ", standardHead='" + standardHead + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }

    public String getStandardTime() {
        return standardTime;
    }

    public void setStandardTime(String standardTime) {
        this.standardTime = standardTime;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String majorType) {
        this.mid = majorType;
    }

    public String getWeightExam() {
        return weightExam;
    }

    public void setWeightExam(String weightExam) {
        this.weightExam = weightExam;
    }

    public String getStandardExam() {
        return standardExam;
    }

    public void setStandardExam(String standardExam) {
        this.standardExam = standardExam;
    }

    public String getWeightProject() {
        return weightProject;
    }

    public void setWeightProject(String weightProject) {
        this.weightProject = weightProject;
    }

    public String getStandardProject() {
        return standardProject;
    }

    public void setStandardProject(String standardProject) {
        this.standardProject = standardProject;
    }

    public String getWeightAttendance() {
        return weightAttendance;
    }

    public void setWeightAttendance(String weightAttendance) {
        this.weightAttendance = weightAttendance;
    }

    public String getStandardAttendance() {
        return standardAttendance;
    }

    public void setStandardAttendance(String standardAttendance) {
        this.standardAttendance = standardAttendance;
    }

    public String getWeightSatisfaction() {
        return weightSatisfaction;
    }

    public void setWeightSatisfaction(String weightSatisfaction) {
        this.weightSatisfaction = weightSatisfaction;
    }

    public String getStandardSatisfaction() {
        return standardSatisfaction;
    }

    public void setStandardSatisfaction(String standardSatisfaction) {
        this.standardSatisfaction = standardSatisfaction;
    }

    public String getWeightSchool() {
        return weightSchool;
    }

    public void setWeightSchool(String weightSchool) {
        this.weightSchool = weightSchool;
    }

    public String getStandardSchool() {
        return standardSchool;
    }

    public void setStandardSchool(String standardSchool) {
        this.standardSchool = standardSchool;
    }

    public String getWeightHead() {
        return weightHead;
    }

    public void setWeightHead(String weightHead) {
        this.weightHead = weightHead;
    }

    public String getStandardHead() {
        return standardHead;
    }

    public void setStandardHead(String standardHead) {
        this.standardHead = standardHead;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
