package com.jdp.hls.page.admin.group.member;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.EmployeeDetail;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface MemberSelectContract {
    interface View extends BaseView {
        void onGetEmployeeByCompanySuccess(EmployeeDetail employeeDetail);
    }

    interface Presenter extends BasePresenter<View> {
        void getEmployeeByCompany(RequestBody requestBody);
    }
}
