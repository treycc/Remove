package com.jdp.hls.page.supervise.project.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ProjectSuperviseDetail;

import okhttp3.RequestBody;
import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ProjectDetailSuperviseContract {
    interface View extends BaseView {
        void onGetProjectSuperviseDetailSuccess(ProjectSuperviseDetail projectSuperviseDetail);
    }

    interface Presenter extends BasePresenter<View> {
        void getProjectSuperviseDetail(String projectId);
    }
}
