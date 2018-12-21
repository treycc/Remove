package com.jdp.hls.model.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/12/18 0018 下午 8:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayItem implements Serializable {

    /**
     * Id : 1
     * BuildingId : 83faf037-6ee7-4a1f-9c0e-039948b90f26
     * BuildingType : 0
     * BankAccountId : 0
     * RecBankAccountId : 0
     * BankStreamId : 0
     * PayDate : 2018-12-18
     * Amount : 20000
     * UseItemId : 0
     * UseItemName :
     * IsDouble : false
     * LimitStartDate :
     * LimitEndDate :
     * Remark :
     * IsAllowEdit : true
     */

    private int Id;
    private String BuildingId;
    private int BuildingType;
    private int BankAccountId;
    private int RecBankAccountId;
    private String BankStreamId;
    private String PayDate;
    private double Amount;
    private int UseItemId;
    private String UseItemName;
    private boolean IsDouble;
    private String LimitStartDate;
    private String LimitEndDate;
    private String Remark;
    private String BankAccountName;
    private String BankAccount;
    private String Idcard;
    private String RealName;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public String getIdcard() {
        return null == Idcard ? "" : Idcard;
    }

    public void setIdcard(String idcard) {
        Idcard = idcard;
    }

    public String getRealName() {
        return null == RealName ? "" : RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
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

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(String BuildingId) {
        this.BuildingId = BuildingId;
    }

    public int getBuildingType() {
        return BuildingType;
    }

    public void setBuildingType(int BuildingType) {
        this.BuildingType = BuildingType;
    }

    public int getBankAccountId() {
        return BankAccountId;
    }

    public void setBankAccountId(int BankAccountId) {
        this.BankAccountId = BankAccountId;
    }

    public int getRecBankAccountId() {
        return RecBankAccountId;
    }

    public void setRecBankAccountId(int RecBankAccountId) {
        this.RecBankAccountId = RecBankAccountId;
    }

    public String getBankStreamId() {
        return BankStreamId;
    }

    public void setBankStreamId(String BankStreamId) {
        this.BankStreamId = BankStreamId;
    }

    public String getPayDate() {
        return PayDate;
    }

    public void setPayDate(String PayDate) {
        this.PayDate = PayDate;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public int getUseItemId() {
        return UseItemId;
    }

    public void setUseItemId(int UseItemId) {
        this.UseItemId = UseItemId;
    }

    public String getUseItemName() {
        return UseItemName;
    }

    public void setUseItemName(String UseItemName) {
        this.UseItemName = UseItemName;
    }

    public boolean isIsDouble() {
        return IsDouble;
    }

    public void setIsDouble(boolean IsDouble) {
        this.IsDouble = IsDouble;
    }

    public String getLimitStartDate() {
        return LimitStartDate;
    }

    public void setLimitStartDate(String LimitStartDate) {
        this.LimitStartDate = LimitStartDate;
    }

    public String getLimitEndDate() {
        return LimitEndDate;
    }

    public void setLimitEndDate(String LimitEndDate) {
        this.LimitEndDate = LimitEndDate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public boolean isIsAllowEdit() {
        return IsAllowEdit;
    }

    public void setIsAllowEdit(boolean IsAllowEdit) {
        this.IsAllowEdit = IsAllowEdit;
    }

    @Override
    public int hashCode() {
        return this.Id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PayItem)) {
            return false;
        }
        PayItem item = (PayItem) obj;
        return this.Id == item.getId();
    }
}
