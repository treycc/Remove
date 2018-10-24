package com.jdp.hls.event;

import com.jdp.hls.model.entiy.AirPhotoItem;

/**
 * Description:TODO
 * Create Time:2018/10/11 0011 下午 4:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RemoveAirPhotoEvent {
    private int airCheckId;
    private String airPhotoType;

    public int getAirCheckId() {
        return airCheckId;
    }

    public void setAirCheckId(int airCheckId) {
        this.airCheckId = airCheckId;
    }

    public String getAirPhotoType() {
        return airPhotoType;
    }

    public void setAirPhotoType(String airPhotoType) {
        this.airPhotoType = airPhotoType;
    }

    public RemoveAirPhotoEvent(int airCheckId, String airPhotoType) {
        this.airCheckId = airCheckId;
        this.airPhotoType = airPhotoType;
    }
}
