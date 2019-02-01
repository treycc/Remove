package com.jdp.hls.model.entiy.resultdata;

import com.jdp.hls.model.entiy.ImgInfo;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2019/1/30 0030 下午 4:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterDetail {
    private String houseId;
    private int buildingType;
    private List<ImgInfo> HouseFiles;

    public String getHouseId() {
        return null == houseId ? "" : houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public int getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(int buildingType) {
        this.buildingType = buildingType;
    }

    public List<ImgInfo> getHouseFiles() {
        return HouseFiles;
    }

    public void setHouseFiles(List<ImgInfo> houseFiles) {
        HouseFiles = houseFiles;
    }
}
