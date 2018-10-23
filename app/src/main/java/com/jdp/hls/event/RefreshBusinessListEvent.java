package com.jdp.hls.event;

/**
 * Description:TODO
 * Create Time:2018/10/23 0023 上午 9:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RefreshBusinessListEvent {
    private String buildingIds;

    public RefreshBusinessListEvent(String buildingIds) {
        this.buildingIds = buildingIds;
    }

    public String getBuildingIds() {
        return buildingIds;
    }

    public void setBuildingIds(String buildingIds) {
        this.buildingIds = buildingIds;
    }
}
