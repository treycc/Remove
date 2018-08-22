package com.jdp.hls.page.modifyPassword;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;

import retrofit2.http.Field;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ModifyPasswordContract {
    interface View extends BaseView {
        void onModifyPasswordSuccess(String newPassword);
    }

    interface Presenter extends BasePresenter<View> {
        void modifyPassword(int employeeId, String oldPassword, String newPassword);
    }
}
