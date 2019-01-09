package com.jdp.hls.page.supervise.statistics.total.pay.detaillist;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AmountInfo;
import com.jdp.hls.model.entiy.FamilyMember;
import com.jdp.hls.model.entiy.KeyValue;
import com.jdp.hls.model.entiy.PayItem;
import com.jdp.hls.model.entiy.PayOwner;
import com.jdp.hls.model.entiy.SupervisePayInfo;
import com.jdp.hls.page.familyrelation.detail.FamilyMememberDetailActivity;
import com.jdp.hls.view.StringTextView;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/12/24 0024 下午 4:19
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsPayDetailListActivity extends BaseTitleActivity implements StatisticsPayDetailListContract.View {
    @BindView(R.id.tv_cusCode)
    StringTextView tvCusCode;
    @BindView(R.id.tv_owner)
    StringTextView tvOwner;
    @BindView(R.id.tv_idcard)
    StringTextView tvIdcard;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_payableAmount)
    StringTextView tvPayableAmount;
    @BindView(R.id.tv_paidAmount)
    StringTextView tvPaidAmount;
    @BindView(R.id.tv_balanceAmount)
    StringTextView tvBalanceAmount;

    @Inject
    StatisticsPayDetailListPresenter statisticsPayDetailListPresenter;
    private PayOwner payOwner;
    private String title;


    @Override
    public void initVariable() {
        payOwner = (PayOwner) getIntent().getSerializableExtra(Constants.Extra.Object);
        title = getIntent().getStringExtra(Constants.Extra.TITLE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_supervise_paylistdetail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        statisticsPayDetailListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return TextUtils.isEmpty(title)?"":title +"明细";
    }

    @Override
    protected void initView() {

    }

    private CommonAdapter adapter;

    @Override
    protected void initData() {
        lv.setAdapter(adapter = new CommonAdapter<PayItem>(this, new ArrayList<>(), R.layout
                .item_pay_detail) {
            @Override
            public void convert(ViewHolder helper, PayItem item) {
                helper.setText(R.id.tv_payDate, item.getPayDate());
                helper.setText(R.id.tv_amount, item.getAmount());
                helper.setText(R.id.tv_remark, item.getRemark());
                helper.setText(R.id.tv_bankAccount,item.getBankAccountName()+"  "+ item.getBankAccount());
                helper.setText(R.id.tv_limitDate, item.getLimitStartDate() + "至" + item.getLimitEndDate());
                helper.setVisibility(R.id.tv_isDouble, item.isIsDouble());
                helper.setVisibility(R.id.ll_limitDate, item.getUseItemId() == Status.PayTypeItem.TempPlacementFee);

            }
        });
        if (payOwner != null) {
            tvCusCode.setString(payOwner.getCusCode());
            tvIdcard.setString(payOwner.getIdcard());
            tvOwner.setString(payOwner.getRealName() + "/" + payOwner.getMobilePhone());
        }
    }

    @Override
    public void initNet() {
        statisticsPayDetailListPresenter.getSupervisePayList(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("BuildingId", payOwner.getBuildingId())
                .addFormDataPart("BuildingType", String.valueOf(payOwner.getBuildingType()))
                .addFormDataPart("UseItemId", String.valueOf(payOwner.getUseItemId()))
                .build());
    }

    @Override
    public void onGetSupervisePayListSuccess(SupervisePayInfo supervisePayInfo) {
        setListView(supervisePayInfo.getLstFinancePay(), adapter);
        AmountInfo amountInfo = supervisePayInfo.getAmountInfo();
        if (amountInfo != null) {
            tvPayableAmount.setString(amountInfo.getPayableAmount() + "元");
            tvPaidAmount.setString(amountInfo.getPaidAmount() + "元");
            tvBalanceAmount.setString(amountInfo.getBalanceAmount() + "元");
        }
    }

    public static void goActivity(Context context, PayOwner payOwner,String title) {
        Intent intent = new Intent(context, StatisticsPayDetailListActivity.class);
        intent.putExtra(Constants.Extra.Object, payOwner);
        intent.putExtra(Constants.Extra.TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
