package com.jdp.hls.page.admin.employee.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Employee;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface EmployeeDetailContract {
    interface View extends BaseView {
        void onModifyEmployeeSuccess(Employee employee);

        void onGetEmployeeDetailSuccess(Employee employee);
    }

    interface Presenter extends BasePresenter<View> {

        void modifyEmployee(RequestBody requestBody);

        void getEmployeeDetail(int employeeId);
    }
}
