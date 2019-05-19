package com.hp.common.model;


public class Udetails {
    private Long did;
    private Long mid;
    private Long price;
    private Long total;

    private UMajor uMajor;

    public UMajor getuMajor() {
        return uMajor;
    }

    public void setuMajor(UMajor uMajor) {
        this.uMajor = uMajor;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


}
