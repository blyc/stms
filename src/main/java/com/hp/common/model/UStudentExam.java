package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;

public class UStudentExam implements Serializable {
    private Long useid;

    private Long cid;

    private Long ccid;

    private Long sid;

    private Double examgrade;

    private String eaddr;

    private Long ceid;

    private Date registerexam;

    private UStudent uStudent;

    private UClass uClass;

    private UClassExam uClassExam ;

    private static final long serialVersionUID = 1L;

    public Long getUseid() {
        return useid;
    }

    public void setUseid(Long useid) {
        this.useid = useid;
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

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Double getExamgrade() {
        return examgrade;
    }

    public void setExamgrade(Double examgrade) {
        this.examgrade = examgrade;
    }

    public String getEaddr() {
        return eaddr;
    }

    public void setEaddr(String eaddr) {
        this.eaddr = eaddr;
    }

    public Long getCeid() {
        return ceid;
    }

    public UStudent getuStudent() {
        return uStudent;
    }

    public void setuStudent(UStudent uStudent) {
        this.uStudent = uStudent;
    }

    public UClass getuClass() {
        return uClass;
    }

    public void setuClass(UClass uClass) {
        this.uClass = uClass;
    }

    public UClassExam getuClassExam() {
        return uClassExam;
    }

    public void setuClassExam(UClassExam uClassExam) {
        this.uClassExam = uClassExam;
    }

    public void setCeid(Long ceid) {
        this.ceid = ceid;
    }

    public Date getRegisterexam() {
        return registerexam;
    }

    public void setRegisterexam(Date registerexam) {
        this.registerexam = registerexam;
    }


}