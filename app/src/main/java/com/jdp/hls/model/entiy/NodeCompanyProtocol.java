package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/30 0030 上午 11:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyProtocol {

    /**
     * PCId : 1
     * EnterpriseId : b41d2663-f3b4-406b-a566-0655ca4437cd
     * CheckerId : 1
     * RealName : 杰森
     * CompanyName : 特兰普微信朋友圈
     * PayType : 产权置换
     * PayTypeName :
     * TotalPurchasePrice : 1
     * TotalPay : 1
     * Rate : 1
     * RemoveFee : 1.00
     * TempPlacementFee : 1.00
     * OtherFee : 1.00
     * PCDate : 2018/6/6 星期三 上午 12:00:00
     * Remark : 12
     * SimpleHouseArea :
     * TotalNotRecordArea : 0.00
     * TotalBuildingArea :
     * Address : 不动产证，地址改了22
     * IsAllowEdit : false
     */

    private int PCId;
    private String EnterpriseId;
    private int CheckerId;
    private String RealName;
    private String CompanyName;
    private int PayType;
    private String PayTypeName;
    private double TotalPurchasePrice;
    private double TotalPay;
    private int Rate;
    private String RemoveFee;
    private String CusCode;
    private String TempPlacementFee;
    private String OtherFee;
    private String PCDate;
    private String Remark;
    private String SimpleHouseArea;
    private String TotalNotRecordArea;
    private String TotalBuildingArea;
    private String TotalLandAZArea;
    private String ClearObstaclePay;
    private String Address;
    private String LandCertArea;
    private String ChangeArea;
    private String DamagesAmount;
    private double NeedPayAmount;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;
    public String getCusCode() {
        return CusCode;
    }

    public String getChangeArea() {
        return ChangeArea;
    }

    public void setChangeArea(String changeArea) {
        ChangeArea = changeArea;
    }

    public String getDamagesAmount() {
        return DamagesAmount;
    }

    public void setDamagesAmount(String damagesAmount) {
        DamagesAmount = damagesAmount;
    }

    public double getNeedPayAmount() {
        return NeedPayAmount;
    }

    public void setNeedPayAmount(double needPayAmount) {
        NeedPayAmount = needPayAmount;
    }

    public String getClearObstaclePay() {
        return ClearObstaclePay;
    }

    public void setClearObstaclePay(String clearObstaclePay) {
        ClearObstaclePay = clearObstaclePay;
    }

    public void setCusCode(String cusCode) {
        CusCode = cusCode;
    }

    public String getTotalLandAZArea() {
        return TotalLandAZArea;
    }

    public void setTotalLandAZArea(String totalLandAZArea) {
        TotalLandAZArea = totalLandAZArea;
    }

    public int getPCId() {
        return PCId;
    }

    public String getLandCertArea() {
        return LandCertArea;
    }

    public void setLandCertArea(String landCertArea) {
        LandCertArea = landCertArea;
    }

    public void setPCId(int PCId) {
        this.PCId = PCId;
    }

    public String getEnterpriseId() {
        return EnterpriseId;
    }

    public void setEnterpriseId(String EnterpriseId) {
        this.EnterpriseId = EnterpriseId;
    }

    public int getCheckerId() {
        return CheckerId;
    }

    public void setCheckerId(int CheckerId) {
        this.CheckerId = CheckerId;
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

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int payType) {
        PayType = payType;
    }

    public String getPayTypeName() {
        return PayTypeName;
    }

    public void setPayTypeName(String PayTypeName) {
        this.PayTypeName = PayTypeName;
    }

    public double getTotalPurchasePrice() {
        return TotalPurchasePrice;
    }

    public void setTotalPurchasePrice(double TotalPurchasePrice) {
        this.TotalPurchasePrice = TotalPurchasePrice;
    }

    public double getTotalPay() {
        return TotalPay;
    }

    public void setTotalPay(double TotalPay) {
        this.TotalPay = TotalPay;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int Rate) {
        this.Rate = Rate;
    }

    public String getRemoveFee() {
        return RemoveFee;
    }

    public void setRemoveFee(String RemoveFee) {
        this.RemoveFee = RemoveFee;
    }

    public String getTempPlacementFee() {
        return TempPlacementFee;
    }

    public void setTempPlacementFee(String TempPlacementFee) {
        this.TempPlacementFee = TempPlacementFee;
    }

    public String getOtherFee() {
        return OtherFee;
    }

    public void setOtherFee(String OtherFee) {
        this.OtherFee = OtherFee;
    }

    public String getPCDate() {
        return PCDate;
    }

    public void setPCDate(String PCDate) {
        this.PCDate = PCDate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getSimpleHouseArea() {
        return SimpleHouseArea;
    }

    public void setSimpleHouseArea(String SimpleHouseArea) {
        this.SimpleHouseArea = SimpleHouseArea;
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

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }
}
