package com.jdp.hls.page.node.protocol.personal.lastst.pay.banklist;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BankInfo;
import com.jdp.hls.model.entiy.BankListInfo;
import com.jdp.hls.view.PullToBottomListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:其它面积-列表
 * Create Time:2018/9/17 0017 下午 4:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReceiveAccountListActivity extends BaseTitleActivity implements ReceiveAccountListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @Inject
    ReceiveAccountListPresenter brankListPresenter;
    private String buildingId;
    private CommonAdapter adapter;

    @Override
    public void initVariable() {
        buildingId = getIntent().getStringExtra(Constants.Extra.BUILDING_ID);
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        BankInfo bankInfo = (BankInfo) adapterView.getItemAtPosition(position);
        Intent intent = new Intent();
        intent.putExtra(Constants.Extra.ReceiveAccount, bankInfo.getBankAccountName() + "  " + bankInfo
                .getBankAccount());
        intent.putExtra(Constants.Extra.RecBankAccountId, bankInfo.getId());
        setResult(Activity.RESULT_OK, intent);
        finish();
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
    }


    @Override
    protected void initData() {
        plv.setAdapter(adapter = new CommonAdapter<BankInfo>(this, null, R.layout.item_receive_account) {
            @Override
            public void convert(ViewHolder helper, BankInfo item) {
                helper.setText(R.id.tv_realName, item.getBankAccountName());
                helper.setText(R.id.tv_bankName, item.getBankName());
                helper.setText(R.id.tv_bankAccount, item.getBankAccount());
            }
        });
    }

    @Override
    public void initNet() {
        brankListPresenter.getBrankList(buildingId);
    }

    public static void goActivity(Activity context, String buildingId) {
        Intent intent = new Intent(context, ReceiveAccountListActivity.class);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        context.startActivityForResult(intent, Constants.RequestCode.ReceiveAccount);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onGetBrankListSuccess(BankListInfo brankListInfo) {
        setListView(brankListInfo.getLstBankAccount(), adapter);
    }
}
