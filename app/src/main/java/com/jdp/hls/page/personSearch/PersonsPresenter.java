package com.jdp.hls.page.personSearch;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.Person;
import com.jdp.hls.model.entiy.ResultObserver;

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
public class PersonsPresenter implements PersonsContract.Presenter {
    private UserApi mApi;
    private PersonsContract.View mView;

    @Inject
    public PersonsPresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull PersonsContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getPersons(String projectId) {
        mApi.getApiService().getPersons(projectId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<Person>>(mView) {
                    @Override
                    protected void onSuccess(List<Person> persons) {
                        mView.onGetPersonsSuccess(persons);
                    }
                });
    }

}