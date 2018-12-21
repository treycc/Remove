package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/12/20 0020 上午 9:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AmountInfo {
    private String PayableAmount;
    private String PaidAmount;
    private String BalanceAmount;

    public String getPayableAmount() {
        return null == PayableAmount ? "" : PayableAmount;
    }

    public void setPayableAmount(String payableAmount) {
        PayableAmount = payableAmount;
    }

    public String getPaidAmount() {
        return null == PaidAmount ? "" : PaidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        PaidAmount = paidAmount;
    }

    public String getBalanceAmount() {
        return null == BalanceAmount ? "" : BalanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        BalanceAmount = balanceAmount;
    }
}
