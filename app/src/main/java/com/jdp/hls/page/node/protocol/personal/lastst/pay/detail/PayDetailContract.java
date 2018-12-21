package com.jdp.hls.page.node.protocol.personal.lastst.pay.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.PayItem;

import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface PayDetailContract {
    interface View extends BaseView {
        void onGetPayDetailSuccess(PayItem payItem);

        void onSavePaySuccess(PayItem payItem);
    }

    interface Presenter extends BasePresenter<View> {
        void getPayDetail(int Id);

        void savePay(RequestBody requestBody);
    }
}
