package com.jdp.hls.page.photo;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ImgInfo;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface PhotoContract {
    interface View extends BaseView {
        void onModifyPhotosSuccess(List<ImgInfo> imgInfoList);
    }

    interface Presenter extends BasePresenter<View> {
        void modifyPhotos(RequestBody requestBody);
    }
}
