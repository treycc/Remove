package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/12/20 0020 上午 9:23
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsTotalInfo {
    private AmountInfo AmountInfo;
    private List<KeyValue> LstBuildingCollect;

    public AmountInfo getAmountInfo() {
        return AmountInfo;
    }

    public void setAmountInfo(AmountInfo amountInfo) {
        AmountInfo = amountInfo;
    }

    public List<KeyValue> getLstBuildingCollect() {
        return LstBuildingCollect;
    }

    public void setLstBuildingCollect(List<KeyValue> lstBuildingCollect) {
        LstBuildingCollect = lstBuildingCollect;
    }
}
