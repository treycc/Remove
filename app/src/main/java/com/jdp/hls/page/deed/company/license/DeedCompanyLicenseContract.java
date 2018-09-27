package com.jdp.hls.page.deed.company.license;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DeedCompanyLand;
import com.jdp.hls.model.entiy.DeedCompanyLicense;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeedCompanyLicenseContract {
    interface View extends BaseView {
        void onGetDeedCompanyLicenseSuccess(DeedCompanyLicense deedCompanyLicense);

        void onAddDeedCompanyLicenseSuccess();

        void onModifyDeedCompanyLicenseSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getDeedCompanyLicense(String enterpriseId);

        void addDeedCompanyLicense(RequestBody rosterBody);

        void modifyDeedCompanyLicense(RequestBody rosterBody);
    }
}
