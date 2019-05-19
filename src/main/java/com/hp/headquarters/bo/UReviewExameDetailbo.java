package com.hp.headquarters.bo;

import java.math.BigDecimal;

/**
 * @Author: boy
 * @Date: 2018/11/09
 * @Description:
 */
public class UReviewExameDetailbo {
    private BigDecimal reviewGrade;

    private Boolean reviewOk;

    private String remake;

    private Long useid;


    public BigDecimal getReviewGrade() {
        return reviewGrade;
    }

    public void setReviewGrade(BigDecimal reviewGrade) {
        this.reviewGrade = reviewGrade;
    }

    public Boolean getReviewOk() {
        return reviewOk;
    }

    public void setReviewOk(Boolean reviewOk) {
        this.reviewOk = reviewOk;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public Long getUseid() {
        return useid;
    }

    public void setUseid(Long useid) {
        this.useid = useid;
    }

}
