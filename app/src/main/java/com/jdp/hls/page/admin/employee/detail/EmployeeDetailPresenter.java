package com.jdp.hls.page.admin.employee.detail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EmployeeDetailPresenter implements EmployeeDetailContract.Presenter {
    private UserApi mApi;
    private EmployeeDetailContract.View mView;

    @Inject
    public EmployeeDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull EmployeeDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void modifyEmployee(RequestBody requestBody) {
        mApi.getApiService().modifyEmployee(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Employee>(mView) {
                    @Override
                    protected void onSuccess(Employee employee) {
                        mView.onModifyEmployeeSuccess(employee);
                    }
                });
    }

    @Override
    public void getEmployeeDetail(String employeeId) {
        mApi.getApiService().getEmployeeDetail(employeeId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<Employee>(mView) {
                    @Override
                    protected void onSuccess(Employee employee) {
                        mView.onGetEmployeeDetailSuccess(employee);
                    }
                });
    }

}