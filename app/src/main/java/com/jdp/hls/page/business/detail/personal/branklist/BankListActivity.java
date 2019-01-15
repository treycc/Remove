package com.jdp.hls.page.business.detail.personal.branklist;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BaseLvAdapter;
import com.jdp.hls.adapter.BankAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddBankInfoEvent;
import com.jdp.hls.event.ModifyBankInfoEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BankInfo;
import com.jdp.hls.model.entiy.BankListInfo;
import com.jdp.hls.page.business.detail.personal.bankdetail.DeedPersonalBankActivity;
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
public class BankListActivity extends BaseTitleActivity implements BankListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    private BankAdapter brankAdapter;
    @Inject
    BankListPresenter brankListPresenter;
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
        brankAdapter = new BankAdapter(this, null);
        plv.setAdapter(brankAdapter);
    }

    @Override
    protected void initData() {
        brankAdapter.setOnItemOperListener(new BaseLvAdapter.OnItemOperListener<BankInfo>() {
            @Override
            public void onItemDelete(BankInfo brankInfo, int position) {
                DialogUtil.showDoubleDialog(BankListActivity.this, "是否确定删除该项?", (dialog, which) -> {
                    brankListPresenter.deleteBankInfo(String.valueOf(brankInfo.getId()), position);
                });
            }

            @Override
            public void onItemClick(BankInfo item) {
                DeedPersonalBankActivity.goActivity(BankListActivity.this, String.valueOf(item.getId()), buildingId);
            }
        });
    }

    @Override
    public void initNet() {
        brankListPresenter.getBrankList(buildingId);
    }

    public static void goActivity(Context context, String buildingId) {
        Intent intent = new Intent(context, BankListActivity.class);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addBankInfo(AddBankInfoEvent event) {
        showSuccessCallback();
        brankAdapter.addFirst(event.getBankInfo());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyBankInfo(ModifyBankInfoEvent event) {
        brankAdapter.modifyItem(event.getBankInfo());
    }

    @Override
    public void onGetBrankListSuccess(BankListInfo brankListInfo) {
        boolean allowEdit = brankListInfo.isAllowEdit();
        if (allowEdit) {
            setRightClick("增加", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    DeedPersonalBankActivity.goActivity(BankListActivity.this, "", buildingId);
                }
            });
        }
        setListView(brankListInfo.getLstBankAccount(), brankAdapter, allowEdit);
    }

    @Override
    public void onDeleteBankInfo(int position) {
        brankAdapter.removeItem(position);
    }
}
