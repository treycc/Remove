package com.jdp.hls.page.familyrelation.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Task;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface FamilyMememberDetailContract {
    interface View extends BaseView {
        void onSaveFamilyRememberSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void saveFamilyRemember(@Body RequestBody requestBody);
    }
}
