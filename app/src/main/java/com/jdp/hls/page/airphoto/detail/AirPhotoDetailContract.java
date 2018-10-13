package com.jdp.hls.page.airphoto.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.AirPhotoItem;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface AirPhotoDetailContract {
    interface View extends BaseView {
        void onGetAirPhotoDetailSuccess(AirPhotoItem airPhotoItem);
        void onModifyAirPhotoDetailSuccess(AirPhotoItem airPhotoItem);
    }

    interface Presenter extends BasePresenter<View> {
        void getAirPhotoDetail(String airCheckProId);
        void modifyAirPhotoDetail(RequestBody rosterBody);
    }
}
