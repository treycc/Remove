package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2019/3/8 0008 上午 10:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedItem {
    private int Id;
    private String CertNum;
    private String Address;
    private String Remark;
    private int CertType;

    public int getCertType() {
        return CertType;
    }

    public void setCertType(int certType) {
        CertType = certType;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCertNum() {
        return null == CertNum ? "" : CertNum;
    }

    public void setCertNum(String certNum) {
        CertNum = certNum;
    }

    public String getAddress() {
        return null == Address ? "" : Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRemark() {
        return null == Remark ? "" : Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
