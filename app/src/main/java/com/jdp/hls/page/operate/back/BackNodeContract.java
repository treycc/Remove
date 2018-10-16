package com.jdp.hls.page.operate.back;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;

import okhttp3.RequestBody;
import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BackNodeContract {
    interface View extends BaseView {
        void onBackNodeSuccess();
        void onGetOperatePersonSuccess(String name);
    }

    interface Presenter extends BasePresenter<View> {

        void backNode(RequestBody requestBody);

        void getOperatePerson(String buildingId, String buildingType);
    }
}
