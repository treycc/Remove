package com.jdp.hls.page.airphoto.person;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.AirPhotoPerson;
import com.jdp.hls.model.entiy.Business;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface AirPhotoPersonContract {
    interface View extends BaseView {
        void onGetAirPhotoPersonsSuccess(List<AirPhotoPerson> airPhotoPersonList);
    }

    interface Presenter extends BasePresenter<View> {
        void getAirPhotoPersons(String projectId);
    }
}
