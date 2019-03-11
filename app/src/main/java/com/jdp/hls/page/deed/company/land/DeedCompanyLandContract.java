package com.jdp.hls.page.deed.company.land;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DeedCompanyLand;
import com.jdp.hls.model.entiy.DeedItem;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeedCompanyLandContract {
    interface View extends BaseView {
        void onGetDeedCompanyLandSuccess(DeedCompanyLand deedCompanyLand);

        void onSaveDeedCompanyLandSuccess(DeedItem deedItem);

    }

    interface Presenter extends BasePresenter<View> {
        void getDeedCompanyLandDetail(int certId);

        void saveDeedCompanyLand(RequestBody rosterBody);

    }
}
