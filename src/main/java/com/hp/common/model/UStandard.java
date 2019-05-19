package com.hp.common.model;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UStandard implements Serializable {
    private Long sid;

    @ExcelField(title = "考试权重(%)", type = 0, align = 2, sort = 4)
    private Long weightExam;

    @ExcelField(title = "考试考核标准", type = 0, align = 2, sort = 5)
    private BigDecimal standardExam;

    private BigDecimal lowStanndardExam;

    @ExcelField(title = "项目权重(%)", type = 0, align = 2, sort = 6)
    private Long weightProject;

    @ExcelField(title = "项目考核标准", type = 0, align = 2, sort = 7)
    private BigDecimal standardProject;

    private BigDecimal lowStandardProject;

    @ExcelField(title = "满意度权重(%)", type = 0, align = 2, sort = 10)
    private Long weightSatisfaction;

    @ExcelField(title = "满意度考核标准", type = 0, align = 2, sort = 11)
    private BigDecimal standardSatisfaction;

    private BigDecimal lowStandardSatisfaction;

    @ExcelField(title = "出勤权重(%)", type = 0, align = 2, sort = 8)
    private Long weightAttendance;

    @ExcelField(title = "出勤考核标准", type = 0, align = 2, sort = 9)
    private BigDecimal standardAttendance;

    private BigDecimal lowStandardAttendance;

    @ExcelField(title = "校区权重(%)", type = 0, align = 2, sort = 12)
    private Long weightSchool;

    @ExcelField(title = "校区考核标准", type = 0, align = 2, sort = 13)
    private BigDecimal standardSchool;

    private BigDecimal lowStandardSchool;
    @ExcelField(title = "总部权重(%)", type = 0, align = 2, sort = 14)
    private Long weightHead;

    @ExcelField(title = "总部考核标准", type = 0, align = 2, sort = 15)
    private BigDecimal standardHead;

    private BigDecimal lowStandardHead;

    private Long cid;

    private Long mid;

    @ExcelField(title = "专业", type = 0, align = 2, sort = 3)
    private String majorType;

    @ExcelField(title = "适用校区(多个校区用-号分隔,不写则为全校区)", type = 0, align = 2, sort = 16)
    private String scope;

    private BigDecimal result;

    private BigDecimal base;

    @ExcelField(title = "年级", type = 0, align = 2, sort = 2)
    private String grade;

    @ExcelField(title = "月份", type = 0, align = 2, sort = 1)
    private String standardTime;

    private Date createTime;

    private Long createId;

    private String cids;

    private UMajor uMajor;

    private UCompany uCompany;

    private static final long serialVersionUID = 1L;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getWeightExam() {
        return weightExam;
    }

    public void setWeightExam(Long weightExam) {
        this.weightExam = weightExam;
    }

    public BigDecimal getStandardExam() {
        return standardExam;
    }

    public void setStandardExam(BigDecimal standardExam) {
        this.standardExam = standardExam;
    }

    public BigDecimal getLowStanndardExam() {
        return lowStanndardExam;
    }

    public void setLowStanndardExam(BigDecimal lowStanndardExam) {
        this.lowStanndardExam = lowStanndardExam;
    }

    public Long getWeightProject() {
        return weightProject;
    }

    public void setWeightProject(Long weightProject) {
        this.weightProject = weightProject;
    }

    public BigDecimal getStandardProject() {
        return standardProject;
    }

    public void setStandardProject(BigDecimal standardProject) {
        this.standardProject = standardProject;
    }

    public BigDecimal getLowStandardProject() {
        return lowStandardProject;
    }

    public void setLowStandardProject(BigDecimal lowStandardProject) {
        this.lowStandardProject = lowStandardProject;
    }

    public Long getWeightSatisfaction() {
        return weightSatisfaction;
    }

    public void setWeightSatisfaction(Long weightSatisfaction) {
        this.weightSatisfaction = weightSatisfaction;
    }

    public BigDecimal getStandardSatisfaction() {
        return standardSatisfaction;
    }

    public void setStandardSatisfaction(BigDecimal standardSatisfaction) {
        this.standardSatisfaction = standardSatisfaction;
    }

    public BigDecimal getLowStandardSatisfaction() {
        return lowStandardSatisfaction;
    }

    public void setLowStandardSatisfaction(BigDecimal lowStandardSatisfaction) {
        this.lowStandardSatisfaction = lowStandardSatisfaction;
    }

    public Long getWeightAttendance() {
        return weightAttendance;
    }

    public void setWeightAttendance(Long weightAttendance) {
        this.weightAttendance = weightAttendance;
    }

    public BigDecimal getStandardAttendance() {
        return standardAttendance;
    }

    public void setStandardAttendance(BigDecimal standardAttendance) {
        this.standardAttendance = standardAttendance;
    }

    public BigDecimal getLowStandardAttendance() {
        return lowStandardAttendance;
    }

    public void setLowStandardAttendance(BigDecimal lowStandardAttendance) {
        this.lowStandardAttendance = lowStandardAttendance;
    }

    public Long getWeightSchool() {
        return weightSchool;
    }

    public void setWeightSchool(Long weightSchool) {
        this.weightSchool = weightSchool;
    }

    public BigDecimal getStandardSchool() {
        return standardSchool;
    }

    public void setStandardSchool(BigDecimal standardSchool) {
        this.standardSchool = standardSchool;
    }

    public BigDecimal getLowStandardSchool() {
        return lowStandardSchool;
    }

    public void setLowStandardSchool(BigDecimal lowStandardSchool) {
        this.lowStandardSchool = lowStandardSchool;
    }

    public Long getWeightHead() {
        return weightHead;
    }

    public void setWeightHead(Long weightHead) {
        this.weightHead = weightHead;
    }

    public BigDecimal getStandardHead() {
        return standardHead;
    }

    public void setStandardHead(BigDecimal standardHead) {
        this.standardHead = standardHead;
    }

    public BigDecimal getLowStandardHead() {
        return lowStandardHead;
    }

    public void setLowStandardHead(BigDecimal lowStandardHead) {
        this.lowStandardHead = lowStandardHead;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public UMajor getuMajor() {
        return uMajor;
    }

    public void setuMajor(UMajor uMajor) {
        this.uMajor = uMajor;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStandardTime() {
        return standardTime;
    }

    public void setStandardTime(String standardTime) {
        this.standardTime = standardTime;
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

    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCids() {
        return cids;
    }

    public void setCids(String cids) {
        this.cids = cids;
    }

    public UCompany getuCompany() {
        return uCompany;
    }

    public void setuCompany(UCompany uCompany) {
        this.uCompany = uCompany;
    }
}