package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/19 0019 下午 8:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BasicCompany {

    private String HouseId;
    private String SysCode;
    private String EnterpriseName;
    private String Address;
    private String StatusDesc;
    private String GroupId;
    private int StatusId;
    private List<FlowNode> FlowNodes;
    private Auth Auth;

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }

    public String getStatusDesc() {
        return StatusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        StatusDesc = statusDesc;
    }

    public List<FlowNode> getFlowNodes() {
        return FlowNodes;
    }

    public void setFlowNodes(List<FlowNode> flowNodes) {
        FlowNodes = flowNodes;
    }

    public com.jdp.hls.model.entiy.Auth getAuth() {
        return Auth;
    }

    public void setAuth(com.jdp.hls.model.entiy.Auth auth) {
        Auth = auth;
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

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }
}
