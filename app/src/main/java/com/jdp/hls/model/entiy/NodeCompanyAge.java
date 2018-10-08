package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/30 0030 上午 11:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyAge {

    /**
     * IdenId : 7
     * EnterpriseId : b41d2663-f3b4-406b-a566-0655ca4437cd
     * IdentifierId : 1
     * RealName :
     * CompanyName :
     * Before90Area : 1.00
     * After90Area : 2.00
     * AsLegitimateArea : 12.00
     * IdenDate : 2018/9/9 星期日 上午 12:00:00
     * Address : 不动产证，地址改了22
     * SimpleHouseArea : 1.00
     * ShedArea : 1.00
     * IsAllowEdit : false
     */

    private int IdenId;
    private String EnterpriseId;
    private String IdentifierId;
    private String RealName;
    private String CompanyName;
    private String Before90Area;
    private String After90Area;
    private String AsLegitimateArea;
    private String IdenDate;
    private String Address;
    private String SimpleHouseArea;
    private String ShedArea;
    private String TotalNotRecordArea;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;
    public int getIdenId() {
        return IdenId;
    }

    public String getTotalNotRecordArea() {
        return TotalNotRecordArea;
    }

    public void setTotalNotRecordArea(String totalNotRecordArea) {
        TotalNotRecordArea = totalNotRecordArea;
    }

    public void setIdenId(int IdenId) {
        this.IdenId = IdenId;
    }

    public String getEnterpriseId() {
        return EnterpriseId;
    }

    public void setEnterpriseId(String EnterpriseId) {
        this.EnterpriseId = EnterpriseId;
    }

    public String getIdentifierId() {
        return IdentifierId;
    }

    public void setIdentifierId(String IdentifierId) {
        this.IdentifierId = IdentifierId;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getBefore90Area() {
        return Before90Area;
    }

    public void setBefore90Area(String Before90Area) {
        this.Before90Area = Before90Area;
    }

    public String getAfter90Area() {
        return After90Area;
    }

    public void setAfter90Area(String After90Area) {
        this.After90Area = After90Area;
    }

    public String getAsLegitimateArea() {
        return AsLegitimateArea;
    }

    public void setAsLegitimateArea(String AsLegitimateArea) {
        this.AsLegitimateArea = AsLegitimateArea;
    }

    public String getIdenDate() {
        return IdenDate;
    }

    public void setIdenDate(String IdenDate) {
        this.IdenDate = IdenDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
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
