package com.jdp.hls.model.entiy;

import org.greenrobot.greendao.annotation.Id;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/26 0026 下午 6:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyImmovable {
    private String Id;
    private String EnterpriseId;
    private String CertNum;
    private int LandUseTypeId;
    private int LandNatureTypeId;
    private int PropertyUseTypeId;
    private int StructureTypeId;
    private double LandArea;
    private double PropertyArea;

    public double getMu() {
        return Mu;
    }

    public void setMu(double mu) {
        Mu = mu;
    }

    private double Mu;
    private String Address;
    private String LandNatureTypeName;
    private String LandUseTypeName;
    private String PropertyUseTypeName;
    private String StructureTypeName;
    private String Remark;
    private String LandUse;
    private String PropertyUse;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public String getLandUse() {
        return LandUse;
    }

    public void setLandUse(String landUse) {
        LandUse = landUse;
    }

    public String getPropertyUse() {
        return PropertyUse;
    }

    public void setPropertyUse(String propertyUse) {
        PropertyUse = propertyUse;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEnterpriseId() {
        return EnterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        EnterpriseId = enterpriseId;
    }

    public String getCertNum() {
        return CertNum;
    }

    public void setCertNum(String certNum) {
        CertNum = certNum;
    }

    public int getLandUseTypeId() {
        return LandUseTypeId;
    }

    public void setLandUseTypeId(int landUseTypeId) {
        LandUseTypeId = landUseTypeId;
    }

    public int getLandNatureTypeId() {
        return LandNatureTypeId;
    }

    public void setLandNatureTypeId(int landNatureTypeId) {
        LandNatureTypeId = landNatureTypeId;
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

    public double getLandArea() {
        return LandArea;
    }

    public void setLandArea(double landArea) {
        LandArea = landArea;
    }

    public double getPropertyArea() {
        return PropertyArea;
    }

    public void setPropertyArea(double propertyArea) {
        PropertyArea = propertyArea;
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

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }
}
