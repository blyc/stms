package com.hp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class VSummaryExam implements Serializable {
    private String summaryTime;
    private Long ccid;
    private BigDecimal summaryExam;
    private VClass vClass;
    private static final long serialVersionUID = 1L;

    public String getSummaryTime() {
        return summaryTime;
    }

    public void setSummaryTime(String summaryTime) {
        this.summaryTime = summaryTime;
    }

    public Long getCcid() {
        return ccid;
    }

    public void setCcid(Long ccid) {
        this.ccid = ccid;
    }

    public BigDecimal getSummaryExam() {
        return summaryExam;
    }

    public void setSummaryExam(BigDecimal summaryExam) {
        this.summaryExam = summaryExam;
    }

    public VClass getvClass() {
        return vClass;
    }

    public void setvClass(VClass vClass) {
        this.vClass = vClass;
    }
}