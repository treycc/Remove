package com.jdp.hls.page.admin.group.detail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.Group;
import com.jdp.hls.model.entiy.GroupDetail;
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
public class GroupDetailPresenter implements GroupDetailContract.Presenter {
    private UserApi mApi;
    private GroupDetailContract.View mView;

    @Inject
    public GroupDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getGroupDetail(String projectId, int groupId) {
        mApi.getApiService().getGroupDetail(projectId, groupId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<GroupDetail>(mView) {
                    @Override
                    protected void onSuccess(GroupDetail groupDetail) {
                        mView.onGetGroupDetailSuccess(groupDetail);
                    }
                });
    }

    @Override
    public void saveGroupInfo(RequestBody requestBody) {
        mApi.getApiService().saveGroupInfo(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Group>(mView) {
                    @Override
                    protected void onSuccess(Group group) {
                        mView.onSaveGroupInfoSuccess(group);
                    }
                });
    }


    @Override
    public void attachView(@NonNull GroupDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}