package com.jdp.hls.page.admin.contrast;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ProjectFacade;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ProjectContrastDetailContract {
    interface View extends BaseView {
        void onGetProjectPhotoSuccess(ProjectFacade projectFacade);
    }

    interface Presenter extends BasePresenter<View> {
        void getProjectPhoto(String projectId);
    }
}
