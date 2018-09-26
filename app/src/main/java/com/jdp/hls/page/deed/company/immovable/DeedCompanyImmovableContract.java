package com.jdp.hls.page.deed.company.immovable;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DeedCompanyImmovable;
import com.jdp.hls.model.entiy.DeedCompanyLand;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeedCompanyImmovableContract {
    interface View extends BaseView {
        void onGetDeedCompanyImmovableSuccess(DeedCompanyImmovable deedCompanyImmovable);

        void onAddDeedCompanyImmovableSuccess();

        void onModifyDeedCompanyImmovableSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getDeedCompanyImmovable(String enterpriseId);

        void addDeedCompanyImmovable(RequestBody rosterBody);

        void modifyDeedCompanyImmovable(RequestBody rosterBody);
    }
}
