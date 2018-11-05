package com.jdp.hls.page.deed.company.bank;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DeedCompanyLand;
import com.jdp.hls.model.entiy.DeedCompanyOpenAccountCert;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeedCompanyOpenAccountCertContract {
    interface View extends BaseView {
        void onGetDeedCompanyOpenAccountCertSuccess(DeedCompanyOpenAccountCert deedCompanyOpenAccountCert);

        void onAddDeedCompanyOpenAccountCertSuccess();

        void onModifyDeedCompanyOpenAccountCertSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getDeedCompanyOpenAccountCert(String enterpriseId);

        void addDeedCompanyOpenAccountCert(RequestBody rosterBody);

        void modifyDeedCompanyOpenAccountCert(RequestBody rosterBody);
    }
}
