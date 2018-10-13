package com.jdp.hls.page.otherarea.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface OtherAreaDetailContract {
    interface View extends BaseView {
        void onModifyOtherAreaSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void modifyOtherArea(RequestBody requestBody);
    }
}
