package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/9/13 0013 上午 11:46
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AirPhotoItem {
    private String HouseId;
    private String address;
    private int BuildingType;
    private String RealName;
    private String MobilePhone;
    private int StatusId;
    private String StatusDesc;
    private String LandUseTypeName;
    private int StructureTypeName;
    private int Layer;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
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

    public String getLandUseTypeName() {
        return LandUseTypeName;
    }

    public void setLandUseTypeName(String landUseTypeName) {
        LandUseTypeName = landUseTypeName;
    }

    public int getStructureTypeName() {
        return StructureTypeName;
    }

    public void setStructureTypeName(int structureTypeName) {
        StructureTypeName = structureTypeName;
    }

    public int getLayer() {
        return Layer;
    }

    public void setLayer(int layer) {
        Layer = layer;
    }
}
