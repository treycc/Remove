package com.jdp.hls.page.business.detail.personal.bankdetail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.BankInfo;
import com.jdp.hls.model.entiy.DeedPersonalBank;
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
public class DeedPersonalBankPresenter implements DeedPersonalBankContract.Presenter {
    private UserApi mApi;
    private DeedPersonalBankContract.View mView;

    @Inject
    public DeedPersonalBankPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedPersonalBank(String houseId) {
        mApi.getApiService().getDeedPersonalBank(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedPersonalBank>(mView) {
                    @Override
                    protected void onSuccess(DeedPersonalBank deedPersonalBank) {
                        mView.onGetDeedPersonalBankSuccess(deedPersonalBank);
                    }
                });
    }

    @Override
    public void addDeedPersonalBank(RequestBody rosterBody) {
        mApi.getApiService().addDeedPersonalBank(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<BankInfo>(mView) {
                    @Override
                    protected void onSuccess(BankInfo bankInfo) {
                        mView.onAddDeedPersonalBankSuccess(bankInfo);
                    }
                });
    }

    @Override
    public void modifyDeedPersonalBank(RequestBody rosterBody) {
        mApi.getApiService().modifyDeedPersonalBank(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<BankInfo>(mView) {
                    @Override
                    protected void onSuccess(BankInfo bankInfo) {
                        mView.onModifyDeedPersonalBankSuccess(bankInfo);
                    }
                });
    }

    @Override
    public void attachView(@NonNull DeedPersonalBankContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}