package com.jdp.hls.page.admin.contrast;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ProjectFacade;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ProjectContrastDetailContract {
    interface View extends BaseView {
        void onSaveVrInfoSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void saveVrInfo(RequestBody requestBody);
    }
}
