package com.jdp.hls.page.admin.project.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ProjectItem;
import com.jdp.hls.model.entiy.ProjectListInfo;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ProjecDetailContract {
    interface View extends BaseView {
        void onGetProjectDetailSuccess(ProjectItem projectItem);

        void onAddProjectSuccess(ProjectItem projectItem);

        void onSaveProjectSuccess(ProjectItem projectItem);
    }

    interface Presenter extends BasePresenter<View> {
        void getProjectDetail(String projectId);

        void addProject(RequestBody requestBody);

        void saveProject(RequestBody requestBody);
    }
}
