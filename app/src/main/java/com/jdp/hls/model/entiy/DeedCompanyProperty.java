package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/26 0026 下午 6:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyProperty {
    private String Id;
    private String EnterpriseId;
    private int PropertyUseTypeId;
    private int StructureTypeId;
    private String CertNum;
    private double Area;
    private String PropertyUseTypeName;
    private String StructureTypeName;
    private String Address;
    private String Remark;
    private String PropertyUse;
    private boolean IsAllowEdit;
    private List<ImgInfo>Files;

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getPropertyUse() {
        return PropertyUse;
    }

    public void setPropertyUse(String propertyUse) {
        PropertyUse = propertyUse;
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

    public double getArea() {
        return Area;
    }

    public void setArea(double area) {
        Area = area;
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
}
