package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/26 0026 下午 2:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalMeasure {
    private int MeaId;
    private String HouseId;
    private int MeasurerId;
    private String RealName;
    private String MeaDate;
    private String Remark;
    private String Address;
    private boolean IsAllowEdit;
    private List<ImgInfo> Files;

    public int getMeaId() {
        return MeaId;
    }

    public void setMeaId(int meaId) {
        MeaId = meaId;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

    public int getMeasurerId() {
        return MeasurerId;
    }

    public void setMeasurerId(int measurerId) {
        MeasurerId = measurerId;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getMeaDate() {
        return MeaDate;
    }

    public void setMeaDate(String meaDate) {
        MeaDate = meaDate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

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
}
