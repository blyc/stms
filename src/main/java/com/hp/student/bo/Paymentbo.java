package com.hp.student.bo;

import java.util.Date;

/**
 * @Author: boy
 * @Date: 2018/03/03
 * @Description:
 */
public class Paymentbo {
    private Long sid;
    private Long record;
    private String name;
    private Date recodeDate;

    public Long getRecord() {
        return record;
    }

    public void setRecord(Long record) {
        this.record = record;
    }

    public Date getRecodeDate() {
        return recodeDate;
    }

    public void setRecodeDate(Date recodeDate) {
        this.recodeDate = recodeDate;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
