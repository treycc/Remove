package com.jdp.hls.page.airphoto.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.AirPhotoItem;

import okhttp3.RequestBody;
import retrofit2.http.Body;

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
        void onSendAirPhotoSuccess(AirPhotoItem airPhotoItem);
        void onFinishAirPhotoSuccess();
        void onReviewAirPhotoSuccess(AirPhotoItem airPhotoItem);
        void onUpdateAgePhotosSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getAirPhotoDetail(String airCheckProId);
        void modifyAirPhotoDetail(RequestBody rosterBody);
        void sendAirPhoto(RequestBody requestBody);
        void finishAirPhoto(RequestBody requestBody);
        void reviewAirPhoto(RequestBody requestBody);
        void updateAgePhotos(RequestBody requestBody);
    }
}
