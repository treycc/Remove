package com.jdp.hls.page.admin.query.list;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.QueryAdapter;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BusinessQuery;
import com.jdp.hls.page.business.basic.company.BasicCompanyActivity;
import com.jdp.hls.page.business.basic.personla.BasicPersonalActivity;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.view.PullToBottomListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:业务列表
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class QueryListFragment extends BaseFragment {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    private List<BusinessQuery> business = new ArrayList<>();
    private QueryAdapter adapter;

    public static QueryListFragment newInstance(List<BusinessQuery> business, int buildingType) {
        QueryListFragment fragment = new QueryListFragment();
        Bundle args = new Bundle();
        args.putSerializable("business", (Serializable) business);
        args.putInt("buildingType", buildingType);
        fragment.setArguments(args);
        return fragment;
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        BusinessQuery business = (BusinessQuery) adapterView.getItemAtPosition(position);
        String buildingId = business.getBuildingId();
        if (business.getBuildingType() == Status.BuildingId.PERSONAL) {
            BasicPersonalActivity.goActivity(getActivity(), buildingId);
        } else {
            BasicCompanyActivity.goActivity(getActivity(), buildingId);
        }
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            business = (List<BusinessQuery>) getArguments().getSerializable("business");
            LogUtil.e(TAG, "business:" + business.size());
        }
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
        adapter = new QueryAdapter(getActivity(), business);
        plv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        checkListSize(business);
    }

    @Override
    public void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.common_plv;
    }


    public void refreshData(List<BusinessQuery> businessQueryList) {
        checkListSize(businessQueryList);
    }

    private void checkListSize(List<BusinessQuery> businesses) {
        if (businesses != null && businesses.size() > 0) {
            showSuccessCallback();
            adapter.setData(businesses);
        } else {
            showEmptyCallback();
        }
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
