package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/20 0020 下午 8:03
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedPersonalImmovable {
    private int Id;
    private String HouseId;
    private String CertNum;
    private int LandNatureTypeId;
    private int LandUseTypeId;
    private int PropertyUseTypeId;
    private int StructureTypeId;
    private double HouseTotalArea;
    private double HouseShareArea;
    private double LandTotalArea;
    private double BuildOccupyArea;
    private String Address;
    private String LandNatureTypeName;
    private String LandUseTypeName;
    private String PropertyUseTypeName;
    private String StructureTypeName;
    private String DeleteFileIDs;
    private String Remark;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

    public String getCertNum() {
        return CertNum;
    }

    public void setCertNum(String certNum) {
        CertNum = certNum;
    }

    public int getLandNatureTypeId() {
        return LandNatureTypeId;
    }

    public void setLandNatureTypeId(int landNatureTypeId) {
        LandNatureTypeId = landNatureTypeId;
    }

    public int getLandUseTypeId() {
        return LandUseTypeId;
    }

    public void setLandUseTypeId(int landUseTypeId) {
        LandUseTypeId = landUseTypeId;
    }

    public int getPropertyUseTypeId() {
        return PropertyUseTypeId;
    }

    public void setPropertyUseTypeId(int propertyUseTypeId) {
        PropertyUseTypeId = propertyUseTypeId;
    }

    public int getStructureTypeId() {
        return StructureTypeId;
    }

    public void setStructureTypeId(int structureTypeId) {
        StructureTypeId = structureTypeId;
    }

    public double getHouseTotalArea() {
        return HouseTotalArea;
    }

    public void setHouseTotalArea(double houseTotalArea) {
        HouseTotalArea = houseTotalArea;
    }

    public double getHouseShareArea() {
        return HouseShareArea;
    }

    public void setHouseShareArea(double houseShareArea) {
        HouseShareArea = houseShareArea;
    }

    public double getLandTotalArea() {
        return LandTotalArea;
    }

    public void setLandTotalArea(double landTotalArea) {
        LandTotalArea = landTotalArea;
    }

    public double getBuildOccupyArea() {
        return BuildOccupyArea;
    }

    public void setBuildOccupyArea(double buildOccupyArea) {
        BuildOccupyArea = buildOccupyArea;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLandNatureTypeName() {
        return LandNatureTypeName;
    }

    public void setLandNatureTypeName(String landNatureTypeName) {
        LandNatureTypeName = landNatureTypeName;
    }

    public String getLandUseTypeName() {
        return LandUseTypeName;
    }

    public void setLandUseTypeName(String landUseTypeName) {
        LandUseTypeName = landUseTypeName;
    }

    public String getPropertyUseTypeName() {
        return PropertyUseTypeName;
    }

    public void setPropertyUseTypeName(String propertyUseTypeName) {
        PropertyUseTypeName = propertyUseTypeName;
    }

    public String getStructureTypeName() {
        return StructureTypeName;
    }

    public void setStructureTypeName(String structureTypeName) {
        StructureTypeName = structureTypeName;
    }

    public String getDeleteFileIDs() {
        return DeleteFileIDs;
    }

    public void setDeleteFileIDs(String deleteFileIDs) {
        DeleteFileIDs = deleteFileIDs;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }
}
