package com.jdp.hls.event;

import com.jdp.hls.model.entiy.UnRecordBuilding;

/**
 * Description:TODO
 * Create Time:2018/10/11 0011 上午 9:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyUnrecordBuildingEvent {
    private UnRecordBuilding unRecordBuilding;

    public ModifyUnrecordBuildingEvent(UnRecordBuilding unRecordBuilding) {
        this.unRecordBuilding = unRecordBuilding;
    }

    public UnRecordBuilding getUnRecordBuilding() {
        return unRecordBuilding;
    }

    public void setUnRecordBuilding(UnRecordBuilding unRecordBuilding) {
        this.unRecordBuilding = unRecordBuilding;
    }
}
