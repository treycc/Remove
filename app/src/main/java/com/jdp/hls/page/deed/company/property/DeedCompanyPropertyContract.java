package com.jdp.hls.page.deed.company.property;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DeedCompanyProperty;
import com.jdp.hls.model.entiy.DeedPersonalProperty;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeedCompanyPropertyContract {
    interface View extends BaseView {
        void onGetDeedCompanyPropertySuccess(DeedCompanyProperty deedCompanyProperty);

        void onAddDeedCompanyPropertySuccess();

        void onModifyDeedCompanyPropertySuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getDeedCompanyProperty(String houseId);

        void addDeedCompanyProperty(RequestBody rosterBody);

        void modifyDeedCompanyProperty(RequestBody rosterBody);
    }
}
