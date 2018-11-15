package com.jdp.hls.page.message.notification;

import com.jdp.hls.R;
import com.jdp.hls.adapter.NotificationAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Notification;
import com.jdp.hls.view.FixedListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/11/15 0015 下午 5:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NotificationActivity extends BaseTitleActivity implements NotificationContract.View {
    @BindView(R.id.flv)
    FixedListView flv;
    @Inject
    NotificationPresenter notificationPresenter;

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        notificationPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "消息通知";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initNet() {
        notificationPresenter.getNotificationList();
    }

    @Override
    public void onGetNotificationListSuccess(List<Notification> notificationList) {
        if (notificationList != null && notificationList.size() > 0) {
            flv.setAdapter(new NotificationAdapter(this, notificationList));
        }
    }
}
