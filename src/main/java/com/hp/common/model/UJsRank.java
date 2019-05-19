package com.hp.common.model;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;

public class UJsRank implements Serializable {
    private Long lid;

    private String type;

    private String level;

    private Long subsidy;

    private static final long serialVersionUID = 1L;

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    @ExcelField(title = "岗位代码", type = 0, align = 2, sort = 1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ExcelField(title = "级别(不可重复)", type = 0, align = 2, sort = 1)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @ExcelField(title = "薪资", type = 0, align = 2, sort = 1)
    public Long getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(Long subsidy) {
        this.subsidy = subsidy;
    }
}