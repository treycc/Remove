package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/27 0027 下午 4:52
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalEvaluate {
    private int EvalId;
    private String HouseId;
    private String CompanyName;
    private int EvaluatorId;
    private double HouseResetMoney;
    private double InnerDecorateMoney;
    private double AppurtenancePay;
    private double OldHouseMarketMoney;
    private double OldHouseMarketTotalMoney;
    private String EvalDate;
    private String Address;
    private String RealName;
    private String Remark;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getEvalId() {
        return EvalId;
    }

    public void setEvalId(int evalId) {
        EvalId = evalId;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public int getEvaluatorId() {
        return EvaluatorId;
    }

    public void setEvaluatorId(int evaluatorId) {
        EvaluatorId = evaluatorId;
    }

    public double getHouseResetMoney() {
        return HouseResetMoney;
    }

    public void setHouseResetMoney(double houseResetMoney) {
        HouseResetMoney = houseResetMoney;
    }

    public double getInnerDecorateMoney() {
        return InnerDecorateMoney;
    }

    public void setInnerDecorateMoney(double innerDecorateMoney) {
        InnerDecorateMoney = innerDecorateMoney;
    }

    public double getAppurtenancePay() {
        return AppurtenancePay;
    }

    public void setAppurtenancePay(double appurtenancePay) {
        AppurtenancePay = appurtenancePay;
    }

    public double getOldHouseMarketMoney() {
        return OldHouseMarketMoney;
    }

    public void setOldHouseMarketMoney(double oldHouseMarketMoney) {
        OldHouseMarketMoney = oldHouseMarketMoney;
    }

    public double getOldHouseMarketTotalMoney() {
        return OldHouseMarketTotalMoney;
    }

    public void setOldHouseMarketTotalMoney(double oldHouseMarketTotalMoney) {
        OldHouseMarketTotalMoney = oldHouseMarketTotalMoney;
    }

    public String getEvalDate() {
        return EvalDate;
    }

    public void setEvalDate(String evalDate) {
        EvalDate = evalDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
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
