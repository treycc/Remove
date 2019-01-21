package com.jdp.hls.page.projects;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.ProjectAreaInfo;
import com.jdp.hls.model.entiy.Project;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ProjectsAreaContract {
    interface View extends BaseView {
        void onGetProjectsSuccess(ProjectAreaInfo projectAreaInfo);

        void onSwitchProjectSuccess(Project project);
    }

    interface Presenter extends BasePresenter<View> {
        void getProjects(int userId);

        void switchProject(RequestBody requestBody, Project project);
    }
}
