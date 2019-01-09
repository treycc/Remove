package com.jdp.hls.page.admin.project.detail.payscheme;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.PaySchemeInfo;
import com.jdp.hls.model.entiy.ProjectItem;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface PaySchemeContract {
    interface View extends BaseView {
        void onGetPaySchemeSuccess(PaySchemeInfo paySchemeInfo);

        void onSavePaySchemeSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getPayScheme(String projectId);

        void savePayScheme(RequestBody requestBody);
    }
}
