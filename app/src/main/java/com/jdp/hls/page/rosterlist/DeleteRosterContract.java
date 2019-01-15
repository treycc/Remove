package com.jdp.hls.page.rosterlist;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Roster;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeleteRosterContract {
    interface View extends BaseView {
        void onDeleteRosterSuccess(Roster roster, int position);
    }

    interface Presenter extends BasePresenter<View> {

        void deleteRoster(RequestBody requestBody, Roster roster, int position);
    }
}
