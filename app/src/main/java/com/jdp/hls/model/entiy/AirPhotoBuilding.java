package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/9/13 0013 下午 6:48
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AirPhotoBuilding implements Serializable{

    /**
     * BuildingId : 9b188978-db9b-4c29-a15e-e7f7ad2d3fe7
     * SysCode :
     * RealName :
     * EnterpriseName : afd
     * MobilePhone :
     * Address : add
     * StatusId : 4
     * CreateDatetime : 2018/9/4 星期二 下午 2:42:23
     * ProjectName : 三垟湿地二期
     * ProjectAddress : 浙江省温州市龙湾区三垟湿地，大龙街道200号-354号
     */

    private String BuildingId;
    private int BuilldingType;
    private String SysCode;
    private String CusCode;
    private String RealName;
    private String EnterpriseName;
    private String MobilePhone;
    private String Address;
    private int StatusId;
    private String CreateDatetime;
    private String ProjectName;
    private String ProjectAddress;

    public String getCusCode() {
        return CusCode;
    }

    public void setCusCode(String cusCode) {
        CusCode = cusCode;
    }

    public int getBuilldingType() {
        return BuilldingType;
    }

    public void setBuilldingType(int builldingType) {
        BuilldingType = builldingType;
    }

    public String getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(String BuildingId) {
        this.BuildingId = BuildingId;
    }

    public String getSysCode() {
        return SysCode;
    }

    public void setSysCode(String SysCode) {
        this.SysCode = SysCode;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String EnterpriseName) {
        this.EnterpriseName = EnterpriseName;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int StatusId) {
        this.StatusId = StatusId;
    }

    public String getCreateDatetime() {
        return CreateDatetime;
    }

    public void setCreateDatetime(String CreateDatetime) {
        this.CreateDatetime = CreateDatetime;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getProjectAddress() {
        return ProjectAddress;
    }

    public void setProjectAddress(String ProjectAddress) {
        this.ProjectAddress = ProjectAddress;
    }
}
