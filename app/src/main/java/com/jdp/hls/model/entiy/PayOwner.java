package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/12/25 0025 上午 10:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayOwner implements Serializable{

    /**
     * BuildingId : 83faf037-6ee7-4a1f-9c0e-039948b90f26
     * BuildingType : 0
     * RealName : 童测试三1
     * MobilePhone :
     * PayableAmount : 0.0
     * PaidAmount : 2000.0
     * BalanceAmount : -2000.0
     */

    private String BuildingId;
    private int BuildingType;
    private int Quantity;
    private int UseItemId;
    private String RealName;
    private String MobilePhone;
    private String Address;
    private String CusCode;
    private String Idcard;
    private double PayableAmount;
    private double PaidAmount;
    private double BalanceAmount;

    public String getIdcard() {
        return null == Idcard ? "" : Idcard;
    }

    public void setIdcard(String idcard) {
        Idcard = idcard;
    }

    public int getUseItemId() {
        return UseItemId;
    }

    public void setUseItemId(int useItemId) {
        UseItemId = useItemId;
    }

    public String getAddress() {
        return null == Address ? "" : Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCusCode() {
        return null == CusCode ? "" : CusCode;
    }

    public void setCusCode(String cusCode) {
        CusCode = cusCode;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(String BuildingId) {
        this.BuildingId = BuildingId;
    }

    public int getBuildingType() {
        return BuildingType;
    }

    public void setBuildingType(int BuildingType) {
        this.BuildingType = BuildingType;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public double getPayableAmount() {
        return PayableAmount;
    }

    public void setPayableAmount(double PayableAmount) {
        this.PayableAmount = PayableAmount;
    }

    public double getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(double PaidAmount) {
        this.PaidAmount = PaidAmount;
    }

    public double getBalanceAmount() {
        return BalanceAmount;
    }

    public void setBalanceAmount(double BalanceAmount) {
        this.BalanceAmount = BalanceAmount;
    }
}
