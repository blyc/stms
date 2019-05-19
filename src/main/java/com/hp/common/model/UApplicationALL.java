package com.hp.common.model;

import java.util.Date;


public class UApplicationALL {

    /*主键*/
    private Long aid;
    /*申请日期*/
    private Date adate;
    /*申请人*/
    private String Applicant;
    /*订购校区*/
    private String campus;
    /*邮编*/
    private Long Zipcode;
    /*送货地址*/
    private String address;
    /*收件人1*/
    private String Addressee1;
    /*电话1*/
    private String Telephone1;
    /*手机1*/
    private String Tel1;
    /*收件人2*/
    private String Addressee2;
    /*电话2*/
    private String Telephone2;
    /*手机2*/
    private String Tel2;
    /*使用班级*/
    private String classname;
    /*数量*/
    private Long Number;

    private String edition;
    private Long price;
    private String img;
    private Long state;
    private  String Remarks;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Date getAdate() {
        return adate;
    }

    public void setAdate(Date adate) {
        this.adate = adate;
    }

    public String getApplicant() {
        return Applicant;
    }

    public void setApplicant(String applicant) {
        Applicant = applicant;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public Long getZipcode() {
        return Zipcode;
    }

    public void setZipcode(Long zipcode) {
        Zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressee1() {
        return Addressee1;
    }

    public void setAddressee1(String addressee1) {
        Addressee1 = addressee1;
    }

    public String getTelephone1() {
        return Telephone1;
    }

    public void setTelephone1(String telephone1) {
        Telephone1 = telephone1;
    }

    public String getTel1() {
        return Tel1;
    }

    public void setTel1(String tel1) {
        Tel1 = tel1;
    }

    public String getAddressee2() {
        return Addressee2;
    }

    public void setAddressee2(String addressee2) {
        Addressee2 = addressee2;
    }

    public String getTelephone2() {
        return Telephone2;
    }

    public void setTelephone2(String telephone2) {
        Telephone2 = telephone2;
    }

    public String getTel2() {
        return Tel2;
    }

    public void setTel2(String tel2) {
        Tel2 = tel2;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Long getNumber() {
        return Number;
    }

    public void setNumber(Long number) {
        Number = number;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
