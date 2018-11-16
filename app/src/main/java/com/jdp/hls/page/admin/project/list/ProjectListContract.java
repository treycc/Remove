package com.jdp.hls.page.admin.project.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ProjectListInfo;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ProjectListContract {
    interface View extends BaseView {
        void onGetProjectListSuccess(ProjectListInfo projectListInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getProjectList();
    }
}
