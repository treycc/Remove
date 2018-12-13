package com.jdp.hls.page.supervise.project.contrast;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ProjectFacade;
import com.jdp.hls.model.entiy.ProjectSuperviseDetail;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ProjectContrastContract {
    interface View extends BaseView {
        void onGetProjectPhotoSuccess(ProjectFacade projectFacade);
    }

    interface Presenter extends BasePresenter<View> {
        void getProjectPhoto(String projectId);
    }
}
