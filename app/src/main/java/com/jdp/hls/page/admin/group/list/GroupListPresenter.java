package com.jdp.hls.page.admin.group.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.EmployeeDetail;
import com.jdp.hls.model.entiy.Group;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.page.admin.group.member.MemberSelectContract;

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
public class GroupListPresenter implements GroupListContract.Presenter {
    private UserApi mApi;
    private GroupListContract.View mView;

    @Inject
    public GroupListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getGroupList(String projectId) {
        mApi.getApiService().getGroupList(projectId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<Group>>(mView) {
                    @Override
                    protected void onSuccess(List<Group> groupList) {
                        mView.onGetGroupListSuccess(groupList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull GroupListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}