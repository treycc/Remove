package com.jdp.hls.page.node.age.personal;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.NodePersonalAge;
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
public class NodePersonalAgePresenter implements NodePersonalAgeContract.Presenter {
    private UserApi mApi;
    private NodePersonalAgeContract.View mView;

    @Inject
    public NodePersonalAgePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPersonalAge(String houseId) {
        mApi.getApiService().getPersonalAge(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<NodePersonalAge>(mView) {
                    @Override
                    protected void onSuccess(NodePersonalAge nodePersonalAge) {
                        mView.onGetPersonalAgeSuccess(nodePersonalAge);
                    }
                });
    }


    @Override
    public void modifyPersonalAge(RequestBody rosterBody) {
        mApi.getApiService().modifyPersonalAge(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyPersonalAgeSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodePersonalAgeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}