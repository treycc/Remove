package com.jdp.hls.page.geography;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Roster;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface GetGeographyRosterListContract {
    interface View extends BaseView {
        void onGetGeographyRosterListSuccess(List<Roster> rosterList);
    }

    interface Presenter extends BasePresenter<View> {
        void getGeographyRosterList();
    }
}
