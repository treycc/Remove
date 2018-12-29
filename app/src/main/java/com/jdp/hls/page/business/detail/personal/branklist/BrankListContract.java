package com.jdp.hls.page.business.detail.personal.branklist;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.BrankListInfo;
import com.jdp.hls.model.entiy.FamilyRelation;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BrankListContract {
    interface View extends BaseView {
        void onGetBrankListSuccess(BrankListInfo brankListInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getBrankList(String buildingId);
    }
}
