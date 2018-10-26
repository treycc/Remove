package com.jdp.hls.page.publicity.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.PublicityDetail;
import com.jdp.hls.model.entiy.PublicityItem;

import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface PublicityDetailContract {
    interface View extends BaseView {
        void onGetPublicityDetailSuccess(PublicityDetail publicityDetail);

        void onModifyPublicitySuccess(PublicityItem publicityItem);
    }

    interface Presenter extends BasePresenter<View> {
        void getPublicityDetail(int pubId);

        void modifyPublicity(RequestBody rosterBody);
    }
}
