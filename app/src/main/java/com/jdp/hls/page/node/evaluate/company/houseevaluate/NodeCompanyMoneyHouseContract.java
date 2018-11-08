package com.jdp.hls.page.node.evaluate.company.houseevaluate;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodeCompanyHouseEvaluate;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodeCompanyMoneyHouseContract {
    interface View extends BaseView {
        void onGetCompanyHouseEvaluateSuccess(NodeCompanyHouseEvaluate nodeCompanyHouseEvaluate);

        void onModifyCompanyHouseEvaluateSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getCompanyHouseEvaluate(String enterpriseId);

        void modifyCompanyHouseEvaluate(RequestBody rosterBody);
    }
}
