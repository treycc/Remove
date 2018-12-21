package com.jdp.hls.page.node.protocol.personal.lastst.pay.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.EmployeeDetail;
import com.jdp.hls.model.entiy.PayItem;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface PayListContract {
    interface View extends BaseView {
        void onGetPayListSuccess(List<PayItem> payItemList);
        void onDeletePaySuccess(PayItem payItem);
    }

    interface Presenter extends BasePresenter<View> {
        void getPayList(String buildingId, String buildingType);
        void deletePay( int Id,PayItem payItem);
    }
}
