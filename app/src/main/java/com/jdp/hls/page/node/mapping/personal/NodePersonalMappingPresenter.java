package com.jdp.hls.page.node.mapping.personal;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.NodePersonalMapping;
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
public class NodePersonalMappingPresenter implements NodePersonalMappingContract.Presenter {
    private UserApi mApi;
    private NodePersonalMappingContract.View mView;

    @Inject
    public NodePersonalMappingPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPersonalMapping(String houseId) {
        mApi.getApiService().getPersonalMapping(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<NodePersonalMapping>(mView) {
                    @Override
                    protected void onSuccess(NodePersonalMapping nodePersonalMapping) {
                        mView.onGetPersonalMappingSuccess(nodePersonalMapping);
                    }
                });
    }


    @Override
    public void modifyPersonalMapping(RequestBody rosterBody) {
        mApi.getApiService().modifyPersonalMapping(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyPersonalMappingSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodePersonalMappingContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}