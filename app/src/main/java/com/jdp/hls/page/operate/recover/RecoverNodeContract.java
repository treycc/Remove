package com.jdp.hls.page.operate.recover;


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
public interface RecoverNodeContract {
    interface View extends BaseView {
        void onGetReviewRecoverListSuccess(List<ReceivePerson> receivePersonList);
    }

    interface Presenter extends BasePresenter<View> {
        void getRecoverReceiverList(String buildingId, String buildingType);
    }
}
