package com.jdp.hls.page.deed.personal.immovable;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DeedItem;
import com.jdp.hls.model.entiy.DeedPersonalImmovable;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeedPersonalImmovableContract {
    interface View extends BaseView {
        void onGetDeedPersonalImmovableSuccess(DeedPersonalImmovable deedPersonalImmovable);

        void onSaveDeedPersonalImmovableSuccess(DeedItem deedItem);

    }

    interface Presenter extends BasePresenter<View> {
        void getDeedPersonalImmovableDetail(int certId);

        void saveDeedPersonalImmovable(RequestBody rosterBody);

    }
}
