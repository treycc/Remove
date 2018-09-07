package com.jdp.hls.page.businesslist;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.page.rosterlist.GetRostersByTypeContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetBusinessByTypePresenter implements GetRostersByTypeContract.Presenter {
    private UserApi mApi;
    private GetRostersByTypeContract.View mView;

    @Inject
    public GetBusinessByTypePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getRosterListByType(String projectId, int employeeId,int isEnterprise) {
        mApi.getApiService().getRosterListByType(projectId, employeeId,isEnterprise).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<Roster>>(mView) {
                    @Override
                    protected void onSuccess(List<Roster> rosters) {
                        mView.onGetRosterListByTypeSuccess(rosters);
                    }
                });
    }


    @Override
    public void attachView(@NonNull GetRostersByTypeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}