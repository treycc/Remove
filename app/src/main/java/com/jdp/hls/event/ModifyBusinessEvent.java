package com.jdp.hls.event;

/**
 * Description:TODO
 * Create Time:2018/10/26 0026 下午 2:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyBusinessEvent {
    private String buildingId;
    private String cusCode;
    private String realName;
    private String mobile;
    private String address;
    private int buildingType;

    public int getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(int buildingType) {
        this.buildingType = buildingType;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
