package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/12/29 0029 上午 10:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BankInfo {
    private int Id;
    private String HouseId;
    private String BankAccountName;
    private String BankName;
    private String BankAccount;
    private String Remark;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHouseId() {
        return null == HouseId ? "" : HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

    public String getBankName() {
        return null == BankName ? "" : BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getRemark() {
        return null == Remark ? "" : Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getBankAccountName() {
        return null == BankAccountName ? "" : BankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        BankAccountName = bankAccountName;
    }

    public String getBankAccount() {
        return null == BankAccount ? "" : BankAccount;
    }

    public void setBankAccount(String bankAccount) {
        BankAccount = bankAccount;
    }
}
