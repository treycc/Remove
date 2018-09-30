package com.jdp.hls.page.node.evaluate.company;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.NodeCompanyEvaluate;
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
public class NodeCompanyEvaluatePresenter implements NodeCompanyEvaluateContract.Presenter {
    private UserApi mApi;
    private NodeCompanyEvaluateContract.View mView;

    @Inject
    public NodeCompanyEvaluatePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getCompanyEvaluate(String houseId) {
        mApi.getApiService().getCompanyEvaluate(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<NodeCompanyEvaluate>(mView) {
                    @Override
                    protected void onSuccess(NodeCompanyEvaluate nodeCompanyEvaluate) {
                        mView.onGetCompanyEvaluateSuccess(nodeCompanyEvaluate);
                    }
                });
    }


    @Override
    public void modifyCompanyEvaluate(RequestBody rosterBody) {
        mApi.getApiService().modifyCompanyEvaluate(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyCompanyEvaluateSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodeCompanyEvaluateContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}