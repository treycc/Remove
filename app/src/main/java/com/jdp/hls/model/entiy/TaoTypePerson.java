package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/12/20 0020 下午 6:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TaoTypePerson {

    /**
     * BuildingId : 55b57fab-b03b-498e-bb37-476c7cea308b
     * SysCode : 89-222222-P3-A-0007
     * CusCode : 89-222222-P3-A-0003
     * BuildingType : 0
     * RealName : 童测试三1
     * MobilePhone : 13712345678
     * Idcard : 13712345678
     * StatusId : 0
     * StatusDesc : 拆迁排查
     * PayType : 2
     * Address : 高考路66号66
     * PatternQuantity : 3套
     */

    private String BuildingId;
    private String SysCode;
    private String CusCode;
    private int BuildingType;
    private String RealName;
    private String MobilePhone;
    private String Idcard;
    private int StatusId;
    private String StatusDesc;
    private int PayType;
    private String Address;
    private String PatternQuantity;

    public String getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(String BuildingId) {
        this.BuildingId = BuildingId;
    }

    public String getSysCode() {
        return SysCode;
    }

    public void setSysCode(String SysCode) {
        this.SysCode = SysCode;
    }

    public String getCusCode() {
        return CusCode;
    }

    public void setCusCode(String CusCode) {
        this.CusCode = CusCode;
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

    public String getIdcard() {
        return Idcard;
    }

    public void setIdcard(String Idcard) {
        this.Idcard = Idcard;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int StatusId) {
        this.StatusId = StatusId;
    }

    public String getStatusDesc() {
        return StatusDesc;
    }

    public void setStatusDesc(String StatusDesc) {
        this.StatusDesc = StatusDesc;
    }

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int PayType) {
        this.PayType = PayType;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPatternQuantity() {
        return PatternQuantity;
    }

    public void setPatternQuantity(String PatternQuantity) {
        this.PatternQuantity = PatternQuantity;
    }
}
