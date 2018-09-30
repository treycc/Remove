package com.jdp.hls.page.node.measure.company;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodeCompanyMeasure;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodeCompanyMeasureContract {
    interface View extends BaseView {
        void onGetCompanyMeasureSuccess(NodeCompanyMeasure nodeCompanyMeasure);

        void onModifyCompanyMeasureSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getCompanyMeasure(String houseId);

        void modifyCompanyMeasure(RequestBody rosterBody);
    }
}
