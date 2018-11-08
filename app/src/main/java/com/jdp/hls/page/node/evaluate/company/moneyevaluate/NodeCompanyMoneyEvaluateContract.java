package com.jdp.hls.page.node.evaluate.company.moneyevaluate;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodeCompanyMoneyEvaluate;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodeCompanyMoneyEvaluateContract {
    interface View extends BaseView {
        void onGetCompanyMoneyevaluateSuccess(NodeCompanyMoneyEvaluate nodeCompanyMoneyEvaluate);
        void onModifyCompanyMoneyevaluateSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getCompanyMoneyevaluate(String enterpriseId);
        void modifyCompanyMoneyevaluate(RequestBody rosterBody);
    }
}
