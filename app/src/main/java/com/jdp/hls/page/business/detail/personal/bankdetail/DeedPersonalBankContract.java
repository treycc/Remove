package com.jdp.hls.page.business.detail.personal.bankdetail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.BankInfo;
import com.jdp.hls.model.entiy.DeedPersonalBank;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeedPersonalBankContract {
    interface View extends BaseView {
        void onGetDeedPersonalBankSuccess(DeedPersonalBank deedPersonalBank);

        void onAddDeedPersonalBankSuccess(BankInfo bankInfo);

        void onModifyDeedPersonalBankSuccess(BankInfo bankInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getDeedPersonalBank(String houseId);

        void addDeedPersonalBank(RequestBody rosterBody);

        void modifyDeedPersonalBank(RequestBody rosterBody);
    }
}
