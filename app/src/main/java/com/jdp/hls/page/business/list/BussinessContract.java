package com.jdp.hls.page.business.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Business;
import com.jdp.hls.model.entiy.TaskInfo;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BussinessContract {
    interface View extends BaseView {
        void onGetBusinessSuccess(TaskInfo taskInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getBusinessList(String projectId, int buildingType, int taskType);
    }
}
