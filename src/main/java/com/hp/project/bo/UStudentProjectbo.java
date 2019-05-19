package com.hp.project.bo;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by 金梦杰 on 2018/4/8/008.
 */
public class UStudentProjectbo implements Serializable {

    private Long spid;
    private String uname;
    private BigDecimal rate;
    private Double drate;
    private Long vsubmit;
    private String strvsubmit;
    private Long vpass;
    private String strvpass;
    private String vaddr;
    private String vtime;
    private String codeaddr;
    private BigDecimal coderate;
    private Double dcoderate;
    private String remark;


    public Long getSpid() {
        return spid;
    }

    public void setSpid(Long spid) {
        this.spid = spid;
    }

    @ExcelField(title = "姓名", align = 2, sort = 1)
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @ExcelField(title = "项目视频提交率", align = 2, sort = 6, type = 1)
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @ExcelField(title = "项目视频提交率", align = 2, sort = 6, type = 2)
    public Double getDrate() {
        return drate;
    }

    public void setDrate(Double drate) {
        this.drate = drate;
    }

    public Long getVsubmit() {
        return vsubmit;
    }

    public void setVsubmit(Long vsubmit) {
        this.vsubmit = vsubmit;
    }

    @ExcelField(title = "视频是否提交", align = 2, sort = 2)
    public String getStrvsubmit() {
        return strvsubmit;
    }

    public void setStrvsubmit(String strvsubmit) {
        this.strvsubmit = strvsubmit;
    }


    public Long getVpass() {
        return vpass;
    }

    public void setVpass(Long vpass) {
        this.vpass = vpass;
    }

    @ExcelField(title = "视频是否合格", align = 2, sort = 5)
    public String getStrvpass() {
        return strvpass;
    }

    public void setStrvpass(String strvpass) {
        this.strvpass = strvpass;
    }

    @ExcelField(title = "项目讲解提交视频地址", align = 2, sort = 3)
    public String getVaddr() {
        return vaddr;
    }

    public void setVaddr(String vaddr) {
        this.vaddr = vaddr;
    }

    @ExcelField(title = "时长", align = 2, sort = 4)
    public String getVtime() {
        return vtime;
    }

    public void setVtime(String vtime) {
        this.vtime = vtime;
    }

    @ExcelField(title = "项目代码提交地址", align = 2, sort = 7)
    public String getCodeaddr() {
        return codeaddr;
    }

    public void setCodeaddr(String codeaddr) {
        this.codeaddr = codeaddr;
    }

    @ExcelField(title = "项目代码完成率", align = 2, sort = 8,type = 1)
    public BigDecimal getCoderate() {
        return coderate;
    }

    public void setCoderate(BigDecimal coderate) {
        this.coderate = coderate;
    }

    @ExcelField(title = "项目代码完成率", align = 2, sort = 8,type = 2)
    public Double getDcoderate() {
        return dcoderate;
    }

    public void setDcoderate(Double dcoderate) {
        this.dcoderate = dcoderate;
    }

    @ExcelField(title = "备注", align = 2, sort = 9)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
