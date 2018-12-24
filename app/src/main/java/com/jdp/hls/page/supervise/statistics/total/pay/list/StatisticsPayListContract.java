package com.jdp.hls.page.supervise.statistics.total.pay.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.StatisticsTotalInfo;
import com.jdp.hls.model.entiy.SupervisePayInfo;

import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface StatisticsPayListContract {
    interface View extends BaseView {
        void onGetSupervisePayListSuccess(SupervisePayInfo supervisePayInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getSupervisePayList(RequestBody requestBody);
    }
}
