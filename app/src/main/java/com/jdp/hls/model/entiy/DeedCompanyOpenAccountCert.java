package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/5 0005 下午 3:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyOpenAccountCert {

    /**
     * Id : 0
     * EnterpriseId : 06987b16-5df7-4326-a07a-ed01f5fa0de5
     * BankAccountName :
     * JuridicalPersonName :
     * CertNum :
     * BankName :
     * BankAccount :
     * Remark :
     * Files : []
     * IsAllowEdit : true
     */

    private int Id;
    private String EnterpriseId;
    private String BankAccountName;
    private String JuridicalPersonName;
    private String CertNum;
    private String BankName;
    private String BankAccount;
    private String Remark;
    private String EnterpriseName;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getEnterpriseId() {
        return EnterpriseId;
    }

    public void setEnterpriseId(String EnterpriseId) {
        this.EnterpriseId = EnterpriseId;
    }

    public String getBankAccountName() {
        return BankAccountName;
    }

    public void setBankAccountName(String BankAccountName) {
        this.BankAccountName = BankAccountName;
    }

    public String getJuridicalPersonName() {
        return JuridicalPersonName;
    }

    public void setJuridicalPersonName(String JuridicalPersonName) {
        this.JuridicalPersonName = JuridicalPersonName;
    }

    public String getCertNum() {
        return CertNum;
    }

    public void setCertNum(String CertNum) {
        this.CertNum = CertNum;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }

    public String getBankAccount() {
        return BankAccount;
    }

    public void setBankAccount(String BankAccount) {
        this.BankAccount = BankAccount;
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
