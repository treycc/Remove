package com.jdp.hls.page.node.age.personal;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodePersonalAge;
import com.jdp.hls.model.entiy.NodePersonalMeasure;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodePersonalAgeContract {
    interface View extends BaseView {
        void onGetPersonalAgeSuccess(NodePersonalAge nodePersonalAge);
        void onModifyPersonalAgeSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getPersonalAge(String houseId);
        void modifyPersonalAge(RequestBody rosterBody);
    }
}
