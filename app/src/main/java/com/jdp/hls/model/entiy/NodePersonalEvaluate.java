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
    private int HouseResetMoney;
    private int InnerDecorateMoney;
    private int AppurtenancePay;
    private int OldHouseMarketMoney;
    private int OldHouseMarketTotalMoney;
    private String EvalDate;
    private String Address;
    private String RealName;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

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

    public int getHouseResetMoney() {
        return HouseResetMoney;
    }

    public void setHouseResetMoney(int houseResetMoney) {
        HouseResetMoney = houseResetMoney;
    }

    public int getInnerDecorateMoney() {
        return InnerDecorateMoney;
    }

    public void setInnerDecorateMoney(int innerDecorateMoney) {
        InnerDecorateMoney = innerDecorateMoney;
    }

    public int getAppurtenancePay() {
        return AppurtenancePay;
    }

    public void setAppurtenancePay(int appurtenancePay) {
        AppurtenancePay = appurtenancePay;
    }

    public int getOldHouseMarketMoney() {
        return OldHouseMarketMoney;
    }

    public void setOldHouseMarketMoney(int oldHouseMarketMoney) {
        OldHouseMarketMoney = oldHouseMarketMoney;
    }

    public int getOldHouseMarketTotalMoney() {
        return OldHouseMarketTotalMoney;
    }

    public void setOldHouseMarketTotalMoney(int oldHouseMarketTotalMoney) {
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
