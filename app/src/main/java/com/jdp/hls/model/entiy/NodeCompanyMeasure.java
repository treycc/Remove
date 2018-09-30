package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/30 0030 上午 10:52
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyMeasure {

    /**
     * MeaId : 1
     * EnterpriseId : b41d2663-f3b4-406b-a566-0655ca4437cd
     * CompanyName : 特兰普微信朋友圈
     * MeasurerId : 1
     * RealName : 杰森
     * MeaDate :
     * Remark :
     * Address : 不动产证，地址改了22
     * IsAllowEdit : false
     */

    private int MeaId;
    private String EnterpriseId;
    private String CompanyName;
    private int MeasurerId;
    private String RealName;
    private String MeaDate;
    private String Remark;
    private String Address;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }

    public int getMeaId() {
        return MeaId;
    }

    public void setMeaId(int MeaId) {
        this.MeaId = MeaId;
    }

    public String getEnterpriseId() {
        return EnterpriseId;
    }

    public void setEnterpriseId(String EnterpriseId) {
        this.EnterpriseId = EnterpriseId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public int getMeasurerId() {
        return MeasurerId;
    }

    public void setMeasurerId(int MeasurerId) {
        this.MeasurerId = MeasurerId;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getMeaDate() {
        return MeaDate;
    }

    public void setMeaDate(String MeaDate) {
        this.MeaDate = MeaDate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
}
