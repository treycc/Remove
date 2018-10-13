package com.jdp.hls.page.airphoto.building;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.AirPhotoBuilding;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface AirPhotoBuildingContract {
    interface View extends BaseView {
        void onGetAirPhotoBuildingsSuccess(List<AirPhotoBuilding> airPhotoBuildingList);
    }

    interface Presenter extends BasePresenter<View> {
        void getAirPhotoBuildings( String buildingType);
    }
}
