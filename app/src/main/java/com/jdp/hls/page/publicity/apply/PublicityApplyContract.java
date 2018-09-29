package com.jdp.hls.page.publicity.apply;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.PublicityDetail;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface PublicityApplyContract {
    interface View extends BaseView {
        void onApplyPublicitySuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void applyPublicity(RequestBody rosterBody);
    }
}
