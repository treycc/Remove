package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/20 0020 上午 9:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailPersonal {

    /**
     * HouseId : 55b57fab-b03b-498e-bb37-476c7cea308b
     * SysCode : 89-222222-P3-A-0003
     * CusCode :
     * IsBanned : false
     * IsFlowBack : false
     * IsEvaluated : false
     * IsMeasured : false
     * CreateDatetime : 0001-01-01 00:00:00
     * RealName : 童纯斌测试呃呃
     * Gender : false
     * Idcard :
     * MobilePhone : 15888820299
     * PoliticalTitle : 0
     * PoliticalTitleDesc :
     * NeedTempHouse : false
     * Address : 高考路66号
     * IsShop : false
     * Longitude : 120.7287823137684
     * Latitude : 27.9646237028033
     * IsAllowPublicity : false
     * BizUseArea : 0.0
     * PropertyCertNum :
     * LandCertNum :
     * EstateCertNum :
     * Remark : 容易导致12121
     * StatusId : 0
     * StatusDesc :
     * HouseFiles : []
     */

    private String HouseId;
    private String SysCode;
    private String CusCode;
    private boolean IsBanned;
    private boolean IsFlowBack;
    private boolean IsEvaluated;
    private boolean IsMeasured;
    private String CreateDatetime;
    private String RealName;
    private boolean Gender;
    private String Idcard;
    private String MobilePhone;
    private int PoliticalTitle;
    private String PoliticalTitleDesc;
    private boolean NeedTempHouse;
    private String Address;
    private boolean IsShop;
    private double Longitude;
    private double Latitude;
    private boolean IsAllowPublicity;
    private double BizUseArea;
    private boolean IsAllowEdit;
    private String PropertyCertNum;
    private String LandCertNum;
    private String EstateCertNum;
    private String Remark;
    private int StatusId;
    private String StatusDesc;
    private String PersonId;
    private String BookletId;
    private List<ImgInfo> Files;

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }

    public String getBookletId() {
        return BookletId;
    }

    public void setBookletId(String bookletId) {
        BookletId = bookletId;
    }

    public String getPersonId() {
        return PersonId;
    }

    public void setPersonId(String personId) {
        PersonId = personId;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String HouseId) {
        this.HouseId = HouseId;
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

    public String getCreateDatetime() {
        return CreateDatetime;
    }

    public void setCreateDatetime(String CreateDatetime) {
        this.CreateDatetime = CreateDatetime;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean Gender) {
        this.Gender = Gender;
    }

    public String getIdcard() {
        return Idcard;
    }

    public void setIdcard(String Idcard) {
        this.Idcard = Idcard;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public int getPoliticalTitle() {
        return PoliticalTitle;
    }

    public void setPoliticalTitle(int PoliticalTitle) {
        this.PoliticalTitle = PoliticalTitle;
    }

    public String getPoliticalTitleDesc() {
        return PoliticalTitleDesc;
    }

    public void setPoliticalTitleDesc(String PoliticalTitleDesc) {
        this.PoliticalTitleDesc = PoliticalTitleDesc;
    }

    public boolean isNeedTempHouse() {
        return NeedTempHouse;
    }

    public void setNeedTempHouse(boolean NeedTempHouse) {
        this.NeedTempHouse = NeedTempHouse;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }


    public void setIsShop(boolean IsShop) {
        this.IsShop = IsShop;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double Longitude) {
        this.Longitude = Longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double Latitude) {
        this.Latitude = Latitude;
    }

    public double getBizUseArea() {
        return BizUseArea;
    }

    public void setBizUseArea(double BizUseArea) {
        this.BizUseArea = BizUseArea;
    }

    public String getPropertyCertNum() {
        return PropertyCertNum;
    }

    public void setPropertyCertNum(String PropertyCertNum) {
        this.PropertyCertNum = PropertyCertNum;
    }

    public String getLandCertNum() {
        return LandCertNum;
    }

    public void setLandCertNum(String LandCertNum) {
        this.LandCertNum = LandCertNum;
    }

    public String getEstateCertNum() {
        return EstateCertNum;
    }

    public void setEstateCertNum(String EstateCertNum) {
        this.EstateCertNum = EstateCertNum;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
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

    public boolean isBanned() {
        return IsBanned;
    }

    public void setBanned(boolean banned) {
        IsBanned = banned;
    }

    public boolean isFlowBack() {
        return IsFlowBack;
    }

    public void setFlowBack(boolean flowBack) {
        IsFlowBack = flowBack;
    }

    public boolean isEvaluated() {
        return IsEvaluated;
    }

    public void setEvaluated(boolean evaluated) {
        IsEvaluated = evaluated;
    }

    public boolean isMeasured() {
        return IsMeasured;
    }

    public void setMeasured(boolean measured) {
        IsMeasured = measured;
    }

    public boolean isShop() {
        return IsShop;
    }

    public void setShop(boolean shop) {
        IsShop = shop;
    }

    public boolean isAllowPublicity() {
        return IsAllowPublicity;
    }

    public void setAllowPublicity(boolean allowPublicity) {
        IsAllowPublicity = allowPublicity;
    }
}
