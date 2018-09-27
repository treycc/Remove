package com.jdp.hls.page.node.evaluate.personal;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.NodePersonalEvaluate;
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
public class NodePersonalEvaluatePresenter implements NodePersonalEvaluateContract.Presenter {
    private UserApi mApi;
    private NodePersonalEvaluateContract.View mView;

    @Inject
    public NodePersonalEvaluatePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPersonalEvaluate(String houseId) {
        mApi.getApiService().getPersonalEvaluate(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<NodePersonalEvaluate>(mView) {
                    @Override
                    protected void onSuccess(NodePersonalEvaluate nodePersonalEvaluate) {
                        mView.onGetPersonalEvaluateSuccess(nodePersonalEvaluate);
                    }
                });
    }


    @Override
    public void modifyPersonalEvaluate(RequestBody rosterBody) {
        mApi.getApiService().modifyPersonalEvaluate(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyPersonalEvaluateSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodePersonalEvaluateContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}