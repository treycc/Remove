package com.jdp.hls.page.supervise.statistics.total.pay.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.PayOwnerAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AmountInfo;
import com.jdp.hls.model.entiy.PayOwner;
import com.jdp.hls.model.entiy.PayOwnerListInfo;
import com.jdp.hls.page.supervise.statistics.total.pay.detaillist.StatisticsPayDetailListActivity;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/12/20 0020 上午 11:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SupervisePayOwnerListActivity extends BaseTitleActivity implements StatisticsPayOwnerListContract.View {
    @Inject
    StatisticsPayOwnerListPresenter statisticsPayListPresenter;
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_payableAmount)
    StringTextView tvPayableAmount;
    @BindView(R.id.tv_paidAmount)
    StringTextView tvPaidAmount;
    @BindView(R.id.tv_balanceAmount)
    StringTextView tvBalanceAmount;
    @BindView(R.id.rsrl)
    RefreshSwipeRefreshLayout rsrl;
    private PayOwnerAdapter payDetailAdapter;
    private int buildingType;
    private int userItemId;
    private String title;

    @OnItemClick({R.id.lv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        PayOwner payOwner = (PayOwner) adapterView.getItemAtPosition(position);
        if (payOwner.getQuantity() == 0) {
            ToastUtil.showText("暂无支付明细");
            return;
        }
        StatisticsPayDetailListActivity.goActivity(this, payOwner, title);
    }

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etKeyword.setText("");
                break;
        }
    }

    @Override
    public void initVariable() {
        buildingType = getIntent().getIntExtra(Constants.Extra.BUILDING_TYPE, 0);
        userItemId = getIntent().getIntExtra(Constants.Extra.ID, 0);
        title = getIntent().getStringExtra(Constants.Extra.TITLE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_supervise_paylist;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        statisticsPayListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return TextUtils.isEmpty(title) ? "支付详情" : title;
    }

    @Override
    protected void initView() {
        rsrl.stepRefresh(this);

    }

    @Override
    protected void initData() {
        etKeyword.setHint("请输入档案编号/户主姓名");
        payDetailAdapter = new PayOwnerAdapter(this, null);
        lv.setAdapter(payDetailAdapter);
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                payDetailAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void initNet() {
        statisticsPayListPresenter.getPayOwnList(buildingType, userItemId);
    }

    public static void GoActivity(Context context, int buildingType, int userItemId, String title) {
        Intent intent = new Intent(context, SupervisePayOwnerListActivity.class);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        intent.putExtra(Constants.Extra.ID, userItemId);
        intent.putExtra(Constants.Extra.TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onGetPayOwnListListSuccess(PayOwnerListInfo payOwnerListInfo) {
        String keyword = etKeyword.getText().toString().trim();
        setSearchListView(payOwnerListInfo.getLstPayable(), payDetailAdapter, keyword);
        AmountInfo amountInfo = payOwnerListInfo.getAmountInfo();
        if (amountInfo != null) {
            tvPayableAmount.setString(amountInfo.getPayableAmount() + "元");
            tvPaidAmount.setString(amountInfo.getPaidAmount() + "元");
            tvBalanceAmount.setString(amountInfo.getBalanceAmount() + "元");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
