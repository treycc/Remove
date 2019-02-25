package com.jdp.hls.page.rosterdetail.detail.personal;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.RosterDetail;
import com.jdp.hls.model.entiy.RosterPersonalDetail;
import com.jdp.hls.model.entiy.resultdata.RosterResult;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface RosterPersonalDetailContract {
    interface View extends BaseView {
        void onGetRosterPersonalDetailSuccess(RosterPersonalDetail rosterPersonalDetail);

        void onSaveRosterHouseSuccess(RosterResult rosterResult);
    }

    interface Presenter extends BasePresenter<View> {
        void getRosterPersonalDetail(String buildingId);

        void saveRosterHouse(RequestBody requestBody);
    }
}
