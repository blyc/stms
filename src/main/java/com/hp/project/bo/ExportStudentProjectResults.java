package com.hp.project.bo;

import java.io.Serializable;

/**
 * Created by 金梦杰 on 2018/4/16/016.
 */
public class ExportStudentProjectResults implements Serializable {

    private String name;
    private Long rate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }
}
