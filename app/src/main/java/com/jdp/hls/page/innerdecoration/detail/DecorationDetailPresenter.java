package com.jdp.hls.page.innerdecoration.detail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DecorationItem;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.page.innerdecoration.list.DecorationListContract;

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
public class DecorationDetailPresenter implements DecorationDetailContract.Presenter {
    private UserApi mApi;
    private DecorationDetailContract.View mView;

    @Inject
    public DecorationDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void addDecoration(RequestBody requestBody) {
        mApi.getApiService().addDecoration(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<DecorationItem>(mView) {
                    @Override
                    protected void onSuccess(DecorationItem decorationItem) {
                        mView.onAddDecorationSuccess(decorationItem);
                    }
                });
    }

    @Override
    public void modifyDecoration(RequestBody requestBody) {
        mApi.getApiService().modifyDecoration(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<DecorationItem>(mView) {
                    @Override
                    protected void onSuccess(DecorationItem decorationItem) {
                        mView.onModifyDecorationSuccess( decorationItem);
                    }
                });
    }


    @Override
    public void attachView(@NonNull DecorationDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}