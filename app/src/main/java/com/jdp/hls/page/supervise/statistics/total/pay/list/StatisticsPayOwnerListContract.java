package com.jdp.hls.page.supervise.statistics.total.pay.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.PayOwnerListInfo;
import com.jdp.hls.model.entiy.StatisticsTotalInfo;
import com.jdp.hls.model.entiy.SupervisePayInfo;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface StatisticsPayOwnerListContract {
    interface View extends BaseView {
        void onGetPayOwnListListSuccess(PayOwnerListInfo payOwnerListInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getPayOwnList(int BuildingType, int UseItemId);
    }
}
