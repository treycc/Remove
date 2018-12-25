package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/12/20 0020 上午 11:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SupervisePayInfo {
    private List<PayItem> LstFinancePay;
    private boolean IsAllowEdit;
    private AmountInfo AmountInfo;

    public AmountInfo getAmountInfo() {
        return AmountInfo;
    }

    public void setAmountInfo(AmountInfo amountInfo) {
        AmountInfo = amountInfo;
    }

    public List<PayItem> getLstFinancePay() {
        return LstFinancePay;
    }

    public void setLstFinancePay(List<PayItem> lstFinancePay) {
        LstFinancePay = lstFinancePay;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }
}
