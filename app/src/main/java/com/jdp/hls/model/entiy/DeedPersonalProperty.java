package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/20 0020 下午 8:03
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedPersonalProperty {
    private int Id;
    private String HouseId;
    private int PropertyUseTypeId;
    private int StructureTypeId;
    private String CertNum;
    private double TotalArea;
    private double ShareArea;
    private String Address;
    private String StructureTypeName;
    private String PropertyUseTypeName;
    private String Remark;
    private List<ImgInfo> Files;
    private String DeleteFileIDs;
    private boolean IsAllowEdit;

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
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

    public String getCertNum() {
        return CertNum;
    }

    public void setCertNum(String certNum) {
        CertNum = certNum;
    }

    public double getTotalArea() {
        return TotalArea;
    }

    public void setTotalArea(double totalArea) {
        TotalArea = totalArea;
    }

    public double getShareArea() {
        return ShareArea;
    }

    public void setShareArea(double shareArea) {
        ShareArea = shareArea;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getStructureTypeName() {
        return StructureTypeName;
    }

    public void setStructureTypeName(String structureTypeName) {
        StructureTypeName = structureTypeName;
    }

    public String getPropertyUseTypeName() {
        return PropertyUseTypeName;
    }

    public void setPropertyUseTypeName(String propertyUseTypeName) {
        PropertyUseTypeName = propertyUseTypeName;
    }
}
