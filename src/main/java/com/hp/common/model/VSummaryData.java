package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class VSummaryData implements Serializable {
    private String summaryDate;

    private Long cid;

    private Long ccid;

    private String classname;

    private Long mid;

    private Long num;

    private String grade;

    private String companyname;

    private String majorname;

    private String majortype;

    private Long jseid;

    private String jsname;

    private Long dseid;

    private String dsname;

    private Long bzreid;

    private String bzrname;

    private String stage;

    private BigDecimal summaryAttendance;

    private BigDecimal summaryExam;

    private BigDecimal summaryProject;

    private BigDecimal summarySatisfaction;

    private BigDecimal reviewProject;

    private BigDecimal reviewProjectRate;

    private BigDecimal reviewExam;

    private BigDecimal reviewExamRate;

    private BigDecimal reviewCompany;

    private Long reviewCompanyFlg;

    private BigDecimal reviewHead;

    private Long reviewHeadFlg;

    //private BigDecimal reviewRate;//抽查合格率 (项目抽查合格率+考试抽查合格率)/2

    /** 出勤日期 */
    private String recordTime;
    /** 校区名称 */
    private String campusName;
    /** 专业名称 */
    private String majorName;
    /** 班级名称 */
    private String className;
    /** 学生姓名 */
    private String studentName;
    /** 出勤率 */
    private double attendanceRate;
    /** 出勤次数 */
    private int attendanceCount;
    /** 迟到次数 */
    private int belateCount;
    /** 旷课次数 */
    private int truantCount;
    /** 请假次数 */
    private int leaveCount;
    /** 早退次数 */
    private int leaveearlyCount;

    public int getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(int attendanceCount) {
        this.attendanceCount = attendanceCount;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(double attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    public int getBelateCount() {
        return belateCount;
    }

    public void setBelateCount(int belateCount) {
        this.belateCount = belateCount;
    }

    public int getTruantCount() {
        return truantCount;
    }

    public void setTruantCount(int truantCount) {
        this.truantCount = truantCount;
    }

    public int getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(int leaveCount) {
        this.leaveCount = leaveCount;
    }

    public int getLeaveearlyCount() {
        return leaveearlyCount;
    }

    public void setLeaveearlyCount(int leaveearlyCount) {
        this.leaveearlyCount = leaveearlyCount;
    }



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

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname;
    }

    public String getMajortype() {
        return majortype;
    }

    public void setMajortype(String majortype) {
        this.majortype = majortype;
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

    public Long getDseid() {
        return dseid;
    }

    public void setDseid(Long dseid) {
        this.dseid = dseid;
    }

    public String getDsname() {
        return dsname;
    }

    public void setDsname(String dsname) {
        this.dsname = dsname;
    }

    public Long getBzreid() {
        return bzreid;
    }

    public void setBzreid(Long bzreid) {
        this.bzreid = bzreid;
    }

    public String getBzrname() {
        return bzrname;
    }

    public void setBzrname(String bzrname) {
        this.bzrname = bzrname;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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

    public Long getReviewCompanyFlg() {
        return reviewCompanyFlg;
    }

    public void setReviewCompanyFlg(Long reviewCompanyFlg) {
        this.reviewCompanyFlg = reviewCompanyFlg;
    }

    public Long getReviewHeadFlg() {
        return reviewHeadFlg;
    }

    public void setReviewHeadFlg(Long reviewHeadFlg) {
        this.reviewHeadFlg = reviewHeadFlg;
    }

    public BigDecimal getReviewProjectRate() {
        return reviewProjectRate;
    }

    public void setReviewProjectRate(BigDecimal reviewProjectRate) {
        this.reviewProjectRate = reviewProjectRate;
    }

    public BigDecimal getReviewExamRate() {
        return reviewExamRate;
    }

    public void setReviewExamRate(BigDecimal reviewExamRate) {
        this.reviewExamRate = reviewExamRate;
    }

//    public BigDecimal getReviewRate() {
//        if(this.reviewExamRate!=null && this.reviewProjectRate!=null){
//            reviewRate  = (this.reviewExamRate.add(this.reviewProjectRate)).divide(new BigDecimal(2), 4, BigDecimal.ROUND_HALF_UP);
//            if(reviewRate.compareTo(new BigDecimal(0.85))==-1){
//                reviewRate=new BigDecimal(0);
//            }
//        }
//        return reviewRate;
//    }
//
//    public void setReviewRate(BigDecimal reviewRate) {
//        this.reviewRate = reviewRate;
//    }
}