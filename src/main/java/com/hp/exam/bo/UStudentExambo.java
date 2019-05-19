package com.hp.exam.bo;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * @Author: boy
 * @Date: 2018/03/25
 * @Description:
 */
public class UStudentExambo implements Serializable {

    private Long useid;

    private String userName;

    private Double examgrade;

    private String eaddr;

    public Long getUseid() {
        return useid;
    }

    public void setUseid(Long useid) {
        this.useid = useid;
    }

    @ExcelField(title = "姓名", align = 2, sort = 1)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ExcelField(title = "成绩", align = 2, sort = 2)
    public Double getExamgrade() {
        return examgrade;
    }

    public void setExamgrade(Double examgrade) {
        this.examgrade = examgrade;
    }

    @ExcelField(title = "试卷地址", align = 2, sort = 3)
    public String getEaddr() {
        return eaddr;
    }

    public void setEaddr(String eaddr) {
        this.eaddr = eaddr;
    }
}
