package com.jdp.hls.page.deed.company.immovable;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DeedCompanyImmovable;
import com.jdp.hls.model.entiy.DeedCompanyLand;
import com.jdp.hls.model.entiy.DeedItem;

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

        void onSaveDeedCompanyImmovableSuccess(DeedItem deedItem);

    }

    interface Presenter extends BasePresenter<View> {
        void getDeedCompanyImmovableDetail(int  certId);

        void saveDeedCompanyImmovable(RequestBody rosterBody);

    }
}
