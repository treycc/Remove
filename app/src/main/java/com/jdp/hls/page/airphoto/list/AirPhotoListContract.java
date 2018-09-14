package com.jdp.hls.page.airphoto.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.AirPhotoItem;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface AirPhotoListContract {
    interface View extends BaseView {
        void onGetAirPhotoListSuccess(List<AirPhotoItem> airPhotoItems);
    }

    interface Presenter extends BasePresenter<View> {
        void getAirPhotoList(String projectId, int airCurrentNodeType);
    }
}
