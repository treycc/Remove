package com.jdp.hls.model.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/27 0027 下午 4:52
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalProtocol implements Serializable {

    private int PCId;
    private String HouseId;
    private int CheckerId;
    private double TotalPurchasePrice;
    private double TotalPay;
    private double Rate;
    private int PayType;
    private double OtherArea;
    private double OverAuditArea;
    private double RemoveFee;
    private double TempPlacementFee;
    private double OtherFee;
    private String PCDate;
    private String CompanyName;
    private String Address;
    private String PayTypeName;
    private String OwnerName;
    private String TotalArea;
    private String BuildOccupyArea;
    private String TotalLandArea;
    private String TotalNotRecordArea;
    private String SysCode;
    private String CusCode;
    private String RealName;
    private String LandCertTotalArea;
    private String LandCertArea;
    private String TotalLandAZArea;
    private String Remark;
    private boolean IsAllowEdit;
    private double HZArea;
    private double AZBuildingArea;
    private double AZTNArea;
    private String TaoTypeName;
    private double DamagesAmount;
    private List<ImgInfo> Files;
    private double NeedPayAmount;
    private double EquityRepurchaseTotalAmount;
    private double EquityRepurchaseBountyAmount;
    private double FixedFacilitiesAmount;
    private double PulledDownPayAmount;
    private double KongFanPayAmount;
    private double AppurtenancePay;
    private double InnerDecorateMoney;
    private double NeedPayBuildingAmount;
    private double OldHouseMarketTotalMoney;
    private double HouseResetMoney;
    private double ClearObstaclePay;
    private double PayableAmount;
    private double PaidAmount;
    private double MoveBackFee;
    private double BalanceAmount;
    private double EquityRepurchaseRatio;
    private String PatternInfo;
    private List<TaoType> Patterns;

    public double getEquityRepurchaseRatio() {
        return EquityRepurchaseRatio;
    }

    public void setEquityRepurchaseRatio(double equityRepurchaseRatio) {
        EquityRepurchaseRatio = equityRepurchaseRatio;
    }

    public String getPatternInfo() {
        return null == PatternInfo ? "" : PatternInfo;
    }

    public void setPatternInfo(String patternInfo) {
        PatternInfo = patternInfo;
    }

    public double getMoveBackFee() {
        return MoveBackFee;
    }

    public void setMoveBackFee(double moveBackFee) {
        MoveBackFee = moveBackFee;
    }

    public List<TaoType> getPatterns() {
        return Patterns;
    }

    public void setPatterns(List<TaoType> patterns) {
        Patterns = patterns;
    }

    public double getPayableAmount() {
        return PayableAmount;
    }

    public void setPayableAmount(double payableAmount) {
        PayableAmount = payableAmount;
    }

    public double getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        PaidAmount = paidAmount;
    }

    public double getBalanceAmount() {
        return BalanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        BalanceAmount = balanceAmount;
    }

    public double getClearObstaclePay() {
        return ClearObstaclePay;
    }

    public void setClearObstaclePay(double clearObstaclePay) {
        ClearObstaclePay = clearObstaclePay;
    }

    public double getEquityRepurchaseTotalAmount() {
        return EquityRepurchaseTotalAmount;
    }

    public void setEquityRepurchaseTotalAmount(double equityRepurchaseTotalAmount) {
        EquityRepurchaseTotalAmount = equityRepurchaseTotalAmount;
    }

    public double getEquityRepurchaseBountyAmount() {
        return EquityRepurchaseBountyAmount;
    }

    public void setEquityRepurchaseBountyAmount(double equityRepurchaseBountyAmount) {
        EquityRepurchaseBountyAmount = equityRepurchaseBountyAmount;
    }

    public double getFixedFacilitiesAmount() {
        return FixedFacilitiesAmount;
    }

    public void setFixedFacilitiesAmount(double fixedFacilitiesAmount) {
        FixedFacilitiesAmount = fixedFacilitiesAmount;
    }

    public double getPulledDownPayAmount() {
        return PulledDownPayAmount;
    }

    public void setPulledDownPayAmount(double pulledDownPayAmount) {
        PulledDownPayAmount = pulledDownPayAmount;
    }

    public double getKongFanPayAmount() {
        return KongFanPayAmount;
    }

    public void setKongFanPayAmount(double kongFanPayAmount) {
        KongFanPayAmount = kongFanPayAmount;
    }

    public double getAppurtenancePay() {
        return AppurtenancePay;
    }

    public void setAppurtenancePay(double appurtenancePay) {
        AppurtenancePay = appurtenancePay;
    }

    public double getInnerDecorateMoney() {
        return InnerDecorateMoney;
    }

    public void setInnerDecorateMoney(double innerDecorateMoney) {
        InnerDecorateMoney = innerDecorateMoney;
    }

    public double getNeedPayBuildingAmount() {
        return NeedPayBuildingAmount;
    }

    public void setNeedPayBuildingAmount(double needPayBuildingAmount) {
        NeedPayBuildingAmount = needPayBuildingAmount;
    }

    public double getOldHouseMarketTotalMoney() {
        return OldHouseMarketTotalMoney;
    }

    public void setOldHouseMarketTotalMoney(double oldHouseMarketTotalMoney) {
        OldHouseMarketTotalMoney = oldHouseMarketTotalMoney;
    }

    public double getHouseResetMoney() {
        return HouseResetMoney;
    }

    public void setHouseResetMoney(double houseResetMoney) {
        HouseResetMoney = houseResetMoney;
    }

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }

    public double getHZArea() {
        return HZArea;
    }

    public void setHZArea(double HZArea) {
        this.HZArea = HZArea;
    }

    public double getAZBuildingArea() {
        return AZBuildingArea;
    }

    public void setAZBuildingArea(double AZBuildingArea) {
        this.AZBuildingArea = AZBuildingArea;
    }

    public double getAZTNArea() {
        return AZTNArea;
    }

    public void setAZTNArea(double AZTNArea) {
        this.AZTNArea = AZTNArea;
    }

    public String getTaoTypeName() {
        return TaoTypeName;
    }

    public void setTaoTypeName(String taoTypeName) {
        TaoTypeName = taoTypeName;
    }

    public double getDamagesAmount() {
        return DamagesAmount;
    }

    public void setDamagesAmount(double damagesAmount) {
        DamagesAmount = damagesAmount;
    }

    public double getNeedPayAmount() {
        return NeedPayAmount;
    }

    public void setNeedPayAmount(double needPayAmount) {
        NeedPayAmount = needPayAmount;
    }

    public int getPCId() {
        return PCId;
    }

    public void setPCId(int PCId) {
        this.PCId = PCId;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

    public int getCheckerId() {
        return CheckerId;
    }

    public void setCheckerId(int checkerId) {
        CheckerId = checkerId;
    }

    public double getTotalPurchasePrice() {
        return TotalPurchasePrice;
    }

    public void setTotalPurchasePrice(double totalPurchasePrice) {
        TotalPurchasePrice = totalPurchasePrice;
    }

    public double getTotalPay() {
        return TotalPay;
    }

    public void setTotalPay(double totalPay) {
        TotalPay = totalPay;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        Rate = rate;
    }


    public double getOtherArea() {
        return OtherArea;
    }

    public void setOtherArea(double otherArea) {
        OtherArea = otherArea;
    }

    public double getOverAuditArea() {
        return OverAuditArea;
    }

    public void setOverAuditArea(double overAuditArea) {
        OverAuditArea = overAuditArea;
    }

    public double getRemoveFee() {
        return RemoveFee;
    }

    public void setRemoveFee(double removeFee) {
        RemoveFee = removeFee;
    }

    public double getTempPlacementFee() {
        return TempPlacementFee;
    }

    public void setTempPlacementFee(double tempPlacementFee) {
        TempPlacementFee = tempPlacementFee;
    }

    public double getOtherFee() {
        return OtherFee;
    }

    public void setOtherFee(double otherFee) {
        OtherFee = otherFee;
    }

    public String getPCDate() {
        return PCDate;
    }

    public void setPCDate(String PCDate) {
        this.PCDate = PCDate;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPayTypeName() {
        return PayTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        PayTypeName = payTypeName;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getTotalArea() {
        return TotalArea;
    }

    public void setTotalArea(String totalArea) {
        TotalArea = totalArea;
    }

    public String getBuildOccupyArea() {
        return BuildOccupyArea;
    }

    public void setBuildOccupyArea(String buildOccupyArea) {
        BuildOccupyArea = buildOccupyArea;
    }

    public String getTotalLandArea() {
        return TotalLandArea;
    }

    public void setTotalLandArea(String totalLandArea) {
        TotalLandArea = totalLandArea;
    }

    public String getTotalNotRecordArea() {
        return TotalNotRecordArea;
    }

    public void setTotalNotRecordArea(String totalNotRecordArea) {
        TotalNotRecordArea = totalNotRecordArea;
    }

    public String getSysCode() {
        return SysCode;
    }

    public void setSysCode(String sysCode) {
        SysCode = sysCode;
    }

    public String getCusCode() {
        return CusCode;
    }

    public void setCusCode(String cusCode) {
        CusCode = cusCode;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getLandCertTotalArea() {
        return LandCertTotalArea;
    }

    public void setLandCertTotalArea(String landCertTotalArea) {
        LandCertTotalArea = landCertTotalArea;
    }

    public String getLandCertArea() {
        return LandCertArea;
    }

    public void setLandCertArea(String landCertArea) {
        LandCertArea = landCertArea;
    }

    public String getTotalLandAZArea() {
        return TotalLandAZArea;
    }

    public void setTotalLandAZArea(String totalLandAZArea) {
        TotalLandAZArea = totalLandAZArea;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int payType) {
        PayType = payType;
    }
}

