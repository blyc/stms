package com.hp.common.model;

import java.io.Serializable;
import java.util.Date;


public class UApplication  implements Serializable{
    private Long aid;

    private Date adate;

    private String applicant;

    private Long cid;

    private String campus;

    private Long zipcode;

    private String address;

    private String addressee1;

    private String telephone1;

    private String tel1;

    private String addressee2;

    private String telephone2;

    private String tel2;

    private Long mid;

    private Long number;

    private Long price;

    private String img;

    private String remarks;

    private Long state;

    private  UMajor uMajor;

    private static final long serialVersionUID = 1L;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

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
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public Long getZipcode() {
        return zipcode;
    }

    public void setZipcode(Long zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressee1() {
        return addressee1;
    }

    public void setAddressee1(String addressee1) {
        this.addressee1 = addressee1;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getAddressee2() {
        return addressee2;
    }

    public void setAddressee2(String addressee2) {
        this.addressee2 = addressee2;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public UMajor getuMajor() {
        return uMajor;
    }

    public void setuMajor(UMajor uMajor) {
        this.uMajor = uMajor;
    }
}
