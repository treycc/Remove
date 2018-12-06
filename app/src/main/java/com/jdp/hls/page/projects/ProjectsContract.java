package com.jdp.hls.page.projects;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Project;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ProjectsContract {
    interface View extends BaseView {
        void onGetProjectsSuccess(List<Project> projects);

        void onSwitchProjectSuccess(Project project);
    }

    interface Presenter extends BasePresenter<View> {
        void getProjects(int userId);

        void switchProject(RequestBody requestBody,Project project);
    }
}
