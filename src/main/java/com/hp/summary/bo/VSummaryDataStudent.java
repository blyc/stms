package com.hp.summary.bo;

import com.hp.utils.excel.annotation.ExcelField;

/**
 * Created by @author GaoJie
 *
 * @date 2019/1/20
 */
public class VSummaryDataStudent {

    /** 出勤日期 */
    private String recordTime;
    /** 校区名称 */
    private String campusName;
    /** 年级 */
    private String grade;
    /** 专业名称 */
    private String majorName;
    /** 班级名称 */
    private String className;
    /** 学生姓名 */
    private String studentName;
    /** 出勤率 */
    private String attendanceRate;
    /** 迟到次数 */
    private int    belateCount;
    /** 旷课次数 */
    private int    truantCount;
    /** 请假次数 */
    private int    leaveCount;
    /** 早退次数 */
    private int    leaveearlyCount;

    @ExcelField(title = "出勤月份",type = 1, align = 2, sort = 1)
    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    @ExcelField(title = "校区名称",type = 1, align = 2, sort = 2)
    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    @ExcelField(title = "年级",type = 1, align = 2, sort = 3)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @ExcelField(title = "专业名称",type = 1, align = 2, sort = 4)
    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @ExcelField(title = "班级名称",type = 1, align = 2, sort = 5)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @ExcelField(title = "学生姓名",type = 1, align = 2, sort = 6)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @ExcelField(title = "出勤率",type = 1, align = 2, sort = 7)
    public String getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(String attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    @ExcelField(title = "迟到次数",type = 1, align = 2, sort = 8)
    public int getBelateCount() {
        return belateCount;
    }

    public void setBelateCount(int belateCount) {
        this.belateCount = belateCount;
    }

    @ExcelField(title = "旷课次数",type = 1, align = 2, sort = 9)
    public int getTruantCount() {
        return truantCount;
    }

    public void setTruantCount(int truantCount) {
        this.truantCount = truantCount;
    }

    @ExcelField(title = "请假次数",type = 1, align = 2, sort = 10)
    public int getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(int leaveCount) {
        this.leaveCount = leaveCount;
    }

    @ExcelField(title = "早退次数",type = 1, align = 2, sort = 11)
    public int getLeaveearlyCount() {
        return leaveearlyCount;
    }

    public void setLeaveearlyCount(int leaveearlyCount) {
        this.leaveearlyCount = leaveearlyCount;
    }
}
