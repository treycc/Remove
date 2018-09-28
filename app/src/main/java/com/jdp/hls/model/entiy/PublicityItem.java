package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/9/13 0013 下午 8:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityItem {

    private int PubId;
    private String BatchName;
    private int PubType;
    private String PubTypeDesc;
    private int PubStatus;
    private String PubStatusDesc;
    private int BuildingType;
    private int BuildingCount;
    private String StartDate;
    private String EndDate;
    private String Descriptiton;
    private int OperatorId;
    private int groupId;
    private String CreateDatetime;
    private boolean IsAllowEdit;

    public int getPubId() {
        return PubId;
    }

    public void setPubId(int PubId) {
        this.PubId = PubId;
    }

    public String getBatchName() {
        return BatchName;
    }

    public void setBatchName(String BatchName) {
        this.BatchName = BatchName;
    }

    public int getPubType() {
        return PubType;
    }

    public void setPubType(int PubType) {
        this.PubType = PubType;
    }

    public String getPubTypeDesc() {
        return PubTypeDesc;
    }

    public void setPubTypeDesc(String PubTypeDesc) {
        this.PubTypeDesc = PubTypeDesc;
    }

    public int getPubStatus() {
        return PubStatus;
    }

    public void setPubStatus(int PubStatus) {
        this.PubStatus = PubStatus;
    }

    public String getPubStatusDesc() {
        return PubStatusDesc;
    }

    public void setPubStatusDesc(String PubStatusDesc) {
        this.PubStatusDesc = PubStatusDesc;
    }

    public int getBuildingType() {
        return BuildingType;
    }

    public void setBuildingType(int BuildingType) {
        this.BuildingType = BuildingType;
    }

    public int getBuildingCount() {
        return BuildingCount;
    }

    public void setBuildingCount(int BuildingCount) {
        this.BuildingCount = BuildingCount;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public String getDescriptiton() {
        return Descriptiton;
    }

    public void setDescriptiton(String Descriptiton) {
        this.Descriptiton = Descriptiton;
    }

    public int getOperatorId() {
        return OperatorId;
    }

    public void setOperatorId(int OperatorId) {
        this.OperatorId = OperatorId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getCreateDatetime() {
        return CreateDatetime;
    }

    public void setCreateDatetime(String CreateDatetime) {
        this.CreateDatetime = CreateDatetime;
    }

    public boolean isIsAllowEdit() {
        return IsAllowEdit;
    }

    public void setIsAllowEdit(boolean IsAllowEdit) {
        this.IsAllowEdit = IsAllowEdit;
    }
}
