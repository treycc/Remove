package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/30 0030 上午 10:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyMapping {

    /**
     * MapId : 1
     * EnterpriseId : b41d2663-f3b4-406b-a566-0655ca4437cd
     * MapperId : 1
     * CompanyName : 特兰普微信朋友圈
     * RealName : 杰森
     * TotalNotRecordArea : 12.00
     * TotalBuildingArea :
     * SimpleHouseArea : 1.00
     * ShedArea : 1.00
     * AppurtenanceArea : 1.00
     * MapDate : 2018/6/6 星期三 上午 12:00:00
     * Remark : 213
     * Address : 不动产证，地址改了22
     * CurrentOccupyArea : 0.00
     * IsAllowEdit : false
     */

    private int MapId;
    private String EnterpriseId;
    private String MapperId;
    private String CompanyName;
    private String RealName;
    private String TotalNotRecordArea;
    private String TotalBuildingArea;
    private String SimpleHouseArea;
    private String ShedArea;
    private String AppurtenanceArea;
    private String MapDate;
    private String Remark;
    private String Address;
    private String CurrentOccupyArea;
    private String TotalLegalArea;

    private String Confirmer;
    private String EstateCertLandArea;
    private String EstateCertPropertyArea;
    private String LandCertArea;
    private String PropertyCertArea;
    private String OtherLandArea;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public String getConfirmer() {
        return Confirmer;
    }

    public void setConfirmer(String confirmer) {
        Confirmer = confirmer;
    }

    public String getEstateCertLandArea() {
        return EstateCertLandArea;
    }

    public void setEstateCertLandArea(String estateCertLandArea) {
        EstateCertLandArea = estateCertLandArea;
    }

    public String getEstateCertPropertyArea() {
        return EstateCertPropertyArea;
    }

    public void setEstateCertPropertyArea(String estateCertPropertyArea) {
        EstateCertPropertyArea = estateCertPropertyArea;
    }

    public String getLandCertArea() {
        return LandCertArea;
    }

    public void setLandCertArea(String landCertArea) {
        LandCertArea = landCertArea;
    }

    public String getPropertyCertArea() {
        return PropertyCertArea;
    }

    public void setPropertyCertArea(String propertyCertArea) {
        PropertyCertArea = propertyCertArea;
    }

    public String getOtherLandArea() {
        return OtherLandArea;
    }

    public void setOtherLandArea(String otherLandArea) {
        OtherLandArea = otherLandArea;
    }

    public String getTotalLegalArea() {
        return TotalLegalArea;
    }

    public void setTotalLegalArea(String totalLegalArea) {
        TotalLegalArea = totalLegalArea;
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

    public int getMapId() {
        return MapId;
    }

    public void setMapId(int MapId) {
        this.MapId = MapId;
    }

    public String getEnterpriseId() {
        return EnterpriseId;
    }

    public void setEnterpriseId(String EnterpriseId) {
        this.EnterpriseId = EnterpriseId;
    }

    public String getMapperId() {
        return MapperId;
    }

    public void setMapperId(String MapperId) {
        this.MapperId = MapperId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getTotalNotRecordArea() {
        return TotalNotRecordArea;
    }

    public void setTotalNotRecordArea(String TotalNotRecordArea) {
        this.TotalNotRecordArea = TotalNotRecordArea;
    }

    public String getTotalBuildingArea() {
        return TotalBuildingArea;
    }

    public void setTotalBuildingArea(String TotalBuildingArea) {
        this.TotalBuildingArea = TotalBuildingArea;
    }

    public String getSimpleHouseArea() {
        return SimpleHouseArea;
    }

    public void setSimpleHouseArea(String SimpleHouseArea) {
        this.SimpleHouseArea = SimpleHouseArea;
    }

    public String getShedArea() {
        return ShedArea;
    }

    public void setShedArea(String ShedArea) {
        this.ShedArea = ShedArea;
    }

    public String getAppurtenanceArea() {
        return AppurtenanceArea;
    }

    public void setAppurtenanceArea(String AppurtenanceArea) {
        this.AppurtenanceArea = AppurtenanceArea;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCurrentOccupyArea() {
        return CurrentOccupyArea;
    }

    public void setCurrentOccupyArea(String CurrentOccupyArea) {
        this.CurrentOccupyArea = CurrentOccupyArea;
    }

}
