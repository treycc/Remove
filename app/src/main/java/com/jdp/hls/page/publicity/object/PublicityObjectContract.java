package com.jdp.hls.page.publicity.object;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.PublicityObject;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface PublicityObjectContract {
    interface View extends BaseView {
        void onGetPublicityObjectSuccess(List<PublicityObject> publicityObjects);
    }

    interface Presenter extends BasePresenter<View> {
        void getPublicityObject(int buildingType, int publicityType);
    }
}
