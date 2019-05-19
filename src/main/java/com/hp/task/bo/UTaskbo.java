package com.hp.task.bo;

import java.io.Serializable;

/**
 * Created by Dragon on 2018/4/20 0020.
 */
public class UTaskbo implements Serializable {
    private Long tid;
    private Long qualified;
    private Double qualifiedrate;

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
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
