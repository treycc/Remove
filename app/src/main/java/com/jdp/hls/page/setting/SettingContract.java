package com.jdp.hls.page.setting;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface SettingContract {
    interface View extends BaseView {
        void onLogoutSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void logout();
    }
}
