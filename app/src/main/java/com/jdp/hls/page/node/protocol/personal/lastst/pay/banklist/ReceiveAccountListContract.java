package com.jdp.hls.page.node.protocol.personal.lastst.pay.banklist;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.BankListInfo;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ReceiveAccountListContract {
    interface View extends BaseView {
        void onGetBrankListSuccess(BankListInfo brankListInfo);

    }

    interface Presenter extends BasePresenter<View> {
        void getBrankList(String buildingId);

    }
}
