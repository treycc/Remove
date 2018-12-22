package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/27 0027 下午 4:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalMapping {

    private int MapId;
    private String HouseId;
    private int MapperId;
    private String RealName;
    private String PropertyCertTotalArea;
    private String LandCertTotalArea;
    private String TotalNotRecordArea;
    private String SimpleHouseArea;
    private String TanArea;
    private String ShedArea;
    private String MapDate;
    private String Remark;
    private String CompanyName;
    private String Address;
    private String Confirmer;
    private String TotalBuildingArea;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public String getTotalBuildingArea() {
        return null == TotalBuildingArea ? "" : TotalBuildingArea;
    }

    public void setTotalBuildingArea(String totalBuildingArea) {
        TotalBuildingArea = totalBuildingArea;
    }

    public String getConfirmer() {
        return Confirmer;
    }

    public void setConfirmer(String confirmer) {
        Confirmer = confirmer;
    }

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }

    public int getMapId() {
        return MapId;
    }

    public void setMapId(int MapId) {
        this.MapId = MapId;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String HouseId) {
        this.HouseId = HouseId;
    }

    public int getMapperId() {
        return MapperId;
    }

    public void setMapperId(int MapperId) {
        this.MapperId = MapperId;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getPropertyCertTotalArea() {
        return PropertyCertTotalArea;
    }

    public void setPropertyCertTotalArea(String PropertyCertTotalArea) {
        this.PropertyCertTotalArea = PropertyCertTotalArea;
    }

    public String getLandCertTotalArea() {
        return LandCertTotalArea;
    }

    public void setLandCertTotalArea(String LandCertTotalArea) {
        this.LandCertTotalArea = LandCertTotalArea;
    }

    public String getTotalNotRecordArea() {
        return TotalNotRecordArea;
    }

    public void setTotalNotRecordArea(String TotalNotRecordArea) {
        this.TotalNotRecordArea = TotalNotRecordArea;
    }

    public String getSimpleHouseArea() {
        return SimpleHouseArea;
    }

    public void setSimpleHouseArea(String SimpleHouseArea) {
        this.SimpleHouseArea = SimpleHouseArea;
    }

    public String getTanArea() {
        return TanArea;
    }

    public void setTanArea(String TanArea) {
        this.TanArea = TanArea;
    }

    public String getShedArea() {
        return ShedArea;
    }

    public void setShedArea(String ShedArea) {
        this.ShedArea = ShedArea;
    }

    public String getMapDate() {
        return MapDate;
    }

    public void setMapDate(String MapDate) {
        this.MapDate = MapDate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }
}
