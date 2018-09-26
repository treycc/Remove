package com.jdp.hls.page.deed.personal.property;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DeedPersonalProperty;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeedPersonalPropertyContract {
    interface View extends BaseView {
        void onGetDeedPersonalPropertySuccess(DeedPersonalProperty deedPersonalProperty);

        void onAddDeedPersonalPropertySuccess();

        void onModifyDeedPersonalPropertySuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getDeedPersonalProperty(String houseId);

        void addDeedPersonalProperty(RequestBody rosterBody);

        void modifyDeedPersonalProperty(RequestBody rosterBody);
    }
}
