package com.jdp.hls.page.supervise.statistics.total;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.KeyValue;
import com.jdp.hls.model.entiy.StatisticsTotalInfo;
import com.jdp.hls.model.entiy.TitleItem;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface StatisticsTotalContract {
    interface View extends BaseView {
        void onGetStatisticsTotalSuccess(List<TitleItem> titleItemList);
    }

    interface Presenter extends BasePresenter<View> {
        void getStatisticsTotal(int buildingType);
    }
}
