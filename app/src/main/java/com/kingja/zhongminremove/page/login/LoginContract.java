package com.kingja.zhongminremove.page.login;


import com.kingja.zhongminremove.base.BasePresenter;
import com.kingja.zhongminremove.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface LoginContract {
    interface View extends BaseView {
        void onLoginSuccess(Login account);
    }

    interface Presenter extends BasePresenter<View> {
        void login(String mobile, String password);
    }
}
