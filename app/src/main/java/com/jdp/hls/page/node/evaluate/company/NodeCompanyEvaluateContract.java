package com.jdp.hls.page.node.evaluate.company;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodeCompanyEvaluate;
import com.jdp.hls.model.entiy.NodePersonalEvaluate;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodeCompanyEvaluateContract {
    interface View extends BaseView {
        void onGetCompanyEvaluateSuccess(NodeCompanyEvaluate nodeCompanyEvaluate);
        void onModifyCompanyEvaluateSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getCompanyEvaluate(String houseId);
        void modifyCompanyEvaluate(RequestBody rosterBody);
    }
}
