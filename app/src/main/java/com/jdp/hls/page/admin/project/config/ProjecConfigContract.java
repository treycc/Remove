package com.jdp.hls.page.admin.project.config;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ConfigCompany;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ProjecConfigContract {
    interface View extends BaseView {
        void onGetProjectConfigSuccess(List<ConfigCompany> companyList);

        void onModifyProjectConfigSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getProjectConfig(String projectId);

        void modifyProjectConfig(RequestBody requestBody);
    }
}
