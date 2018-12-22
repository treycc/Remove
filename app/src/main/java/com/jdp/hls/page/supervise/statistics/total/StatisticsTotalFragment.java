package com.jdp.hls.page.supervise.statistics.total;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AmountInfo;
import com.jdp.hls.model.entiy.KeyValue;
import com.jdp.hls.model.entiy.StatisticsTotalInfo;
import com.jdp.hls.page.supervise.statistics.total.pay.SupervisePayListActivity;
import com.jdp.hls.page.supervise.statistics.total.taotype.StatisticsTaotypeListActivity;
import com.jdp.hls.view.FixSwipeRefreshLayout;
import com.jdp.hls.view.FixedListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;
import com.jdp.hls.view.StringTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;


/**
 * Description:TODO
 * Create Time:2018/11/27 0027 下午 4:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsTotalFragment extends BaseFragment implements StatisticsTotalContract.View {

    @BindView(R.id.flv)
    FixedListView flv;
    @BindView(R.id.tv_payableAmount)
    StringTextView tvPayableAmount;
    @BindView(R.id.tv_paidAmount)
    StringTextView tvPaidAmount;
    @BindView(R.id.tv_balanceAmount)
    StringTextView tvBalanceAmount;
    Unbinder unbinder;
    private int buildingType;
    private CommonAdapter adapter;
    @Inject
    StatisticsTotalPresenter statisticsTotalPresenter;
    private String projectId;
    private String buildingArea;

    @OnItemClick({R.id.flv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        KeyValue keyValue = (KeyValue) adapterView.getItemAtPosition(position);
        if (!keyValue.isHasDetail()) {
            return;
        }
        switch (keyValue.getInterfaceId()) {
            case Status.InterfaceId.TAOTYPE_DETAIL:
                StatisticsTaotypeListActivity.GoActivity(getActivity(), keyValue.getName(), keyValue.getValue(),
                        buildingArea);
                break;
            case Status.InterfaceId.PAY_DETAIL:
                SupervisePayListActivity.GoActivity(getActivity(), keyValue.getName(), keyValue.getValue());
                break;
        }

    }

    public static StatisticsTotalFragment newInstance(String projectId, int buildingType) {
        StatisticsTotalFragment fragment = new StatisticsTotalFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.Extra.BUILDING_TYPE, buildingType);
        args.putString(Constants.Extra.PROJECTID, projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            buildingType = getArguments().getInt(Constants.Extra.BUILDING_TYPE);
            projectId = getArguments().getString(Constants.Extra.PROJECTID);
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        statisticsTotalPresenter.attachView(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        flv.setAdapter(adapter = new CommonAdapter<KeyValue>(getActivity(), new ArrayList<>(), R.layout
                .item_statistics_total) {
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
        statisticsTotalPresenter.getStatisticsTotal(buildingType);
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_statistics_total;
    }

    @Override
    public void onGetStatisticsTotalSuccess(StatisticsTotalInfo statisticsTotalInfo) {
        AmountInfo amountInfo = statisticsTotalInfo.getAmountInfo();
        if (amountInfo != null) {
            tvPayableAmount.setString(amountInfo.getPayableAmount());
            tvPaidAmount.setString(amountInfo.getPaidAmount());
            tvBalanceAmount.setString(amountInfo.getBalanceAmount());
        }
        setListView(statisticsTotalInfo.getLstBuildingCollect(), adapter);

        buildingArea = getBuildingArea(statisticsTotalInfo.getLstBuildingCollect());
    }

    private String getBuildingArea(List<KeyValue> keyValueList) {
        if (keyValueList != null && keyValueList.size() > 0) {
            for (KeyValue keyValue : keyValueList) {
                if (Status.AreaType.BUILDING_AREA.equals(keyValue.getName())) {
                    return keyValue.getValue();
                }

            }
        }
        return "0";

    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
