package com.jdp.hls.base;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.event.RefreshBusinessListEvent;
import com.jdp.hls.event.RefreshTaskEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Auth;
import com.jdp.hls.page.operate.back.BackDialog;
import com.jdp.hls.page.operate.delete.DeleteDialog;
import com.jdp.hls.page.operate.recover.RecoverDialog;
import com.jdp.hls.page.operate.reminder.ReminderDialog;
import com.jdp.hls.page.operate.review.ReviewDialog;
import com.jdp.hls.page.operate.send.SendDialog;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/10/16 0016 下午 1:53
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseBasicActivity extends BaseTitleActivity {
    @BindView(R.id.ll_node_send)
    protected LinearLayout llNodeSend;
    @BindView(R.id.ll_node_back)
    protected LinearLayout llNodeBack;
    @BindView(R.id.ll_node_review)
    protected LinearLayout llNodeReview;
    @BindView(R.id.ll_node_delete)
    protected LinearLayout llNodeDelete;
    @BindView(R.id.ll_node_recover)
    protected LinearLayout llNodeRecover;
    @BindView(R.id.ll_node_reminder)
    protected LinearLayout llNodeReminder;
    @BindView(R.id.ll_node_operateBar)
    protected LinearLayout llNodeOperateBar;
    private SendDialog sendDialog;
    private DeleteDialog deleteDialog;
    private BackDialog backDialog;
    private ReviewDialog reviewDialog;
    private Auth auth;
    private RecoverDialog recoverDialog;
    private ReminderDialog reminderDialog;

    @Override
    public abstract void initVariable();

    @Override
    protected abstract int getContentView();

    @Override
    protected abstract void initComponent(AppComponent appComponent);

    @Override
    protected abstract String getContentTitle();

    @Override
    protected abstract void initView();

    @Override
    protected abstract void initData();

    @Override
    public abstract void initNet();

    protected void setSingleAuth(Auth auth, String buildingId, String buildingType, String stastusId) {
        setSingleAuth(auth, buildingId, buildingType, stastusId, "");
    }

    protected void setSingleAuth(Auth auth, String buildingId, String buildingType, String stastusId, String groupId) {
        initAuthLayout(auth);
        fillDialogDate(buildingId, buildingType, stastusId, groupId);
    }

    protected void initAuthLayout(Auth auth) {
        this.auth = auth;
        boolean allowSend = auth.isAllowSend();
        boolean allowBanned = auth.isAllowBanned();
        boolean allowReview = auth.isAllowReview();
        boolean allowFlowBack = auth.isAllowFlowBack();
        boolean allowRecover = auth.isAllowRecover();
        boolean allowReminder = auth.isAllowReminder();
        if (allowSend || allowBanned || allowFlowBack || allowReview || allowRecover || allowReminder) {
            llNodeOperateBar.setVisibility(View.VISIBLE);
        } else {
            llNodeOperateBar.setVisibility(View.GONE);
        }
        llNodeSend.setVisibility(allowSend ? View.VISIBLE : View.GONE);
        llNodeDelete.setVisibility(allowBanned ? View.VISIBLE : View.GONE);
        llNodeReview.setVisibility(allowReview ? View.VISIBLE : View.GONE);
        llNodeBack.setVisibility(allowFlowBack ? View.VISIBLE : View.GONE);
        llNodeRecover.setVisibility(allowRecover ? View.VISIBLE : View.GONE);
        llNodeReminder.setVisibility(allowReminder ? View.VISIBLE : View.GONE);
        if (allowSend) {
            initSendDialog();
        }
        if (allowBanned) {
            initDeleteDialog();
        }
        if (allowReview) {
            initReviewDialog();
        }
        if (allowFlowBack) {
            initBackDialog();
        }
        if (allowRecover) {
            initRecoverDialog();
        }
        if (allowReminder) {
            initReminderDialog();
        }
    }

    private void initReminderDialog() {
        reminderDialog = new ReminderDialog(BaseBasicActivity.this);
        reminderDialog.setOnOperateConfirmListener(requestBody -> {
            onReminderNode(requestBody, reminderDialog.getBuildingId());
        });
        llNodeReminder.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (TextUtils.isEmpty(reminderDialog.getBuildingId())) {
                    ToastUtil.showText("请选择催办业务");
                    return;
                }
                reminderDialog.show();
            }
        });
    }

    private void initRecoverDialog() {
        recoverDialog = new RecoverDialog(BaseBasicActivity.this);
        recoverDialog.setOnOperateConfirmListener(requestBody -> {
            onRecoverNode(requestBody, recoverDialog.getBuildingId());
        });
        llNodeRecover.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (TextUtils.isEmpty(recoverDialog.getBuildingId())) {
                    ToastUtil.showText("请选择恢复业务");
                    return;
                }
                recoverDialog.show();
            }
        });
    }

    private void initReviewDialog() {
        reviewDialog = new ReviewDialog(BaseBasicActivity.this);
        reviewDialog.setOnOperateConfirmListener(requestBody -> {
            onReviewNode(requestBody, reviewDialog.getBuildingId());
        });
        llNodeReview.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (TextUtils.isEmpty(reviewDialog.getBuildingId())) {
                    ToastUtil.showText("请选择复查业务");
                    return;
                }
                reviewDialog.show();
            }
        });
    }

    private void initBackDialog() {
        backDialog = new BackDialog(BaseBasicActivity.this);
        backDialog.setOnOperateConfirmListener(requestBody -> {
            onBackNode(requestBody, backDialog.getBuildingId());
        });
        llNodeBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (TextUtils.isEmpty(backDialog.getBuildingId())) {
                    ToastUtil.showText("请选择退回业务");
                    return;
                }
                backDialog.show();
            }
        });
    }

    private void initDeleteDialog() {
        deleteDialog = new DeleteDialog(BaseBasicActivity.this);
        llNodeDelete.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (TextUtils.isEmpty(deleteDialog.getBuildingId())) {
                    ToastUtil.showText("请选择废弃业务");
                    return;
                }
                deleteDialog.show();
            }
        });
        deleteDialog.setOnOperateConfirmListener(requestBody -> {
            onDeleteNode(requestBody, deleteDialog.getBuildingId());
        });
    }

    private void initSendDialog() {
        sendDialog = new SendDialog(BaseBasicActivity.this);
        sendDialog.setOnOperateConfirmListener(requestBody -> {
            onSendNode(requestBody, sendDialog.getBuildingId());
        });
        llNodeSend.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (TextUtils.isEmpty(sendDialog.getBuildingId())) {
                    ToastUtil.showText("请选择发送业务");
                    return;
                }
                sendDialog.show();
            }
        });
    }

    protected void fillDialogDate(String buildingId, String buildingType, String stastusId, String groupId) {
        boolean allowSend = auth.isAllowSend();
        boolean allowBanned = auth.isAllowBanned();
        boolean allowReview = auth.isAllowReview();
        boolean allowFlowBack = auth.isAllowFlowBack();
        boolean allowRecover = auth.isAllowRecover();
        boolean allowReminder = auth.isAllowReminder();
        if (allowSend) {
            sendDialog.setData(buildingId, buildingType, stastusId, groupId);
        }
        if (allowBanned) {
            deleteDialog.setData(buildingId, buildingType, stastusId, groupId);
        }
        if (allowReview) {
            reviewDialog.setData(buildingId, buildingType, stastusId, groupId);
        }
        if (allowFlowBack) {
            backDialog.setData(buildingId, buildingType, stastusId, groupId);
        }
        if (allowRecover) {
            recoverDialog.setData(buildingId, buildingType, stastusId, groupId);
        }
        if (allowReminder) {
            reminderDialog.setData(buildingId, buildingType, stastusId, groupId);
        }
    }

    protected void fillDialogDate(String buildingId, String buildingType, String stastusId) {
        fillDialogDate(buildingId, buildingType, stastusId, "");
    }


    protected abstract void onSendNode(RequestBody requestBody, String buildingIds);

    protected abstract void onBackNode(RequestBody requestBody, String buildingIds);

    protected abstract void onReviewNode(RequestBody requestBody, String buildingIds);

    protected abstract void onDeleteNode(RequestBody requestBody, String buildingIds);

    protected abstract void onRecoverNode(RequestBody requestBody, String buildingIds);

    protected abstract void onReminderNode(RequestBody requestBody, String buildingIds);

    protected void onOperateSuccess(String msg, String buildingIds) {
        EventBus.getDefault().post(new RefreshBusinessListEvent(buildingIds));
        EventBus.getDefault().post(new RefreshTaskEvent());
        super.showSuccessDialogAndFinish(msg);
    }

}
