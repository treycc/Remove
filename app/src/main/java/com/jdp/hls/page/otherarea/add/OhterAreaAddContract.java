package com.jdp.hls.page.otherarea.add;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface OhterAreaAddContract {
    interface View extends BaseView {
        void onAddOtherAreaSuccess(Integer id);
    }

    interface Presenter extends BasePresenter<View> {
        void addOtherArea(RequestBody requestBody);
    }
}
