package com.jdp.hls.page.admin.employee.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.EmployeeDetail;
import com.jdp.hls.model.entiy.PublicityItem;

import okhttp3.RequestBody;
import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface EmployeeListContract {
    interface View extends BaseView {
        void onGetEmployeeListSuccess(EmployeeDetail employeeDetail);
    }

    interface Presenter extends BasePresenter<View> {
        void getEmployeeList( int pageIndex,  int pageSize);
    }
}
