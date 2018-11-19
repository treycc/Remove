package com.jdp.hls.page.admin.message.notification;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.Notification;
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
public class NotificationPresenter implements NotificationContract.Presenter {
    private UserApi mApi;
    private NotificationContract.View mView;

    @Inject
    public NotificationPresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull NotificationContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getNotificationList() {
        mApi.getApiService().getNotificationList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<Notification>>(mView) {
                    @Override
                    protected void onSuccess(List<Notification> notificationList) {
                        mView.onGetNotificationListSuccess(notificationList);
                    }
                });
    }

}