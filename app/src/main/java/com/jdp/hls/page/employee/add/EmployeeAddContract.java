package com.jdp.hls.page.employee.add;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Employee;

import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface EmployeeAddContract {
    interface View extends BaseView {
        void onAddEmployeeSuccess(Employee employee);
    }

    interface Presenter extends BasePresenter<View> {

        void addEmployee(RequestBody requestBody);
    }
}
