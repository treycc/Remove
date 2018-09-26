package com.jdp.hls.page.business.basic.personla;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.BaiscPersonal;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BaiscPersonalContract {
    interface View extends BaseView {
        void onGetPersonalBasicSuccess(BaiscPersonal baiscPersonal);
    }

    interface Presenter extends BasePresenter<View> {
        void getPersonalBasic(String buildingId);
    }
}
