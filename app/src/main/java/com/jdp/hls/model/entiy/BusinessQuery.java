package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/11/21 0021 下午 3:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessQuery implements Serializable {

    /**
     * BuildingId : 7e46dbfa-7872-47d1-872f-fe7af193750d
     * BuildingType : 0
     * RealName : 安子杰
     * OperatorName : 杰森2
     * MobilePhone : 16666666665
     * Idcard : 16666666665
     * Address : 浙江省温州市龙湾区蒲州街道兴国路166号
     * CusCode :
     * SysCode : 89-222222-P3-A-0034
     * GroupId : 1
     * StatusId : 5
     * StatusDesc : 协议生成
     * PayType : 99
     */

    private String BuildingId;
    private int BuildingType;
    private String RealName;
    private String OperatorName;
    private String MobilePhone;
    private String Idcard;
    private String Address;
    private String CusCode;
    private String SysCode;
    private String PayTypeName;
    private int GroupId;
    private int StatusId;
    private String StatusDesc;
    private int PayType;

    public String getPayTypeName() {
        return PayTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        PayTypeName = payTypeName;
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

    public String getOperatorName() {
        return OperatorName;
    }

    public void setOperatorName(String OperatorName) {
        this.OperatorName = OperatorName;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCusCode() {
        return CusCode;
    }

    public void setCusCode(String CusCode) {
        this.CusCode = CusCode;
    }

    public String getSysCode() {
        return SysCode;
    }

    public void setSysCode(String SysCode) {
        this.SysCode = SysCode;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int GroupId) {
        this.GroupId = GroupId;
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
}
