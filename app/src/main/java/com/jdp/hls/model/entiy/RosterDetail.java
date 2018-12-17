package com.jdp.hls.model.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/7/31 0031 下午 3:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterDetail implements Serializable {
    private String HouseId;
    private String RealName;
    private String HouseAddress;
    private String MobilePhone;
    private double Longitude;
    private double Latitude;
    private boolean IsEnterprise;
    private boolean IsEvaluated;
    private boolean IsMeasured;
    private boolean IsAssetEvaluator;
    private boolean Gender;
    private String Remark;
    private String Idcard;
    private String EnterpriseName;
    private String PersonId;
    private List<ImgInfo> HouseFiles;

    public boolean isAssetEvaluator() {
        return IsAssetEvaluator;
    }

    public void setAssetEvaluator(boolean assetEvaluator) {
        IsAssetEvaluator = assetEvaluator;
    }

    public String getPersonId() {
        return PersonId;
    }

    public void setPersonId(String personId) {
        PersonId = personId;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public List<ImgInfo> getHouseFiles() {
        return HouseFiles;
    }

    public void setHouseFiles(List<ImgInfo> houseFiles) {
        HouseFiles = houseFiles;
    }

    public String getIdcard() {
        return Idcard;
    }

    public void setIdcard(String idcard) {
        Idcard = idcard;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean gender) {
        Gender = gender;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getHouseAddress() {
        return HouseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        HouseAddress = houseAddress;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
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

    public boolean isEnterprise() {
        return IsEnterprise;
    }

    public void setEnterprise(boolean enterprise) {
        IsEnterprise = enterprise;
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
}
