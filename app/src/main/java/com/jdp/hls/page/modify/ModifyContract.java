package com.jdp.hls.page.modify;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ModifyContract {
    interface View extends BaseView {
        void onModifyAliasSuccess(String aliasName);
        void onModifyMobileSuccess(String mobile);
    }

    interface Presenter extends BasePresenter<View> {
        void modifyAlias(int employeeId, String aliasName);

        void modifyMobile(int employeeId, String mobile);
    }
}
