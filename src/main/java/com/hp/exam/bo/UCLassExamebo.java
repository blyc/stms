package com.hp.exam.bo;

import java.io.Serializable;

/**
 * @Author: boy
 * @Date: 2018/03/26
 * @Description:
 */
public class UCLassExamebo implements Serializable {
    private Long ceid;
    private Double hightgrade;
    private Double lowgrade;
    private Double avggrade;
    private Long qualified;
    private Double qualifiedrate;

    public Long getCeid() {
        return ceid;
    }

    public void setCeid(Long ceid) {
        this.ceid = ceid;
    }

    public Double getHightgrade() {
        return hightgrade;
    }

    public void setHightgrade(Double hightgrade) {
        this.hightgrade = hightgrade;
    }

    public Double getLowgrade() {
        return lowgrade;
    }

    public void setLowgrade(Double lowgrade) {
        this.lowgrade = lowgrade;
    }

    public Double getAvggrade() {
        return avggrade;
    }

    public void setAvggrade(Double avggrade) {
        this.avggrade = avggrade;
    }

    public Long getQualified() {
        return qualified;
    }

    public void setQualified(Long qualified) {
        this.qualified = qualified;
    }

    public Double getQualifiedrate() {
        return qualifiedrate;
    }

    public void setQualifiedrate(Double qualifiedrate) {
        this.qualifiedrate = qualifiedrate;
    }
}
