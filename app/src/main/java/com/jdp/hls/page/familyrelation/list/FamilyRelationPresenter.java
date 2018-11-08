package com.jdp.hls.page.familyrelation.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.FamilyRelation;
import com.jdp.hls.model.entiy.LoadSirObserver;
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
public class FamilyRelationPresenter implements FamilyRelationContract.Presenter {
    private UserApi mApi;
    private FamilyRelationContract.View mView;

    @Inject
    public FamilyRelationPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getFamilyRelation(String houseId) {
        mApi.getApiService().getFamilyRelation(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<FamilyRelation>(mView) {
                    @Override
                    protected void onSuccess(FamilyRelation familyRelation) {
                        mView.onGetFamilyRelationSuccess(familyRelation);
                    }
                });
    }

    @Override
    public void deleteFamilyRemember(RequestBody requestBody,int position) {
        mApi.getApiService().deleteFamilyRemember(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onDeleteFamilyRememberSuccess(position);
                    }
                });
    }

    @Override
    public void modifyFamilyRelation(RequestBody requestBody) {
        mApi.getApiService().modifyFamilyRelation(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyFamilyRelationSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull FamilyRelationContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}