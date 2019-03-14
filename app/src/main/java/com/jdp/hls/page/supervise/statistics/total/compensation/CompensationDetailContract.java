package com.jdp.hls.page.supervise.statistics.total.compensation;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.TitleValue;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface CompensationDetailContract {
    interface View extends BaseView {
        void onGetCompensationDetailSuccess(List<TitleValue> titleValueList);
    }

    interface Presenter extends BasePresenter<View> {
        void getCompensationDetail( int itemId);
    }
}
