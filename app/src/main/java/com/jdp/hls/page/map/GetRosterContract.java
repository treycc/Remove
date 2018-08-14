package com.jdp.hls.page.map;


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
public interface GetRosterContract {
    interface View extends BaseView {
        void onGetRosterdSuccess(List<Roster> rosters);
    }

    interface Presenter extends BasePresenter<View> {
        void getRosterList(String projectId, int employeeId);
    }
}
