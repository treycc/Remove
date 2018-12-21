package com.jdp.hls.page.node.protocol.personal.lastst.pay.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.EmployeeDetail;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.PayItem;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.page.admin.employee.list.EmployeeListContract;

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
public class PayListPresenter implements PayListContract.Presenter {
    private UserApi mApi;
    private PayListContract.View mView;

    @Inject
    public PayListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPayList(String buildingId, String buildingType) {
        mApi.getApiService().getPayList(buildingId, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<PayItem>>(mView) {
                    @Override
                    protected void onSuccess(List<PayItem> payItemList) {
                        mView.onGetPayListSuccess(payItemList);
                    }
                });
    }

    @Override
    public void deletePay(int Id, PayItem payItem) {
        mApi.getApiService().deletePay(Id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object obj) {
                        mView.onDeletePaySuccess(payItem);
                    }
                });
    }


    @Override
    public void attachView(@NonNull PayListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}