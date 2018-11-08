package com.jdp.hls.page.node.evaluate.company.moneyevaluate;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.NodeCompanyMoneyEvaluate;
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
public class NodeCompanyMoneyEvaluatePresenter implements NodeCompanyMoneyEvaluateContract.Presenter {
    private UserApi mApi;
    private NodeCompanyMoneyEvaluateContract.View mView;

    @Inject
    public NodeCompanyMoneyEvaluatePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getCompanyMoneyevaluate(String houseId) {
        mApi.getApiService().getCompanyMoneyevaluate(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<NodeCompanyMoneyEvaluate>(mView) {
                    @Override
                    protected void onSuccess(NodeCompanyMoneyEvaluate nodeCompanyMoneyEvaluate) {
                        mView.onGetCompanyMoneyevaluateSuccess(nodeCompanyMoneyEvaluate);
                    }
                });
    }


    @Override
    public void modifyCompanyMoneyevaluate(RequestBody rosterBody) {
        mApi.getApiService().modifyCompanyMoneyevaluate(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyCompanyMoneyevaluateSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodeCompanyMoneyEvaluateContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}