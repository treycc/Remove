package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/10/10 0010 下午 3:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AirPhotoItem {


    /**
     * AirCheckId : 3
     * BuildingId : 83faf037-6ee7-4a1f-9c0e-039948b90f26
     * BuilldingType : 0
     * BuildingStructureType : 1
     * Layer : 3
     * AirCheckProId : 2
     * Reason : 123
     * ResultType : 0
     * CusCode :
     * Address : 测试地址
     * EnterpriseName :
     * MobilePhone : 18888888888
     * RealName : Bob
     * PropertyUseTypeId : 0
     * ProjectName : 三垟湿地二期
     * ProjectAddress : 浙江省温州市龙湾区三垟湿地，大龙街道200号-354号
     * PropertyUseTypeName :
     * BuildingStructureTypeName : 钢筋混凝土
     * IsAllowEdit : false
     */

    private int AirCheckId;
    private String BuildingId;
    private int BuilldingType;
    private int BuildingStructureType;
    private int Layer;
    private int AirCheckProId;
    private String Reason;
    private int ResultType;
    private String CusCode;
    private String Address;
    private String EnterpriseName;
    private String MobilePhone;
    private String RealName;
    private String Remark;
    private int PropertyUseType;
    private String ProjectName;
    private String ProjectAddress;
    private String PropertyUseTypeName;
    private String BuildingStructureTypeName;
    private boolean IsAllowEdit;
    private List<ImgInfo> AppraiseFiles;
    private List<ImgInfo> Files;
    private List<UnRecordBuilding> Items;

    public int getPropertyUseType() {
        return PropertyUseType;
    }

    public void setPropertyUseType(int propertyUseType) {
        PropertyUseType = propertyUseType;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public List<UnRecordBuilding> getItems() {
        return Items;
    }

    public void setItems(List<UnRecordBuilding> items) {
        Items = items;
    }

    public int getAirCheckId() {
        return AirCheckId;
    }

    public void setAirCheckId(int AirCheckId) {
        this.AirCheckId = AirCheckId;
    }

    public String getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(String BuildingId) {
        this.BuildingId = BuildingId;
    }

    public int getBuilldingType() {
        return BuilldingType;
    }

    public void setBuilldingType(int BuilldingType) {
        this.BuilldingType = BuilldingType;
    }

    public int getBuildingStructureType() {
        return BuildingStructureType;
    }

    public void setBuildingStructureType(int BuildingStructureType) {
        this.BuildingStructureType = BuildingStructureType;
    }

    public int getLayer() {
        return Layer;
    }

    public void setLayer(int Layer) {
        this.Layer = Layer;
    }

    public int getAirCheckProId() {
        return AirCheckProId;
    }

    public void setAirCheckProId(int AirCheckProId) {
        this.AirCheckProId = AirCheckProId;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public int getResultType() {
        return ResultType;
    }

    public void setResultType(int ResultType) {
        this.ResultType = ResultType;
    }

    public String getCusCode() {
        return CusCode;
    }

    public void setCusCode(String CusCode) {
        this.CusCode = CusCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
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

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
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

    public String getPropertyUseTypeName() {
        return PropertyUseTypeName;
    }

    public void setPropertyUseTypeName(String PropertyUseTypeName) {
        this.PropertyUseTypeName = PropertyUseTypeName;
    }

    public String getBuildingStructureTypeName() {
        return BuildingStructureTypeName;
    }

    public void setBuildingStructureTypeName(String BuildingStructureTypeName) {
        this.BuildingStructureTypeName = BuildingStructureTypeName;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public List<ImgInfo> getAppraiseFiles() {
        return AppraiseFiles;
    }

    public void setAppraiseFiles(List<ImgInfo> appraiseFiles) {
        AppraiseFiles = appraiseFiles;
    }

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }
}
