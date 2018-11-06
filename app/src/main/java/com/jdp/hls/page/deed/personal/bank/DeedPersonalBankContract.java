package com.jdp.hls.page.deed.personal.bank;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DeedPersonalBank;
import com.jdp.hls.model.entiy.DeedPersonalImmovable;

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

        void onAddDeedPersonalBankSuccess();

        void onModifyDeedPersonalBankSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getDeedPersonalBank(String houseId);

        void addDeedPersonalBank(RequestBody rosterBody);

        void modifyDeedPersonalBank(RequestBody rosterBody);
    }
}
