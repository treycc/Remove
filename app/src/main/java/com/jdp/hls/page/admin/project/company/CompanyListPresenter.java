package com.jdp.hls.page.admin.project.company;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.Company;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CompanyListPresenter implements CompanyListContract.Presenter {
    private UserApi mApi;
    private CompanyListContract.View mView;

    @Inject
    public CompanyListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getCompanyList(int companyTypeId) {
        mApi.getApiService().getCompanyList(companyTypeId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<Company>>(mView) {
                    @Override
                    protected void onSuccess(List<Company> companyList) {
                        mView.onGetCompanyListSuccess(companyList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull CompanyListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}