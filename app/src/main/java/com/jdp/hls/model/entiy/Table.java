package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/9/19 0019 下午 1:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Table implements Serializable {
    private String BuildingId;
    private String SysCode;
    private int BuildingType;
    private String RealName;
    private String MobilePhone;
    private int StatusId;
    private String StatusDesc;
    private String Address;
    private boolean IsFlowBack;
    private boolean IsBanned;
    private String PropertyArea;
    private String PayTypeName;
    private String CreateDatetime;

    public String getCreateDatetime() {
        return CreateDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        CreateDatetime = createDatetime;
    }

    public String getPayTypeName() {
        return PayTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        PayTypeName = payTypeName;
    }

    public String getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(String buildingId) {
        BuildingId = buildingId;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public boolean isFlowBack() {
        return IsFlowBack;
    }

    public void setFlowBack(boolean flowBack) {
        IsFlowBack = flowBack;
    }

    public boolean isBanned() {
        return IsBanned;
    }

    public void setBanned(boolean banned) {
        IsBanned = banned;
    }

    public String getPropertyArea() {
        return PropertyArea;
    }

    public void setPropertyArea(String propertyArea) {
        PropertyArea = propertyArea;
    }
}
