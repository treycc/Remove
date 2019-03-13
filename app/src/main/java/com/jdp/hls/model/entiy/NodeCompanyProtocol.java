package com.jdp.hls.model.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/30 0030 上午 11:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyProtocol implements Serializable {


    private boolean IsAllowEdit;
    private List<ImgInfo> Files;
    private List<ProtocolItem> Items;
    private String buildingId;
    private String CusCode;
    private String EnterpriseName;
    private String Address;
    private double LegalArea;
    private double After90Area;
    private double OtherArea;
    private double PropertyArea;
    private double LandArea;
    private String LegalName;
    private String AuditCompany;
    private String AuditName;
    private String AuditDate;
    private String Remark;
    private int PayType;

    public String getRemark() {
        return null == Remark ? "" : Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int payType) {
        PayType = payType;
    }

    public String getBuildingId() {
        return null == buildingId ? "" : buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
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

    public List<ProtocolItem> getItems() {
        return Items;
    }

    public void setItems(List<ProtocolItem> items) {
        Items = items;
    }

    public String getCusCode() {
        return CusCode;
    }

    public void setCusCode(String CusCode) {
        this.CusCode = CusCode;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String EnterpriseName) {
        this.EnterpriseName = EnterpriseName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public double getLegalArea() {
        return LegalArea;
    }

    public void setLegalArea(double LegalArea) {
        this.LegalArea = LegalArea;
    }

    public double getAfter90Area() {
        return After90Area;
    }

    public void setAfter90Area(double After90Area) {
        this.After90Area = After90Area;
    }

    public double getOtherArea() {
        return OtherArea;
    }

    public void setOtherArea(double OtherArea) {
        this.OtherArea = OtherArea;
    }

    public double getPropertyArea() {
        return PropertyArea;
    }

    public void setPropertyArea(double PropertyArea) {
        this.PropertyArea = PropertyArea;
    }

    public double getLandArea() {
        return LandArea;
    }

    public void setLandArea(double LandArea) {
        this.LandArea = LandArea;
    }

    public String getLegalName() {
        return LegalName;
    }

    public void setLegalName(String LegalName) {
        this.LegalName = LegalName;
    }

    public String getAuditCompany() {
        return AuditCompany;
    }

    public void setAuditCompany(String AuditCompany) {
        this.AuditCompany = AuditCompany;
    }

    public String getAuditName() {
        return AuditName;
    }

    public void setAuditName(String AuditName) {
        this.AuditName = AuditName;
    }

    public String getAuditDate() {
        return AuditDate;
    }

    public void setAuditDate(String AuditDate) {
        this.AuditDate = AuditDate;
    }
}
