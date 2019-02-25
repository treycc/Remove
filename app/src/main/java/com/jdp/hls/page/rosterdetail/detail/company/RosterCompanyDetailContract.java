package com.jdp.hls.page.rosterdetail.detail.company;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.RosterCompanyDetail;
import com.jdp.hls.model.entiy.resultdata.RosterResult;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface RosterCompanyDetailContract {
    interface View extends BaseView {
        void onGetRosterCompanyDetailSuccess(RosterCompanyDetail rosterCompanyDetail);

        void onSaveRosterCompanySuccess(RosterResult rosterResult);
    }

    interface Presenter extends BasePresenter<View> {
        void getRosterCompanyDetail(String entId);

        void saveRosterCompany(RequestBody requestBody);
    }
}
