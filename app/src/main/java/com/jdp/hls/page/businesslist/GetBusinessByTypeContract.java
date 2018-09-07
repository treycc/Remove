package com.jdp.hls.page.businesslist;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Business;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface GetBusinessByTypeContract {
    interface View extends BaseView {
        void onGetBusinessByTypeSuccess(List<Business> businesses);
    }

    interface Presenter extends BasePresenter<View> {
        void getBusinessListByType(String projectId, int buildingType, int taskType);
    }
}
