package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/8 0008 上午 9:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyHouseEvaluate {

    /**
     * EvalId : string
     * EnterpriseId : string
     * EvaluatorId : string,评估人Id
     * RealName : string,评估人
     * CompanyName : string,公司名称
     * AppurtenancePay : string,附属物及构筑物补偿
     * LegalLandPay : string,合法土地补偿
     * LegalBuildingPay : string,合法建筑补偿
     * LegalDecorationPay : string,合法装修补偿
     * EvalDate : string,评估日期
     * Address : string,地址
     * Remark : string,备注
     * Files : [{"Id":"string","FileUrl":"string","SmallImgUrl":"string"}]
     * IsAllowEdit : string,允许编辑
     */

    private String EvalId;
    private String EnterpriseId;
    private String EvaluatorId;
    private String RealName;
    private String CompanyName;
    private String AppurtenancePay;
    private String LegalLandPay;
    private String LegalBuildingPay;
    private String LegalDecorationPay;
    private String EvalDate;
    private String Address;
    private String Remark;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public String getEvalId() {
        return EvalId;
    }

    public void setEvalId(String EvalId) {
        this.EvalId = EvalId;
    }

    public String getEnterpriseId() {
        return EnterpriseId;
    }

    public void setEnterpriseId(String EnterpriseId) {
        this.EnterpriseId = EnterpriseId;
    }

    public String getEvaluatorId() {
        return EvaluatorId;
    }

    public void setEvaluatorId(String EvaluatorId) {
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

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }


    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> Files) {
        this.Files = Files;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }
}
