package com.jdp.hls.model.entiy.resultdata;

import com.jdp.hls.model.entiy.ImgInfo;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2019/1/30 0030 下午 4:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterResult {
    private String RealName;
    private String MobilePhone;
    private String HouseId;
    private int BuildingType;
    private List<ImgInfo> HouseFiles;

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

    public String getHouseId() {
        return null == HouseId ? "" : HouseId;
    }

    public void setHouseId(String houseId) {
        this.HouseId = houseId;
    }

    public int getBuildingType() {
        return BuildingType;
    }

    public void setBuildingType(int buildingType) {
        this.BuildingType = buildingType;
    }

    public List<ImgInfo> getHouseFiles() {
        return HouseFiles;
    }

    public void setHouseFiles(List<ImgInfo> houseFiles) {
        HouseFiles = houseFiles;
    }
}
