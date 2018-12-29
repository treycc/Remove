package com.jdp.hls.page.business.detail.personal.branklist;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BaseLvAdapter;
import com.jdp.hls.adapter.BrankAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.AddBrankInfoEvent;
import com.jdp.hls.event.ModifyBrankInfoEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BrankInfo;
import com.jdp.hls.model.entiy.BrankListInfo;
import com.jdp.hls.page.deed.personal.bank.DeedPersonalBankActivity;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.PullToBottomListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:其它面积-列表
 * Create Time:2018/9/17 0017 下午 4:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BrankListActivity extends BaseTitleActivity implements BrankListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    private BrankAdapter brankAdapter;
    @Inject
    BrankListPresenter brankListPresenter;
    private String buildingId;

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        buildingId = getIntent().getStringExtra(Constants.Extra.BUILDING_ID);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected int getContentView() {
        return R.layout.common_plv;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        brankListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "账号列表";
    }

    @Override
    protected void initView() {
        brankAdapter = new BrankAdapter(this, null);
        plv.setAdapter(brankAdapter);
    }

    @Override
    protected void initData() {
        brankAdapter.setOnItemOperListener(new BaseLvAdapter.OnItemOperListener<BrankInfo>() {
            @Override
            public void onItemDelete(BrankInfo brankInfo, int position) {
                DialogUtil.showDoubleDialog(BrankListActivity.this, "是否确定删除该项?", (dialog, which) -> {
                });
            }

            @Override
            public void onItemClick(BrankInfo item) {
            }
        });
    }

    @Override
    public void initNet() {
        brankListPresenter.getBrankList(buildingId);
    }

    public static void goActivity(Context context, String buildingId) {
        Intent intent = new Intent(context, BrankListActivity.class);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addBrankInfo(AddBrankInfoEvent event) {
        showSuccessCallback();
        brankAdapter.addFirst(event.getBrankInfo());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyBrankInfo(ModifyBrankInfoEvent event) {
        brankAdapter.modifyItem(event.getBrankInfo());
    }

    @Override
    public void onGetBrankListSuccess(BrankListInfo brankListInfo) {
        boolean allowEdit = brankListInfo.isAllowEdit();
        if (allowEdit) {
            setRightClick("增加", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                }
            });
        }
        setListView(brankListInfo.getBrankList(), brankAdapter, allowEdit);
    }
}
