package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/8 0008 上午 9:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyMoneyEvaluate {

    /**
     * Id : string
     * EnterpriseId : string
     * EvaluatorId : string,评估人ID
     * EvaluatorName : string,评估人姓名
     * EvalDate : string,评估日期
     * AssetEvaluatAmount : string,资产评估金额
     * MobileDevicePay : string,可移动设备补偿
     * NonMobileDevicePay : string,不可移动设备补偿
     * IsSend : true
     * Remark : string,备注
     * Files : [{"Id":"string","FileUrl":"string","SmallImgUrl":"string"}]
     * IsAllowEdit : true
     */

    private String Id;
    private String EnterpriseId;
    private String EvaluatorId;
    private String EvaluatorName;
    private String CompanyName;
    private String EvalDate;
    private String AssetEvaluatAmount;
    private double MobileDevicePay;
    private double NonMobileDevicePay;
    private boolean IsSend;
    private String Remark;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public boolean isSend() {
        return IsSend;
    }

    public void setSend(boolean send) {
        IsSend = send;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
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

    public String getEvaluatorName() {
        return EvaluatorName;
    }

    public void setEvaluatorName(String EvaluatorName) {
        this.EvaluatorName = EvaluatorName;
    }

    public String getEvalDate() {
        return EvalDate;
    }

    public void setEvalDate(String EvalDate) {
        this.EvalDate = EvalDate;
    }

    public String getAssetEvaluatAmount() {
        return AssetEvaluatAmount;
    }

    public void setAssetEvaluatAmount(String AssetEvaluatAmount) {
        this.AssetEvaluatAmount = AssetEvaluatAmount;
    }

    public double getMobileDevicePay() {
        return MobileDevicePay;
    }

    public void setMobileDevicePay(double MobileDevicePay) {
        this.MobileDevicePay = MobileDevicePay;
    }

    public double getNonMobileDevicePay() {
        return NonMobileDevicePay;
    }

    public void setNonMobileDevicePay(double NonMobileDevicePay) {
        this.NonMobileDevicePay = NonMobileDevicePay;
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

}
