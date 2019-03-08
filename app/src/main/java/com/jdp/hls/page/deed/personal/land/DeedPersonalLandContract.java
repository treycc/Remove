package com.jdp.hls.page.deed.personal.land;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DeedItem;
import com.jdp.hls.model.entiy.DeedPersonalLand;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeedPersonalLandContract {
    interface View extends BaseView {
        void onGetDeedPersonalLandSuccess(DeedPersonalLand deedPersonalLand);

        void onSaveDeedPersonalLandSuccess(DeedItem deedItem);

    }

    interface Presenter extends BasePresenter<View> {
        void getDeedPersonalLandDetail(int certId);

        void saveDeedPersonalLand(RequestBody rosterBody);

    }
}
