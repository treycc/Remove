package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/12/25 0025 上午 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayOwnerListInfo {
    private List<PayOwner> LstPayable;
    private AmountInfo AmountInfo;

    public List<PayOwner> getLstPayable() {
        return LstPayable;
    }

    public void setLstPayable(List<PayOwner> lstPayable) {
        LstPayable = lstPayable;
    }

    public com.jdp.hls.model.entiy.AmountInfo getAmountInfo() {
        return AmountInfo;
    }

    public void setAmountInfo(com.jdp.hls.model.entiy.AmountInfo amountInfo) {
        AmountInfo = amountInfo;
    }
}
