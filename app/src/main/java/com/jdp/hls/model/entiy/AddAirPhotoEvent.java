package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/10/11 0011 下午 4:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddAirPhotoEvent {
    private AirPhotoItem airPhotoItem;

    public AirPhotoItem getAirPhotoItem() {
        return airPhotoItem;
    }

    public void setAirPhotoItem(AirPhotoItem airPhotoItem) {
        this.airPhotoItem = airPhotoItem;
    }

    public AddAirPhotoEvent(AirPhotoItem airPhotoItem) {
        this.airPhotoItem = airPhotoItem;
    }
}
