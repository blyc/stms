package com.hp.interview.bo;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/4/20.
 */
public class InterViewBo implements Serializable {

    private Long sid;

    private Long infoid;

    private Double comrate;

    public Long getInfoid() {
        return infoid;
    }

    public void setInfoid(Long infoid) {
        this.infoid = infoid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Double getComrate() {
        return comrate;
    }

    public void setComrate(Double comrate) {
        this.comrate = comrate;
    }
}
