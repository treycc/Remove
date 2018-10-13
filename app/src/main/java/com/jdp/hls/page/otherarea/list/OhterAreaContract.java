package com.jdp.hls.page.otherarea.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.OtherArea;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface OhterAreaContract {
    interface View extends BaseView {
        void onGetOtherAreaListSuccess(List<OtherArea> otherAreaList);

        void onDeleteOtherAreaSuccess(int position);
    }

    interface Presenter extends BasePresenter<View> {
        void getOtherAreaList(String id, String buildingType);

        void deleteOtherArea(RequestBody requestBody,int position);
    }
}
