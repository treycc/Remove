package com.jdp.hls.page.projects;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.ProjectAreaInfo;
import com.jdp.hls.model.entiy.AreaSelectorItem;
import com.jdp.hls.model.entiy.Project;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Query;

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

        void onGetAuthAreaListSuccess(List<AreaSelectorItem> areaSelectorItemList, int parentId,AreaSelectorItem areaSelectorItem);
    }

    interface Presenter extends BasePresenter<View> {
        void getProjects(int userId);

        void getAuthAreaList(int parentId, int level, AreaSelectorItem areaSelectorItem);

        void switchProject(RequestBody requestBody, Project project);
    }
}
