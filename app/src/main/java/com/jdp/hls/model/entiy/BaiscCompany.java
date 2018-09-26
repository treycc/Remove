package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/9/19 0019 下午 8:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BaiscCompany {

    private String HouseId;
    private String SysCode;
    private String EnterpriseName;
    private String Address;
    private int StatusId;

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

    public String getSysCode() {
        return SysCode;
    }

    public void setSysCode(String sysCode) {
        SysCode = sysCode;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }
}
