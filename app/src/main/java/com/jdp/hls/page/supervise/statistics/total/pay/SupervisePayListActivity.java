package com.jdp.hls.page.supervise.statistics.total.pay;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.PayDetailAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.PayItem;
import com.jdp.hls.model.entiy.SupervisePayInfo;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/12/20 0020 上午 11:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SupervisePayListActivity extends BaseTitleActivity implements StatisticsPayListContract.View {
    @Inject
    StatisticsPayListPresenter statisticsPayListPresenter;
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
    private String useItemName;
    private PayDetailAdapter payDetailAdapter;
    private String payableAmount;

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
        useItemName = getIntent().getStringExtra(Constants.Extra.Key);
        payableAmount = getIntent().getStringExtra(Constants.Extra.Value);
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
        return TextUtils.isEmpty(useItemName) ? "支付详情" : useItemName;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        payDetailAdapter = new PayDetailAdapter(this, null);
        lv.setAdapter(payDetailAdapter);
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                payDetailAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
        tvPayableAmount.setString(TextUtils.isEmpty(payableAmount) ? "0" : payableAmount);
    }

    @Override
    public void initNet() {
        statisticsPayListPresenter.getSupervisePayList(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("UseItemName", useItemName)
                .build());
    }

    @Override
    public void onGetSupervisePayListSuccess(SupervisePayInfo supervisePayInfo) {
        String keyword = etKeyword.getText().toString().trim();
        setSearchListView(supervisePayInfo.getLstFinancePay(), payDetailAdapter, keyword);

        double paidAmount = getPaidAmount(supervisePayInfo.getLstFinancePay());
        tvPaidAmount.setString(paidAmount);

        double payableAmount=Double.valueOf(tvPayableAmount.getText().toString().trim()) ;

        tvBalanceAmount.setString((int)(paidAmount-payableAmount));

    }

    private double getPaidAmount(List<PayItem> payItemList) {
        double paidAmount = 0d;
        if (payItemList != null && payItemList.size() > 0) {
            for (PayItem payItem : payItemList) {
                paidAmount += payItem.getAmount();
            }
        }
        return paidAmount;
    }

    public static void GoActivity(Context context, String key, String value) {
        Intent intent = new Intent(context, SupervisePayListActivity.class);
        intent.putExtra(Constants.Extra.Key, key);
        intent.putExtra(Constants.Extra.Value, value);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
