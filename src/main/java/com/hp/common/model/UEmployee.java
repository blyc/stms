package com.hp.common.model;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;
import java.util.Date;

public class UEmployee implements Serializable {
    private Long eid;

    private Long pid;

    private Long did;

    private Long cid;

    private String id;

    private String name;

    private String sex;

    private Date birthday;

    private String tel;

    private String email;

    private String shool;

    private String major;

    private String education;

    private String img;

    private String idcard;

    private String state;

    private Date entryday;

    private String strEntryday;

    private Long lid;

    private Long uerid;

    private Date createtime;

    private Date updatetime;

    private String pinyin;

    private Long userflg;

    private String uCompanyName;

    private UCompany uCompany;

    private String uDepartmentName;

    private UDepartment uDepartment;

    private String uPositionName;

    private UPosition uPosition;

    private UJsRank uJsRank;

    private static final long serialVersionUID = 1L;

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ExcelField(title = "姓名", align = 2, sort = 1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "性别", align = 2, sort = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ExcelField(title = "电话", align = 2, sort = 4)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @ExcelField(title = "邮箱", align = 2, sort = 5)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ExcelField(title = "毕业学校", align = 2, sort = 6)
    public String getShool() {
        return shool;
    }

    public void setShool(String shool) {
        this.shool = shool;
    }

    @ExcelField(title = "专业", align = 2, sort = 7)
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @ExcelField(title = "最高学历", align = 2, sort = 8)
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @ExcelField(title = "入职时间", align = 2, sort = 9, type = 1)
    public Date getEntryday() {
        return entryday;
    }

    public void setEntryday(Date entryday) {
        this.entryday = entryday;
    }

    @ExcelField(title = "入职时间", align = 2, sort = 9, type = 2)
    public String getStrEntryday() {
        return strEntryday;
    }

    public void setStrEntryday(String strEntryday) {
        this.strEntryday = strEntryday;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @ExcelField(title = "校区", align = 2, sort = 10, type = 1)
    public UCompany getuCompany() {
        return uCompany;
    }

    public void setuCompany(UCompany uCompany) {
        this.uCompany = uCompany;
    }

    @ExcelField(title = "校区", align = 2, sort = 10, type = 2)
    public String getuCompanyName() {
        return uCompanyName;
    }

    public void setuCompanyName(String uCompanyName) {
        this.uCompanyName = uCompanyName;
    }

    @ExcelField(title = "部门", align = 2, sort = 11, type = 1)
    public UDepartment getuDepartment() {
        return uDepartment;
    }

    public void setuDepartment(UDepartment uDepartment) {
        this.uDepartment = uDepartment;
    }

    @ExcelField(title = "部门", align = 2, sort = 11, type = 2)
    public String getuDepartmentName() {
        return uDepartmentName;
    }

    public void setuDepartmentName(String uDepartmentName) {
        this.uDepartmentName = uDepartmentName;
    }

    @ExcelField(title = "职位", align = 2, sort = 12, type = 1)
    public UPosition getuPosition() {
        return uPosition;
    }

    public void setuPosition(UPosition uPosition) {
        this.uPosition = uPosition;
    }

    @ExcelField(title = "职位", align = 2, sort = 12, type = 2)
    public String getuPositionName() {
        return uPositionName;
    }

    public void setuPositionName(String uPositionName) {
        this.uPositionName = uPositionName;
    }

    @ExcelField(title = "注册登录账号", align = 2, sort = 13)
    public Long getUserflg() {
        return userflg;
    }

    public void setUserflg(Long userflg) {
        this.userflg = userflg;
    }

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public UJsRank getuJsRank() {
        return uJsRank;
    }

    public void setuJsRank(UJsRank uJsRank) {
        this.uJsRank = uJsRank;
    }

    public Long getUerid() {
        return uerid;
    }

    public void setUerid(Long uerid) {
        this.uerid = uerid;
    }
}