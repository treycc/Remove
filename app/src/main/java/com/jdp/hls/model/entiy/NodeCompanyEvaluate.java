package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/30 0030 上午 11:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyEvaluate {

    /**
     * EvalId : 1
     * EnterpriseId : b41d2663-f3b4-406b-a566-0655ca4437cd
     * EvaluatorId : 1
     * RealName : 杰森
     * CompanyName : 特兰普微信朋友圈
     * AppurtenancePay : 0.00
     * MobileDevicePay : 0.00
     * NonMobileDevicePay : 0.00
     * LegalLandPay : 0.00
     * LegalBuildingPay : 0.00
     * LegalDecorationPay : 0.00
     * ClearObstaclePay :
     * EvalDate :
     * Address : 测试地
     * IsAllowEdit : false
     */

    private int EvalId;
    private String EnterpriseId;
    private int EvaluatorId;
    private String RealName;
    private String CompanyName;
    private String AppurtenancePay;
    private String MobileDevicePay;
    private String NonMobileDevicePay;
    private String LegalLandPay;
    private String LegalBuildingPay;
    private String LegalDecorationPay;
    private String ClearObstaclePay;
    private String EvalDate;
    private String Address;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public int getEvalId() {
        return EvalId;
    }

    public void setEvalId(int EvalId) {
        this.EvalId = EvalId;
    }

    public String getEnterpriseId() {
        return EnterpriseId;
    }

    public void setEnterpriseId(String EnterpriseId) {
        this.EnterpriseId = EnterpriseId;
    }

    public int getEvaluatorId() {
        return EvaluatorId;
    }

    public void setEvaluatorId(int EvaluatorId) {
        this.EvaluatorId = EvaluatorId;
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

    public String getAppurtenancePay() {
        return AppurtenancePay;
    }

    public void setAppurtenancePay(String AppurtenancePay) {
        this.AppurtenancePay = AppurtenancePay;
    }

    public String getMobileDevicePay() {
        return MobileDevicePay;
    }

    public void setMobileDevicePay(String MobileDevicePay) {
        this.MobileDevicePay = MobileDevicePay;
    }

    public String getNonMobileDevicePay() {
        return NonMobileDevicePay;
    }

    public void setNonMobileDevicePay(String NonMobileDevicePay) {
        this.NonMobileDevicePay = NonMobileDevicePay;
    }

    public String getLegalLandPay() {
        return LegalLandPay;
    }

    public void setLegalLandPay(String LegalLandPay) {
        this.LegalLandPay = LegalLandPay;
    }

    public String getLegalBuildingPay() {
        return LegalBuildingPay;
    }

    public void setLegalBuildingPay(String LegalBuildingPay) {
        this.LegalBuildingPay = LegalBuildingPay;
    }

    public String getLegalDecorationPay() {
        return LegalDecorationPay;
    }

    public void setLegalDecorationPay(String LegalDecorationPay) {
        this.LegalDecorationPay = LegalDecorationPay;
    }

    public String getClearObstaclePay() {
        return ClearObstaclePay;
    }

    public void setClearObstaclePay(String ClearObstaclePay) {
        this.ClearObstaclePay = ClearObstaclePay;
    }

    public String getEvalDate() {
        return EvalDate;
    }

    public void setEvalDate(String EvalDate) {
        this.EvalDate = EvalDate;
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
