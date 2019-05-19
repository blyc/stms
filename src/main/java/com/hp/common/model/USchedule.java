package com.hp.common.model;

import java.io.Serializable;

public class USchedule implements Serializable {
    private Integer usid;

    private String weeks;       //周几

    private String section;     //时间段

    private String course;      //什么课

    private String tname;       //老师

    private String place;       //地点

    private Long ccid;       //班级id

    private Integer subscript;  //下标

    private static final long serialVersionUID = 1L;

    public Integer getUsid() {
        return usid;
    }

    public void setUsid(Integer usid) {
        this.usid = usid;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public Integer getSubscript() {
        return subscript;
    }

    public void setSubscript(Integer subscript) {
        this.subscript = subscript;
    }
}