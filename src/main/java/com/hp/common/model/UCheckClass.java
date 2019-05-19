package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class UCheckClass implements Serializable {
    private Long chid;

    private Long ccid;

    private Long cid;

    private Date chdate;

    private String timequantun;//时间段

    private String ccname;

    private Long due;

    private Long actual;

    private Double attendancerate;//出勤率

    private String jsname;

    private String bzrname;

    private String projector; //投影

    private String courseware;//课件

    private String touchscreen;//控屏

    private String teapro;//教学进度

    private String teastate;

    private String stustate;

    private String teatourclass;//讲师巡课

    private Long stunum;

    private String discipline;//班级纪律

    private String remark;//备注

    private String beforestate;

    private String middlestate;

    private String laterstate;

    private UClass uClass;

    private VClass vClass;

    private UCompany uCompany;

    private static final long serialVersionUID = 1L;

    public VClass getvClass() {
        return vClass;
    }

    public void setvClass(VClass vClass) {
        this.vClass = vClass;
    }

    public Long getChid() {
        return chid;
    }

    public void setChid(Long chid) {
        this.chid = chid;
    }

    public Date getChdate() {
        return chdate;
    }

    public void setChdate(Date chdate) {
        this.chdate = chdate;
    }

    public String getTimequantun() {
        return timequantun;
    }

    public void setTimequantun(String timequantun) {
        this.timequantun = timequantun;
    }

    public String getCcname() {
        return ccname;
    }

    public void setCcname(String ccname) {
        this.ccname = ccname;
    }

    public Long getDue() {
        return due;
    }

    public void setDue(Long due) {
        this.due = due;
    }

    public Long getActual() {
        return actual;
    }

    public void setActual(Long actual) {
        this.actual = actual;
    }

    public Double getAttendancerate() {
        return attendancerate;
    }

    public void setAttendancerate(Double attendancerate) {
        this.attendancerate = attendancerate;
    }

    public String getJsname() {
        return jsname;
    }

    public void setJsname(String jsname) {
        this.jsname = jsname;
    }

    public String getBzrname() {
        return bzrname;
    }

    public void setBzrname(String bzrname) {
        this.bzrname = bzrname;
    }

    public String getProjector() {
        return projector;
    }

    public void setProjector(String projector) {
        this.projector = projector;
    }

    public String getCourseware() {
        return courseware;
    }

    public void setCourseware(String courseware) {
        this.courseware = courseware;
    }

    public String getTouchscreen() {
        return touchscreen;
    }

    public void setTouchscreen(String touchscreen) {
        this.touchscreen = touchscreen;
    }

    public String getTeapro() {
        return teapro;
    }

    public void setTeapro(String teapro) {
        this.teapro = teapro;
    }

    public String getTeastate() {
        return teastate;
    }

    public void setTeastate(String teastate) {
        this.teastate = teastate;
    }

    public String getStustate() {
        return stustate;
    }

    public void setStustate(String stustate) {
        this.stustate = stustate;
    }

    public String getTeatourclass() {
        return teatourclass;
    }

    public void setTeatourclass(String teatourclass) {
        this.teatourclass = teatourclass;
    }

    public Long getStunum() {
        return stunum;
    }

    public void setStunum(Long stunum) {
        this.stunum = stunum;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBeforestate() {
        return beforestate;
    }

    public void setBeforestate(String beforestate) {
        this.beforestate = beforestate;
    }

    public String getMiddlestate() {
        return middlestate;
    }

    public void setMiddlestate(String middlestate) {
        this.middlestate = middlestate;
    }

    public String getLaterstate() {
        return laterstate;
    }

    public void setLaterstate(String laterstate) {
        this.laterstate = laterstate;
    }

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public UClass getuClass() {
        return uClass;
    }

    public UCompany getuCompany() {
        return uCompany;
    }

    public void setuCompany(UCompany uCompany) {
        this.uCompany = uCompany;
    }

    public void setuClass(UClass uClass) {
        this.uClass = uClass;
    }
}