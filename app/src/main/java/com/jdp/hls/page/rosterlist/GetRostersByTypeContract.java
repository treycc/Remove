package com.jdp.hls.page.rosterlist;


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
public interface GetRostersByTypeContract {
    interface View extends BaseView {
        void onGetRosterListByTypeSuccess(List<Roster> rosters);
    }

    interface Presenter extends BasePresenter<View> {
        void getRosterListByType(String projectId, int employeeId,int isEnterprise);
    }
}
