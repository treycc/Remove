package com.jdp.hls.page.business.detail.personal;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DetailPersonal;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DetailPersonalContract {
    interface View extends BaseView {
        void onGetPersonalDetailSuccess(DetailPersonal detailPersonal);
        void onModifyPersonalDetailSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getPersonalDetail(String buildingId);
        void modifyPersonalDetail(RequestBody rosterBody);
    }
}
