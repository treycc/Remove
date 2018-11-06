package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/6 0006 上午 9:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedPersonalBank {

    /**
     * Id : string
     * HouseId : string
     * BankAccountName : string,户名
     * BankName : string,银行名称
     * BankAccount : string,银行账号
     * Remark : string
     * Files : [{"Id":"string","FileUrl":"string","SmallImgUrl":"string"}]
     * IsAllowEdit : true
     */

    private String Id;
    private String HouseId;
    private String BankAccountName;
    private String BankName;
    private String BankAccount;
    private String Remark;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String HouseId) {
        this.HouseId = HouseId;
    }

    public String getBankAccountName() {
        return BankAccountName;
    }

    public void setBankAccountName(String BankAccountName) {
        this.BankAccountName = BankAccountName;
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

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> Files) {
        this.Files = Files;
    }

}
