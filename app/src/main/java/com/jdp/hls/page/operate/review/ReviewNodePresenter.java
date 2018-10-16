package com.jdp.hls.page.operate.review;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ReceivePerson;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.page.operate.delete.DeleteNodeContract;

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
public class ReviewNodePresenter implements ReviewNodeContract.Presenter {
    private UserApi mApi;
    private ReviewNodeContract.View mView;

    @Inject
    public ReviewNodePresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull ReviewNodeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }


    @Override
    public void reviewNode(RequestBody requestBody) {
        mApi.getApiService().reviewNode(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onReviewNodeSuccess();
                    }
                });
    }

    @Override
    public void getReviewReceiverList(String buildingId, String buildingType) {
        mApi.getApiService().getReviewReceiverList(buildingId, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<ReceivePerson>>(mView) {
                    @Override
                    protected void onSuccess(List<ReceivePerson> receivePersonList) {
                        mView.onGetReviewReceiverListSuccess(receivePersonList);
                    }
                });
    }
}