package com.jdp.hls.page.deed.personal.immovable;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
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

        void onAddDeedPersonalImmovableSuccess();

        void onModifyDeedPersonalImmovableSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getDeedPersonalImmovable(String houseId);

        void addDeedPersonalImmovable(RequestBody rosterBody);

        void modifyDeedPersonalImmovable(RequestBody rosterBody);
    }
}
