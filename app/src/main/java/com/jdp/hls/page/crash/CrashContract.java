package com.jdp.hls.page.crash;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Login;

import retrofit2.http.Field;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface CrashContract {
    interface View extends BaseView {
        void onUploadErrorSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void uploadError(String iP, String applicationType, String oSversion, String exceptionType, String exceptionMsg,
                         String employeeId);
    }
}
