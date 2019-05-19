package com.hp.exam.bo;

import java.io.Serializable;

/**
 * Created by 晏利花 on 2018/4/14.
 */
public class ExportStudentExambo implements Serializable{
    private Long ceid;
    private String sname;
    private Double examgrade;

    public Long getCeid() {
        return ceid;
    }

    public void setCeid(Long ceid) {
        this.ceid = ceid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Double getExamgrade() {
        return examgrade;
    }

    public void setExamgrade(Double examgrade) {
        this.examgrade = examgrade;
    }

}
