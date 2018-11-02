package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/20 0020 下午 8:03
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedPersonalLand {

    private int Id;
    private String HouseId;
    private String CertNum;
    private int LandNatureId;
    private int LandUseTypeId;
    private double TotalArea;
    private double BuildOccupyArea;
    private String LandNatureTypeName;
    private String LandUseTypeName;
    private String Address;
    private String DeleteFileIDs;
    private String remark;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeleteFileIDs() {
        return DeleteFileIDs;
    }

    public void setDeleteFileIDs(String deleteFileIDs) {
        DeleteFileIDs = deleteFileIDs;
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

    public int getLandNatureId() {
        return LandNatureId;
    }

    public void setLandNatureId(int landNatureId) {
        LandNatureId = landNatureId;
    }

    public int getLandUseTypeId() {
        return LandUseTypeId;
    }

    public void setLandUseTypeId(int landUseTypeId) {
        LandUseTypeId = landUseTypeId;
    }

    public double getTotalArea() {
        return TotalArea;
    }

    public void setTotalArea(double totalArea) {
        TotalArea = totalArea;
    }

    public double getBuildOccupyArea() {
        return BuildOccupyArea;
    }

    public void setBuildOccupyArea(double buildOccupyArea) {
        BuildOccupyArea = buildOccupyArea;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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
