package com.jdp.hls.page.levy;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.LevyInfo;
import com.jdp.hls.model.entiy.Task;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface TaskContract {
    interface View extends BaseView {
        void onGetTaskSuccess(LevyInfo levyInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getTask(String projectId,int buildingType);
        void refreshTask(String projectId,int buildingType);
    }
}
