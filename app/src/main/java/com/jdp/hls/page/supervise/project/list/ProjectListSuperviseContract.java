package com.jdp.hls.page.supervise.project.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ProjectSuperviseInfo;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ProjectListSuperviseContract {
    interface View extends BaseView {
        void onGetProjectSuperviseListSuccess(ProjectSuperviseInfo projectSuperviseInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getProjectSuperviseList(int pageSize, int pageIndex, int orderBy);
    }
}
