package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/20 0020 上午 9:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailCompany {
    private int EstateCount;
    private int PropertyCount;
    private int LandCount;
    private String HouseId;
    private String SysCode;
    private String BizInfo;
    private String RentInfo;
    private String CusCode;
    private String RealName;
    private String EnterpriseName;
    private String MobilePhone;
    private String Address;
    private double BizUseArea;
    private String PropertyCertNum;
    private String LandCertNum;
    private String EstateCertNum;
    private String LicenseNo;
    private String Remark;
    private int StatusId;
    private int PersonCount;
    private String StatusDesc;
    private String CurrentOccupyArea;
    private String JuridicalPersonName;
    private String JuridicalPersonMobilePhone;
    private String BankAccount;
    private double Longitude;
    private double Latitude;
    private boolean IsAllowPublicity;
    private boolean IsAllowEdit;
    private boolean IsUrgent;
    private List<ImgInfo> Files;
    private List<ImgInfo> ApprovalFiles;
    private List<ImgInfo> OtherFiles;



    public int getEstateCount() {
        return EstateCount;
    }

    public void setEstateCount(int estateCount) {
        EstateCount = estateCount;
    }

    public int getPropertyCount() {
        return PropertyCount;
    }

    public void setPropertyCount(int propertyCount) {
        PropertyCount = propertyCount;
    }

    public int getLandCount() {
        return LandCount;
    }

    public void setLandCount(int landCount) {
        LandCount = landCount;
    }

    public int getPersonCount() {
        return PersonCount;
    }

    public void setPersonCount(int personCount) {
        PersonCount = personCount;
    }

    public boolean isUrgent() {
        return IsUrgent;
    }

    public void setUrgent(boolean urgent) {
        IsUrgent = urgent;
    }

    public List<ImgInfo> getOtherFiles() {
        return OtherFiles;
    }

    public void setOtherFiles(List<ImgInfo> otherFiles) {
        OtherFiles = otherFiles;
    }

    public String getBankAccount() {
        return BankAccount;
    }

    public void setBankAccount(String bankAccount) {
        BankAccount = bankAccount;
    }

    public List<ImgInfo> getApprovalFiles() {
        return ApprovalFiles;
    }

    public void setApprovalFiles(List<ImgInfo> approvalFiles) {
        ApprovalFiles = approvalFiles;
    }

    public String getJuridicalPersonName() {
        return JuridicalPersonName;
    }

    public void setJuridicalPersonName(String juridicalPersonName) {
        JuridicalPersonName = juridicalPersonName;
    }

    public String getJuridicalPersonMobilePhone() {
        return JuridicalPersonMobilePhone;
    }

    public void setJuridicalPersonMobilePhone(String juridicalPersonMobilePhone) {
        JuridicalPersonMobilePhone = juridicalPersonMobilePhone;
    }

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }

    public String getCurrentOccupyArea() {
        return CurrentOccupyArea;
    }

    public void setCurrentOccupyArea(String currentOccupyArea) {
        CurrentOccupyArea = currentOccupyArea;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public String getBizInfo() {
        return BizInfo;
    }

    public void setBizInfo(String bizInfo) {
        BizInfo = bizInfo;
    }

    public String getRentInfo() {
        return RentInfo;
    }

    public void setRentInfo(String rentInfo) {
        RentInfo = rentInfo;
    }

    public String getCusCode() {
        return CusCode;
    }

    public void setCusCode(String cusCode) {
        CusCode = cusCode;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

    public String getSysCode() {
        return SysCode;
    }

    public void setSysCode(String sysCode) {
        SysCode = sysCode;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getBizUseArea() {
        return BizUseArea;
    }

    public void setBizUseArea(double bizUseArea) {
        BizUseArea = bizUseArea;
    }

    public String getPropertyCertNum() {
        return PropertyCertNum;
    }

    public void setPropertyCertNum(String propertyCertNum) {
        PropertyCertNum = propertyCertNum;
    }

    public String getLandCertNum() {
        return LandCertNum;
    }

    public void setLandCertNum(String landCertNum) {
        LandCertNum = landCertNum;
    }

    public String getEstateCertNum() {
        return EstateCertNum;
    }

    public void setEstateCertNum(String estateCertNum) {
        EstateCertNum = estateCertNum;
    }

    public String getLicenseNo() {
        return LicenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        LicenseNo = licenseNo;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public String getStatusDesc() {
        return StatusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        StatusDesc = statusDesc;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public boolean isAllowPublicity() {
        return IsAllowPublicity;
    }

    public void setAllowPublicity(boolean allowPublicity) {
        IsAllowPublicity = allowPublicity;
    }
}
