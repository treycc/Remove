package com.jdp.hls.base;

import android.view.View;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.event.RefreshTaskEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Auth;
import com.jdp.hls.page.operate.back.BackDialog;
import com.jdp.hls.page.operate.delete.DeleteDialog;
import com.jdp.hls.page.operate.review.ReviewDialog;
import com.jdp.hls.page.operate.send.SendDialog;
import com.jdp.hls.util.NoDoubleClickListener;

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
    @BindView(R.id.ll_node_operateBar)
    protected LinearLayout llNodeOperateBar;
    private SendDialog sendDialog;
    private DeleteDialog deleteDialog;
    private BackDialog backDialog;
    private ReviewDialog reviewDialog;
    private Auth auth;

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
    protected abstract void initNet();

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
        if (allowSend || allowBanned || allowFlowBack || allowReview) {
            llNodeOperateBar.setVisibility(View.VISIBLE);
        } else {
            llNodeOperateBar.setVisibility(View.GONE);
        }
        llNodeSend.setVisibility(allowSend ? View.VISIBLE : View.GONE);
        llNodeDelete.setVisibility(allowBanned ? View.VISIBLE : View.GONE);
        llNodeReview.setVisibility(allowReview ? View.VISIBLE : View.GONE);
        llNodeBack.setVisibility(allowFlowBack ? View.VISIBLE : View.GONE);
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
    }

    private void initReviewDialog() {
        reviewDialog = new ReviewDialog(BaseBasicActivity.this);

        reviewDialog.setOnOperateConfirmListener(requestBody -> {
            onReviewNode(requestBody);
        });
        llNodeReview.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                reviewDialog.show();
            }
        });
    }

    private void initBackDialog() {
        backDialog = new BackDialog(BaseBasicActivity.this);

        backDialog.setOnOperateConfirmListener(requestBody -> {
            onBackNode(requestBody);
        });
        llNodeBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                backDialog.show();
            }
        });
    }

    private void initDeleteDialog() {
        deleteDialog = new DeleteDialog(BaseBasicActivity.this);

        deleteDialog.setOnOperateConfirmListener(requestBody -> {
            onDeleteNode(requestBody);
        });
        llNodeDelete.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                deleteDialog.show();
            }
        });
    }

    private void initSendDialog() {
        sendDialog = new SendDialog(BaseBasicActivity.this);

        sendDialog.setOnOperateConfirmListener(requestBody -> {
            onSendNode(requestBody);
        });

        llNodeSend.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                sendDialog.show();
            }
        });
    }

    protected void fillDialogDate(String buildingId, String buildingType, String stastusId, String groupId) {
        boolean allowSend = auth.isAllowSend();
        boolean allowBanned = auth.isAllowBanned();
        boolean allowReview = auth.isAllowReview();
        boolean allowFlowBack = auth.isAllowFlowBack();
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
    }

    protected void fillDialogDate(String buildingId, String buildingType, String stastusId) {
        fillDialogDate(buildingId, buildingType, stastusId, "");
    }


    protected abstract void onSendNode(RequestBody requestBody);

    protected abstract void onBackNode(RequestBody requestBody);

    protected abstract void onReviewNode(RequestBody requestBody);

    protected abstract void onDeleteNode(RequestBody requestBody);

    @Override
    public void showSuccessAndFinish(String tip) {
        super.showSuccessAndFinish(tip);
        EventBus.getDefault().post(new RefreshTaskEvent());
    }
}
