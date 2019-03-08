package com.jdp.hls.page.business.deed.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.BankListInfo;
import com.jdp.hls.model.entiy.DeedListInfo;

import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeedListContract {
    interface View extends BaseView {
        void onGetDeedListSuccess(DeedListInfo deedListInfo);

        void onDeleteDeed(int position);
    }

    interface Presenter extends BasePresenter<View> {
        void getDeedList(String buildingId, int certType);

        void deleteDeed(String buildingId, int certId, int certType, int position);
    }
}
