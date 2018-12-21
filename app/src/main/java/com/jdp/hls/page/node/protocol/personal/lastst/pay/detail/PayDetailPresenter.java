package com.jdp.hls.page.node.protocol.personal.lastst.pay.detail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.PayItem;
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
public class PayDetailPresenter implements PayDetailContract.Presenter {
    private UserApi mApi;
    private PayDetailContract.View mView;

    @Inject
    public PayDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPayDetail(int Id) {
        mApi.getApiService().getPayDetail(Id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<PayItem>(mView) {
                    @Override
                    protected void onSuccess(PayItem payItemList) {
                        mView.onGetPayDetailSuccess(payItemList);
                    }
                });
    }

    @Override
    public void savePay(RequestBody requestBody) {
        mApi.getApiService().savePay(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<PayItem>(mView) {
                    @Override
                    protected void onSuccess(PayItem payItemList) {
                        mView.onSavePaySuccess(payItemList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull PayDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}