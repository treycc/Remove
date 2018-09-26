package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/26 0026 下午 6:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyLand {
    private int Id;
    private String EnterpriseId;
    private String CertNum;
    private int LandUseTypeId;
    private int LandNatureTypeId;
    private double Area;
    private double Mu;
    private String Address;
    private String LandNatureTypeName;
    private String LandUseTypeName;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEnterpriseId() {
        return EnterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        EnterpriseId = enterpriseId;
    }

    public String getCertNum() {
        return CertNum;
    }

    public void setCertNum(String certNum) {
        CertNum = certNum;
    }

    public int getLandUseTypeId() {
        return LandUseTypeId;
    }

    public void setLandUseTypeId(int landUseTypeId) {
        LandUseTypeId = landUseTypeId;
    }

    public int getLandNatureTypeId() {
        return LandNatureTypeId;
    }

    public void setLandNatureTypeId(int landNatureTypeId) {
        LandNatureTypeId = landNatureTypeId;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double area) {
        Area = area;
    }

    public double getMu() {
        return Mu;
    }

    public void setMu(double mu) {
        Mu = mu;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLandNatureTypeName() {
        return LandNatureTypeName;
    }

    public void setLandNatureTypeName(String landNatureTypeName) {
        LandNatureTypeName = landNatureTypeName;
    }

    public String getLandUseTypeName() {
        return LandUseTypeName;
    }

    public void setLandUseTypeName(String landUseTypeName) {
        LandUseTypeName = landUseTypeName;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }
}
