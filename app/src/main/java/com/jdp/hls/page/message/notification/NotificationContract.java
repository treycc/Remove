package com.jdp.hls.page.message.notification;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Notification;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NotificationContract {
    interface View extends BaseView {
        void onGetNotificationListSuccess(List<Notification> notificationList);
    }

    interface Presenter extends BasePresenter<View> {
        void getNotificationList();

    }
}
