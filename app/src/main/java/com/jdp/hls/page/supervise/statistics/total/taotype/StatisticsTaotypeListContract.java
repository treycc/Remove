package com.jdp.hls.page.supervise.statistics.total.taotype;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.SupervisePayInfo;
import com.jdp.hls.model.entiy.TaoType;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface StatisticsTaotypeListContract {
    interface View extends BaseView {
        void onGetSuperviseTaotypeListSuccess(List<TaoType> taoTypeList);
    }

    interface Presenter extends BasePresenter<View> {
        void getSuperviseTaotypeList();
    }
}
