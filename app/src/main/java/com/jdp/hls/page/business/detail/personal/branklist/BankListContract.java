package com.jdp.hls.page.business.detail.personal.branklist;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.BankListInfo;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BankListContract {
    interface View extends BaseView {
        void onGetBrankListSuccess(BankListInfo brankListInfo);

        void onDeleteBankInfo(int position);
    }

    interface Presenter extends BasePresenter<View> {
        void getBrankList(String buildingId);

        void deleteBankInfo(String id,int position);
    }
}
