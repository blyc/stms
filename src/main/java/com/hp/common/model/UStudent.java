package com.hp.common.model;

import com.hp.utils.excel.annotation.ExcelField;

import java.io.Serializable;
import java.util.Date;

public class UStudent implements Serializable {
    private Long sid;

    private String id;

    private String name;

    private String sex;

    private String nation;

    private String idcard;

    private Date birthday;

    private String inputBirthday;

    private String education;

    private String email;

    private String qq;

    private String tel;

    private String province;

    private String city;

    private String politics;

    private String address;

    private String grade;

    private String postcode;

    private String wcompany;

    private String state;

    private Long uCCid;

    private Long cid;

    private Long mid;

    private String fatherName;

    private String motherName;

    private String fatherTel;

    private String motherTel;

    private String otherName;

    private String otherTel;

    private String roomcode;

    private Date createTime;

    private Date updateTime;

    private String stage;

    private String recruiter;

    private Long createId;

    private String createName;

    private Long updateId;

    private String updateName;

    private VClass vClass;

    private UCompany uCompany;

    private UMajor uMajor;

    private String uMajorName;

    private static final long serialVersionUID = 1L;

    public Long getSid() {
        return sid;
    }
    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @ExcelField(title="校区", type=1 ,align=2, sort=1)
    public UCompany getuCompany() {
        return uCompany;
    }
    public void setuCompany(UCompany uCompany) {
        this.uCompany = uCompany;
    }


    @ExcelField(title="姓名", align=2, sort=2)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title="性别", align=2, sort=3)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    @ExcelField(title="民族", align=2, sort=4)
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
    @ExcelField(title="身份证号", align=2, sort=5)
    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @ExcelField(title="出生日期", align=2, sort=6, type = 1)
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ExcelField(title="出生日期", align=2, sort=6, type = 2)
    public String getInputBirthday() {
        return inputBirthday;
    }
    public void setInputBirthday(String inputBirthday) {
        this.inputBirthday = inputBirthday;
    }

    @ExcelField(title="学历", align=2, sort=7)
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }

    @ExcelField(title="政治面貌", align=2, sort=8)
    public String getPolitics() {
        return politics;
    }
    public void setPolitics(String politics) {
        this.politics = politics;
    }

    @ExcelField(title="手机号", align=2, sort=9)
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    @ExcelField(title="邮箱", align=2, sort=10)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @ExcelField(title="QQ号", align=2, sort=11)
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }

    @ExcelField(title="生源省", align=2, sort=12)
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    @ExcelField(title="生源市", align=2, sort=13)
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @ExcelField(title="邮编", align=2, sort=14)
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @ExcelField(title="通讯地址", align=2, sort=15)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @ExcelField(title="年级", align=2, sort=16)
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @ExcelField(title="专业", align=2, sort=17,type = 1)
    public UMajor getuMajor() {
        return uMajor;
    }
    public void setuMajor(UMajor uMajor) {
        this.uMajor = uMajor;
    }

    @ExcelField(title="专业", align=2, sort=17,type = 2)
    public String getuMajorName() {
        return uMajorName;
    }

    public void setuMajorName(String uMajorName) {
        this.uMajorName = uMajorName;
    }

    @ExcelField(title="班级", align=2, sort=18,type=1)
    public VClass getvClass() {
        return vClass;
    }

    public void setvClass(VClass vClass) {
        this.vClass = vClass;
    }

    @ExcelField(title="阶段", align=2, sort=19,type=1)
    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @ExcelField(title="状态", align=2, sort=20, type=1)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @ExcelField(title="校区", type=2 ,align=2, sort=1)
    public String getWcompany() {
        return wcompany;
    }

    public void setWcompany(String wcompany) {
        this.wcompany = wcompany;
    }

    public Long getuCCid() {
        return uCCid;
    }

    public void setuCCid(Long uCCid) {
        this.uCCid = uCCid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherTel() {
        return fatherTel;
    }

    public void setFatherTel(String fatherTel) {
        this.fatherTel = fatherTel;
    }

    public String getMotherTel() {
        return motherTel;
    }

    public void setMotherTel(String motherTel) {
        this.motherTel = motherTel;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getOtherTel() {
        return otherTel;
    }

    public void setOtherTel(String otherTel) {
        this.otherTel = otherTel;
    }

    public String getRoomcode() {
        return roomcode;
    }

    public void setRoomcode(String roomcode) {
        this.roomcode = roomcode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(String recruiter) {
        this.recruiter = recruiter;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

}