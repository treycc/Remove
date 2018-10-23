package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/10/12 0012 上午 10:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DecorationItem implements Serializable {
    /**
     * Id : 0
     * EvalId : 1
     * DPSCId : 4
     * GradeName : 2
     * Quantity : 10
     * Price : 30
     * ItemType : 0
     * ItemTypeName :
     * StandardName : (￥/㎡)
     * StandardUnit :
     */

    private int Id;
    private int EvalId;
    private int DPSCId;
    private int DPSId;
    private String GradeName;
    private int Quantity;
    private double Price;
    private int ItemType;
    private String ItemTypeName;
    private String StandardName;
    private String StandardUnit;
    private String Remark;

    public int getDPSId() {
        return DPSId;
    }

    public void setDPSId(int DPSId) {
        this.DPSId = DPSId;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getEvalId() {
        return EvalId;
    }

    public void setEvalId(int EvalId) {
        this.EvalId = EvalId;
    }

    public int getDPSCId() {
        return DPSCId;
    }

    public void setDPSCId(int DPSCId) {
        this.DPSCId = DPSCId;
    }

    public String getGradeName() {
        return GradeName;
    }

    public void setGradeName(String ClassType) {
        this.GradeName = ClassType;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
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

    public String getStandardName() {
        return StandardName;
    }

    public void setStandardName(String StandardName) {
        this.StandardName = StandardName;
    }

    public String getStandardUnit() {
        return StandardUnit;
    }

    public void setStandardUnit(String StandardUnit) {
        this.StandardUnit = StandardUnit;
    }
}
