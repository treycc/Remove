package com.jdp.hls.page.node.measure.personal;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodePersonalMeasure;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodePersonalMeasureContract {
    interface View extends BaseView {
        void onGetPersonalMeasureSuccess(NodePersonalMeasure nodePersonalMeasure);
        void onModifyPersonalMeasureSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getPersonalMeasure(String houseId);
        void modifyPersonalMeasure(RequestBody rosterBody);
    }
}
