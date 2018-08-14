package com.jdp.hls.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.page.rosterdetail.RosterDetailActivity;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterPersonFragment extends BaseFragment {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private List<Roster> rosters = new ArrayList<>();
    private CommonAdapter adapter;

    public static RosterPersonFragment newInstance(List<Roster> rosters) {
        RosterPersonFragment fragment = new RosterPersonFragment();
        Bundle args = new Bundle();
        args.putSerializable("rosters", (Serializable) rosters);
        fragment.setArguments(args);
        return fragment;
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Roster roster = (Roster) adapterView.getItemAtPosition(position);
        RosterDetailActivity.goActivity(getActivity(), roster);


    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            rosters = (List<Roster>) getArguments().getSerializable("rosters");
            LogUtil.e(TAG, "rosters:" + rosters.size());
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView() {
        plv.setAdapter(adapter = new CommonAdapter<Roster>(getActivity(), rosters, R.layout.item_roster) {
                    @Override
                    public void convert(ViewHolder helper, Roster item) {
                        helper.setText(R.id.tv_roster_address, item.getHouseAddress());
                        helper.setText(R.id.tv_roster_name, item.getRealName());
                        helper.setText(R.id.tv_roster_phone, item.getMobilePhone());
                        helper.setBackgroundResource(R.id.iv_roster_isMeasure, item.isMeasured() ? R.mipmap
                                .ic_measure_action : R.mipmap
                                .ic_measure_nor);
                        helper.setBackgroundResource(R.id.iv_roster_isEvaluated, item.isMeasured() ? R.mipmap
                                .ic_evaluate_action : R.mipmap
                                .ic_evaluate_nor);
                    }
                }
        );
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.common_lv;
    }

}
