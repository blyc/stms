package com.hp.student.bo;

import java.io.Serializable;

/**
 * @Author: boy
 * @Date: 2018/08/15
 * @Description:
 */
public class StudentRearchBo implements Serializable {
    private Long cid;
    private Long mid;
    private String grade;
    private String cname;
    private String jsname;
    private String bzrname;


    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
