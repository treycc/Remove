package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/12/29 0029 上午 10:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BankListInfo {
    private boolean IsAllowEdit;
    private List<BankInfo> LstBankAccount;

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public List<BankInfo> getLstBankAccount() {
        return LstBankAccount;
    }

    public void setLstBankAccount(List<BankInfo> lstBankAccount) {
        LstBankAccount = lstBankAccount;
    }
}
