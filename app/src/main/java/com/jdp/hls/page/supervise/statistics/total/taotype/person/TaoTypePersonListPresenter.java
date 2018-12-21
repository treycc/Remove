package com.jdp.hls.page.supervise.statistics.total.taotype.person;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.SupervisePayInfo;
import com.jdp.hls.model.entiy.TaoTypePerson;
import com.jdp.hls.page.supervise.statistics.total.pay.StatisticsPayListContract;

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
public class TaoTypePersonListPresenter implements TaoTypePersonListContract.Presenter {
    private UserApi mApi;
    private TaoTypePersonListContract.View mView;

    @Inject
    public TaoTypePersonListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getTaoTypePersonList(int Id) {
        mApi.getApiService().getTaoTypePersonList(Id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<TaoTypePerson>>(mView) {
                    @Override
                    protected void onSuccess(List<TaoTypePerson> taoTypePersonList) {
                        mView.onGetTaoTypePersonListSuccess(taoTypePersonList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull TaoTypePersonListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}