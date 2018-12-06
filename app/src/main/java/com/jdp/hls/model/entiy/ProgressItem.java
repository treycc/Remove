package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/11/30 0030 下午 4:50
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProgressItem implements Serializable{


    /**
     * ItemType : 5
     * ItemTypeName : string,子项类别名称
     * TotalQuantity : 5
     * PercentDesc : string,占比，完成百分比（文字）
     * IconUrl : string,图片URL
     * Sort : 0
     */

    private int ItemType;
    private String ItemTypeName;
    private int TotalQuantity;
    private int Quantity;
    private String PercentDesc;
    private String IconUrl;
    private String FinishedQty;
    private String UnfinishedQty;
    private String RatioName;
    private int Sort;

    public String getFinishedQty() {
        return null == FinishedQty ? "" : FinishedQty;
    }

    public void setFinishedQty(String finishedQty) {
        FinishedQty = finishedQty;
    }

    public String getUnfinishedQty() {
        return null == UnfinishedQty ? "" : UnfinishedQty;
    }

    public void setUnfinishedQty(String unfinishedQty) {
        UnfinishedQty = unfinishedQty;
    }

    public String getRatioName() {
        return null == RatioName ? "" : RatioName;
    }

    public void setRatioName(String ratioName) {
        RatioName = ratioName;
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

    public int getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getPercentDesc() {
        return null == PercentDesc ? "" : PercentDesc;
    }

    public void setPercentDesc(String percentDesc) {
        PercentDesc = percentDesc;
    }

    public String getIconUrl() {
        return null == IconUrl ? "" : IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        IconUrl = iconUrl;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        Sort = sort;
    }
}
