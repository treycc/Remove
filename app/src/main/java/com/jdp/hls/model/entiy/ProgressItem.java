package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/11/30 0030 下午 4:50
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProgressItem {


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
    private int Sort;

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getItemType() {
        return ItemType;
    }

    public void setItemType(int ItemType) {
        this.ItemType = ItemType;
    }

    public String getItemTypeName() {
        return ItemTypeName;
    }

    public void setItemTypeName(String ItemTypeName) {
        this.ItemTypeName = ItemTypeName;
    }

    public int getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(int TotalQuantity) {
        this.TotalQuantity = TotalQuantity;
    }

    public String getPercentDesc() {
        return PercentDesc;
    }

    public void setPercentDesc(String PercentDesc) {
        this.PercentDesc = PercentDesc;
    }

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String IconUrl) {
        this.IconUrl = IconUrl;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int Sort) {
        this.Sort = Sort;
    }
}
