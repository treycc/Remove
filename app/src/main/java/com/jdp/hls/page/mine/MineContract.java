package com.jdp.hls.page.mine;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;

import okhttp3.MultipartBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface MineContract {
    interface View extends BaseView {
        void onUploadHeadImgSuccess(String url);
    }

    interface Presenter extends BasePresenter<View> {
        void uploadHeadImg(MultipartBody.Part headImg);

    }
}
