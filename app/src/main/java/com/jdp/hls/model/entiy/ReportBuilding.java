package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/12/7 0007 下午 4:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportBuilding {
    private String BuildingId;
    private String SysCode;
    private int BuildingType;
    private String RealName;
    private String MobilePhone;
    private int StatusId;
    private String StatusDesc;
    private String Address;
    private String CusCode;

    public String getBuildingId() {
        return null == BuildingId ? "" : BuildingId;
    }

    public void setBuildingId(String buildingId) {
        BuildingId = buildingId;
    }

    public String getSysCode() {
        return null == SysCode ? "" : SysCode;
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
        return null == RealName ? "" : RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getMobilePhone() {
        return null == MobilePhone ? "" : MobilePhone;
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
        return null == StatusDesc ? "" : StatusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        StatusDesc = statusDesc;
    }

    public String getAddress() {
        return null == Address ? "" : Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCusCode() {
        return null == CusCode ? "" : CusCode;
    }

    public void setCusCode(String cusCode) {
        CusCode = cusCode;
    }
}
