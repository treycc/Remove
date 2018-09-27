package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/19 0019 下午 8:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BaiscPersonal {
    private String HouseId;
    private String SysCode;
    private String RealName;
    private String Address;
    private int StatusId;
    private List<FlowNode> FlowNodes;
    private Auth Auth;

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

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
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
