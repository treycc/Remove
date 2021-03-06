package com.jdp.hls.page.airphoto.add;


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
public interface AirPhotoApplyContract {
    interface View extends BaseView {
        void onApplyAirPhotoSuccess(AirPhotoItem airPhotoItem);
    }

    interface Presenter extends BasePresenter<View> {
        void applyAirPhoto(RequestBody rosterBody);
    }
}
