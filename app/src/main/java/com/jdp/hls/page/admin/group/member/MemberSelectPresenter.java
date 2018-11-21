package com.jdp.hls.page.admin.group.member;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.EmployeeDetail;
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
public class MemberSelectPresenter implements MemberSelectContract.Presenter {
    private UserApi mApi;
    private MemberSelectContract.View mView;

    @Inject
    public MemberSelectPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getEmployeeByCompany(RequestBody requestBody) {
        mApi.getApiService().getEmployeeByCompany(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<EmployeeDetail>(mView) {
                    @Override
                    protected void onSuccess(EmployeeDetail employeeDetail) {
                        mView.onGetEmployeeByCompanySuccess(employeeDetail);
                    }
                });
    }


    @Override
    public void attachView(@NonNull MemberSelectContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}