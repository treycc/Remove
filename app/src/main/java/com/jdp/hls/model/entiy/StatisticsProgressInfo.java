package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/30 0030 下午 4:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressInfo {

    private List<PieChartItem> Series;
    private List<ProgressItem> ProgressItems;
    private int TotalQuantity;
    private int BuildingType;

    public List<PieChartItem> getSeries() {
        return Series;
    }

    public void setSeries(List<PieChartItem> series) {
        Series = series;
    }

    public List<ProgressItem> getProgressItems() {
        return ProgressItems;
    }

    public void setProgressItems(List<ProgressItem> progressItems) {
        ProgressItems = progressItems;
    }

    public int getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public int getBuildingType() {
        return BuildingType;
    }

    public void setBuildingType(int buildingType) {
        BuildingType = buildingType;
    }
}
