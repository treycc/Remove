package com.jdp.hls.page.operate.recover;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ReceivePerson;
import com.jdp.hls.model.entiy.ResultObserver;

import java.util.List;

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
public class RecoverNodePresenter implements RecoverNodeContract.Presenter {
    private UserApi mApi;
    private RecoverNodeContract.View mView;

    @Inject
    public RecoverNodePresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull RecoverNodeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }


    @Override
    public void getRecoverReceiverList(String buildingId, String buildingType) {
        mApi.getApiService().getRecoverReceiverList(buildingId, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<ReceivePerson>>(mView) {
                    @Override
                    protected void onSuccess(List<ReceivePerson> receivePersonList) {
                        mView.onGetReviewRecoverListSuccess(receivePersonList);
                    }
                });
    }
}