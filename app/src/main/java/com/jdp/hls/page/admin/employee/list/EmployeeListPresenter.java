package com.jdp.hls.page.admin.employee.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.EmployeeDetail;
import com.jdp.hls.model.entiy.LoadSirObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EmployeeListPresenter implements EmployeeListContract.Presenter {
    private UserApi mApi;
    private EmployeeListContract.View mView;

    @Inject
    public EmployeeListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getEmployeeList(int pageIndex, int pageSize) {
        mApi.getApiService().getEmployeeList(pageIndex, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<EmployeeDetail>(mView) {
                    @Override
                    protected void onSuccess(EmployeeDetail employeeDetail) {
                        mView.onGetEmployeeListSuccess(employeeDetail);
                    }
                });
    }


    @Override
    public void attachView(@NonNull EmployeeListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}