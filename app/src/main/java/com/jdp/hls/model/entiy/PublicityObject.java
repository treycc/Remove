package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/9/18 0018 上午 8:52
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityObject implements Serializable{
    private boolean checked;
    /**
     * BuildingId : 83faf037-6ee7-4a1f-9c0e-039948b90f26
     * BuildingType : 0
     * SysCode : 89-222222-P3-A-0003
     * RealName : 童纯斌测试呃呃九另二
     * MobilePhone : 15888820299
     * Idcard : 330355881949100857
     * Address : 测试地址
     * EnterpriseName :
     */

    private String BuildingId;
    private int BuildingType;
    private String SysCode;
    private String RealName;
    private String MobilePhone;
    private String Idcard;
    private String Address;
    private String EnterpriseName;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public String getSysCode() {
        return SysCode;
    }

    public void setSysCode(String SysCode) {
        this.SysCode = SysCode;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String EnterpriseName) {
        this.EnterpriseName = EnterpriseName;
    }
}
