package com.jdp.hls.page.supervise.statistics.total.pay.detaillist;

import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.KeyValue;
import com.jdp.hls.model.entiy.SupervisePayDetailInfo;
import com.jdp.hls.view.StringTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

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


    @Override
    public void initVariable() {

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
        return "明细";
    }

    @Override
    protected void initView() {

    }

    private CommonAdapter adapter;

    @Override
    protected void initData() {
        lv.setAdapter(adapter = new CommonAdapter<KeyValue>(this, new ArrayList<>(), R.layout
                .item_pay_detail) {
            @Override
            public void convert(ViewHolder helper, KeyValue item) {
                helper.setText(R.id.tv_key, item.getName());
                helper.setText(R.id.tv_value, item.getValue());
                helper.setVisibility(R.id.iv_detail, item.isHasDetail());
            }
        });
    }

    @Override
    public void initNet() {
        statisticsPayDetailListPresenter.getSupervisePayDetailList();
    }

    @Override
    public void onGetSupervisePayDetailListSuccess(SupervisePayDetailInfo supervisePayDetailInfo) {

    }
}
