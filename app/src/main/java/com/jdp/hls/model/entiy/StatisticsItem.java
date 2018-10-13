package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/10/13 0013 下午 4:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsItem {

    /**
     * StatisType : 1
     * StatisTypeName : 已出图数
     * Quantity : 0
     * Percent : 0
     * PercentDesc : 0%
     */

    private int StatisType;
    private String StatisTypeName;
    private int Quantity;
    private double Percent;
    private String PercentDesc;

    public int getStatisType() {
        return StatisType;
    }

    public void setStatisType(int StatisType) {
        this.StatisType = StatisType;
    }

    public String getStatisTypeName() {
        return StatisTypeName;
    }

    public void setStatisTypeName(String StatisTypeName) {
        this.StatisTypeName = StatisTypeName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getPercent() {
        return Percent;
    }

    public void setPercent(double Percent) {
        this.Percent = Percent;
    }

    public String getPercentDesc() {
        return PercentDesc;
    }

    public void setPercentDesc(String PercentDesc) {
        this.PercentDesc = PercentDesc;
    }
}
