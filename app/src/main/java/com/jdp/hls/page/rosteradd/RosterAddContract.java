package com.jdp.hls.page.rosteradd;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.RosterDetail;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface RosterAddContract {
    interface View extends BaseView {
        void onAddRosterSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void addRoster(RequestBody requestBody);
    }
}
