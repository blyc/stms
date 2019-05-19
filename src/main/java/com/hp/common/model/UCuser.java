package com.hp.common.model;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;

public class UCuser implements Serializable {
    private Long id;

    private String sname;

    private String dname;

    private String name;

    private Long flg;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ExcelField(title = "校区", align = 2, sort = 1)
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
    @ExcelField(title = "部门", align = 2, sort = 2)
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
    @ExcelField(title = "姓名", align = 2, sort = 3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "标志", align = 2, sort = 4)
    public Long getFlg() {
        return flg;
    }

    public void setFlg(Long flg) {
        this.flg = flg;
    }
}