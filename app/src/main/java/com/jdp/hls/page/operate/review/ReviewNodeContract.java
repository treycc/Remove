package com.jdp.hls.page.operate.review;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ReceivePerson;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ReviewNodeContract {
    interface View extends BaseView {
        void onReviewNodeSuccess();

        void onGetReviewReceiverListSuccess(List<ReceivePerson> receivePersonList);
    }

    interface Presenter extends BasePresenter<View> {

        void reviewNode(RequestBody requestBody);

        void getReviewReceiverList(String buildingId, String buildingType);
    }
}
