package com.jdp.hls.event;

import com.jdp.hls.model.entiy.AirPhotoItem;

/**
 * Description:TODO
 * Create Time:2018/10/11 0011 下午 4:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddAirPhotoEvent {
    private AirPhotoItem airPhotoItem;
    private String airPhotoType;

    public AirPhotoItem getAirPhotoItem() {
        return airPhotoItem;
    }

    public void setAirPhotoItem(AirPhotoItem airPhotoItem) {
        this.airPhotoItem = airPhotoItem;
    }

    public AddAirPhotoEvent(AirPhotoItem airPhotoItem,String airPhotoType) {
        this.airPhotoItem = airPhotoItem;
        this.airPhotoType = airPhotoType;
    }

    public String getAirPhotoType() {
        return airPhotoType;
    }

    public void setAirPhotoType(String airPhotoType) {
        this.airPhotoType = airPhotoType;
    }
}
