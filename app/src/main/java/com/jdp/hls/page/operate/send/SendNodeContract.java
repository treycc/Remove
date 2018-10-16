package com.jdp.hls.page.operate.send;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ReceivePerson;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface SendNodeContract {
    interface View extends BaseView {
        void onGetNextNodePersonNameSuccess(ReceivePerson receivePerson);

        void onSendNodeSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getNextNodePersonName(String buildingId, String buildingType);

        void sendNode(RequestBody requestBody);
    }
}
