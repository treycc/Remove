package com.jdp.hls.model.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/7/31 0031 下午 3:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterCompanyDetail implements Serializable {
    private String HouseId;
    private String Address;
    private String EnterpriseName;
    private double Longitude;
    private double Latitude;
    private boolean IsEvaluated;
    private boolean IsMeasured;
    private boolean IsAssetEvaluator;
    private String Remark;
    private List<ImgInfo> HouseFiles;
    private int PersonCount;
    private int buildingType;

    public boolean isAssetEvaluator() {
        return IsAssetEvaluator;
    }

    public void setAssetEvaluator(boolean assetEvaluator) {
        IsAssetEvaluator = assetEvaluator;
    }

    public String getEnterpriseName() {
        return null == EnterpriseName ? "" : EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public int getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(int buildingType) {
        this.buildingType = buildingType;
    }

    public String getHouseId() {
        return null == HouseId ? "" : HouseId;
    }

    public void setHouseId(String houseId) {
        this.HouseId = houseId;
    }

    public String getAddress() {
        return null == Address ? "" : Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public boolean isEvaluated() {
        return IsEvaluated;
    }

    public void setEvaluated(boolean evaluated) {
        IsEvaluated = evaluated;
    }

    public boolean isMeasured() {
        return IsMeasured;
    }

    public void setMeasured(boolean measured) {
        IsMeasured = measured;
    }

    public String getRemark() {
        return null == Remark ? "" : Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public List<ImgInfo> getHouseFiles() {
        return HouseFiles;
    }

    public void setHouseFiles(List<ImgInfo> houseFiles) {
        HouseFiles = houseFiles;
    }

    public int getPersonCount() {
        return PersonCount;
    }

    public void setPersonCount(int personCount) {
        PersonCount = personCount;
    }
}
