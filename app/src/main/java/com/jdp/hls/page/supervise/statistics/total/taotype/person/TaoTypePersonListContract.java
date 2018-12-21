package com.jdp.hls.page.supervise.statistics.total.taotype.person;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.TaoTypePerson;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface TaoTypePersonListContract {
    interface View extends BaseView {
        void onGetTaoTypePersonListSuccess(List<TaoTypePerson> taoTypeList);
    }

    interface Presenter extends BasePresenter<View> {
        void getTaoTypePersonList( int Id);
    }
}
