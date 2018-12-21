package com.jdp.hls.page.table.list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.TableAdapter;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Table;
import com.jdp.hls.page.business.basic.company.BasicCompanyActivity;
import com.jdp.hls.page.business.basic.personla.BasicPersonalActivity;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.PullToBottomListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:一览表列表
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TableListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        TableListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    private List<Table> tables = new ArrayList<>();
    private TableAdapter adapter;
    private int buildingType;
    @Inject
    TableListPresenter tableListPresenter;

    public static TableListFragment newInstance(List<Table> tables, int buildingType) {
        TableListFragment fragment = new TableListFragment();
        Bundle args = new Bundle();
        args.putSerializable("tables", (Serializable) tables);
        args.putInt("buildingType", buildingType);
        fragment.setArguments(args);
        return fragment;
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Table table = (Table) adapterView.getItemAtPosition(position);
        if (table.getBuildingType() == Status.BuildingType.PERSONAL) {
            BasicPersonalActivity.goActivity(getActivity(), table.getBuildingId());
        } else {
            BasicCompanyActivity.goActivity(getActivity(), table.getBuildingId());
        }
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            tables = (List<Table>) getArguments().getSerializable("tables");
            buildingType = getArguments().getInt("buildingType", 0);
        }
        LogUtil.e(TAG, "fragmetn tables:" + tables.size());
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        tableListPresenter.attachView(this);
        plv.setAdapter(adapter = new TableAdapter(getActivity(), tables, R.layout.item_table));
    }

    @Override
    protected void initData() {
    }

    @Override
    public void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.common_plv;
    }


    @Override
    public void onRefresh() {
        tableListPresenter.getTables(SpSir.getInstance().getProjectId(), buildingType, "");
    }


    @Override
    public void onGetTablesSuccess(List<Table> tables) {
        if (tables != null && tables.size() > 0) {
            adapter.setData(tables);
        }
    }
}
