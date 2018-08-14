package com.jdp.hls.page.rosterdetail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.RosterDetail;

import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface RosterDetailContract {
    interface View extends BaseView {
        void onGetRosterDetailSuccess(RosterDetail rosterDetail);
    }

    interface Presenter extends BasePresenter<View> {
        void getRosterDetail(String houseId, int employeeId, int isEnterprise);
    }
}
