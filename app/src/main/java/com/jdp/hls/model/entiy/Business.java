package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/9/7 0007 上午 11:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Business implements Serializable{
    private String HouseId;
    private String SysCode;
    private int BuildingType;
    private String RealName;
    private String HouseAddress;
    private String MobilePhone;
    private int StatusId;
    private String StatusDesc;
    private boolean IsFlowBack;
    private boolean HasLongitudeAndLatitude;

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

    public int getBuildingType() {
        return BuildingType;
    }

    public void setBuildingType(int buildingType) {
        BuildingType = buildingType;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getHouseAddress() {
        return HouseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        HouseAddress = houseAddress;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public String getStatusDesc() {
        return StatusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        StatusDesc = statusDesc;
    }

    public boolean isFlowBack() {
        return IsFlowBack;
    }

    public void setFlowBack(boolean flowBack) {
        IsFlowBack = flowBack;
    }

    public boolean isHasLongitudeAndLatitude() {
        return HasLongitudeAndLatitude;
    }

    public void setHasLongitudeAndLatitude(boolean hasLongitudeAndLatitude) {
        HasLongitudeAndLatitude = hasLongitudeAndLatitude;
    }
}
