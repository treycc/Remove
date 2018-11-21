package com.jdp.hls.page.admin.query;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ProjectItem;
import com.jdp.hls.model.entiy.ProjectListInfo;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ProjectSelectContract {
    interface View extends BaseView {
        void onGetProjectListSuccess(List<ProjectItem> projectItemList);
    }

    interface Presenter extends BasePresenter<View> {
        void getQueryProjectList();
    }
}
