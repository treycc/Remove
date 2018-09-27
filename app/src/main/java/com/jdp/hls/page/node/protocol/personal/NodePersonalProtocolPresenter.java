package com.jdp.hls.page.node.protocol.personal;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.NodePersonalProtocol;
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
public class NodePersonalProtocolPresenter implements NodePersonalProtocolContract.Presenter {
    private UserApi mApi;
    private NodePersonalProtocolContract.View mView;

    @Inject
    public NodePersonalProtocolPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPersonalProtocol(String houseId) {
        mApi.getApiService().getPersonalProtocol(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<NodePersonalProtocol>(mView) {
                    @Override
                    protected void onSuccess(NodePersonalProtocol nodePersonalProtocol) {
                        mView.onGetPersonalProtocolSuccess(nodePersonalProtocol);
                    }
                });
    }


    @Override
    public void modifyPersonalProtocol(RequestBody rosterBody) {
        mApi.getApiService().modifyPersonalProtocol(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyPersonalProtocolSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodePersonalProtocolContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}