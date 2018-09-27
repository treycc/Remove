package com.jdp.hls.page.node.evaluate.personal;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodePersonalEvaluate;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodePersonalEvaluateContract {
    interface View extends BaseView {
        void onGetPersonalEvaluateSuccess(NodePersonalEvaluate nodePersonalEvaluate);
        void onModifyPersonalEvaluateSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getPersonalEvaluate(String houseId);
        void modifyPersonalEvaluate(RequestBody rosterBody);
    }
}
