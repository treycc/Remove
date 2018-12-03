package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/12/3 0003 上午 9:07
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressDetail {

    /**
     * Name : string,报表名称
     * ItemType : 1
     * ItemTypeName : string,统计类型（比如：入户丈量统计）
     * QuantityFinished : string,已完成（总已丈量户数30）
     * QuantityUnfinished : string,未完成（总未丈量户数30）
     * Ratio : string,比例（比如：37.5%）
     * RatioName : string,比如：丈量比例
     */

    private String Name;
    private int ItemType;
    private String ItemTypeName;
    private String QuantityFinished;
    private String QuantityUnfinished;
    private String Ratio;
    private String RatioName;

    public String getName() {
        return null == Name ? "" : Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getItemType() {
        return ItemType;
    }

    public void setItemType(int itemType) {
        ItemType = itemType;
    }

    public String getItemTypeName() {
        return null == ItemTypeName ? "" : ItemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        ItemTypeName = itemTypeName;
    }

    public String getQuantityFinished() {
        return null == QuantityFinished ? "" : QuantityFinished;
    }

    public void setQuantityFinished(String quantityFinished) {
        QuantityFinished = quantityFinished;
    }

    public String getQuantityUnfinished() {
        return null == QuantityUnfinished ? "" : QuantityUnfinished;
    }

    public void setQuantityUnfinished(String quantityUnfinished) {
        QuantityUnfinished = quantityUnfinished;
    }

    public String getRatio() {
        return null == Ratio ? "" : Ratio;
    }

    public void setRatio(String ratio) {
        Ratio = ratio;
    }

    public String getRatioName() {
        return null == RatioName ? "" : RatioName;
    }

    public void setRatioName(String ratioName) {
        RatioName = ratioName;
    }
}
