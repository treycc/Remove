package com.jdp.hls.page.module;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ModuleDetail;
import com.jdp.hls.model.entiy.PublicityDetail;
import com.jdp.hls.model.entiy.PublicityItem;

import okhttp3.RequestBody;
import retrofit2.http.Field;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ModuleContract {
    interface View extends BaseView {
        void onGetModuleDetailSuccess(ModuleDetail moduleDetail);
    }

    interface Presenter extends BasePresenter<View> {
        void getModuleDetail(String routeId);
    }
}
