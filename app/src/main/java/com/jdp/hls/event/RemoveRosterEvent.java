package com.jdp.hls.event;

/**
 * Description:TODO
 * Create Time:2019/1/14 0014 下午 5:15
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RemoveRosterEvent {
    private String HouseId;
    private boolean IsEnterprise;

    public RemoveRosterEvent(String houseId, boolean isEnterprise) {
        HouseId = houseId;
        IsEnterprise = isEnterprise;
    }

    public String getHouseId() {
        return null == HouseId ? "" : HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

    public boolean isEnterprise() {
        return IsEnterprise;
    }

    public void setEnterprise(boolean enterprise) {
        IsEnterprise = enterprise;
    }
}
