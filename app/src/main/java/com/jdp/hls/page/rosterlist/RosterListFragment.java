package com.jdp.hls.page.rosterlist;

import android.os.Bundle;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BaseLvAdapter;
import com.jdp.hls.adapter.RosterListAdapter;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.event.AddRostersEvent;
import com.jdp.hls.event.ModifyMainContactsEvent;
import com.jdp.hls.event.ModifyRostersEvent;
import com.jdp.hls.event.RemoveRosterEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.page.rosterdetail.detail.company.RosterCompanyDetailActivity;
import com.jdp.hls.page.rosterdetail.detail.personal.RosterPersonalDetailActivity;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.SpSir;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:花名册列表
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterListFragment extends BaseFragment implements GetRostersByTypeContract.View, DeleteRosterContract
        .View {
    @BindView(R.id.plv)
    ListView plv;
    private List<Roster> rosters = new ArrayList<>();
    private RosterListAdapter adapter;
    private int buildingType;
    @Inject
    GetRostersByTypePresenter getRostersByTypePresenter;
    @Inject
    DeleteRosterPresenter deleteRosterPresenter;


    public static RosterListFragment newInstance(List<Roster> rosters, int buildingType) {
        RosterListFragment fragment = new RosterListFragment();
        Bundle args = new Bundle();
        args.putSerializable("rosters", (Serializable) rosters);
        args.putInt("buildingType", buildingType);
        fragment.setArguments(args);
        return fragment;
    }

    public static RosterListFragment newInstance(int buildingType) {
        RosterListFragment fragment = new RosterListFragment();
        Bundle args = new Bundle();
        args.putInt("buildingType", buildingType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariable() {
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            rosters = (List<Roster>) getArguments().getSerializable("rosters");
            buildingType = getArguments().getInt("buildingType", 0);
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        deleteRosterPresenter.attachView(this);
        getRostersByTypePresenter.attachView(this);
    }

    @Override
    protected void initView() {
        adapter = new RosterListAdapter(getActivity(), rosters);
        plv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        adapter.setOnItemOperListener(new BaseLvAdapter.OnItemOperListener<Roster>() {
            @Override
            public void onItemDelete(Roster roster, int position) {
                DialogUtil.showDoubleDialog(getActivity(), "是否确定删除该项?", (dialog, which) -> {
                    deleteRosterPresenter.deleteRoster(new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("Reason", "花名册删除")
                            .addFormDataPart("buildingId", roster.getHouseId())
                            .addFormDataPart("buildingType", String.valueOf(roster.isEnterprise() ? 1 : 0))
                            .build(), roster, position);
                });

            }

            @Override
            public void onItemClick(Roster item) {
                if (item.isEnterprise()) {
                    RosterCompanyDetailActivity.goActivity(getActivity(), item.getHouseId());
                } else {
                    RosterPersonalDetailActivity.goActivity(getActivity(), item.getHouseId());
                }

            }
        });
    }

    @Override
    public void initNet() {
//        getRostersByTypePresenter.getRosterListByType(SpSir.getInstance().getProjectId(), SpSir.getInstance()
//                .getEmployeeId(), buildingType);
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_roster_list;
    }

    @Override
    public void onGetRosterListByTypeSuccess(List<Roster> rosters) {
        adapter.setData(rosters);
    }

    public void refreshData(List<Roster> rosters) {
        adapter.setData(rosters);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshRosters(AddRostersEvent event) {
        int buildingType = event.getRoster().isEnterprise() ? 1 : 0;
        if (buildingType == this.buildingType) {
            adapter.addFirst(event.getRoster());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyMainContacts(ModifyMainContactsEvent event) {
        int buildingType = event.getRoster().isEnterprise() ? 1 : 0;
        if (buildingType == this.buildingType) {
            adapter.modifyMainContacts(event.getRoster());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyRosters(ModifyRostersEvent event) {
        int buildingType = event.getRoster().isEnterprise() ? 1 : 0;
        if (buildingType == this.buildingType) {
            adapter.modifyItem(event.getRoster());
        }
    }

    @Override
    public void onDeleteRosterSuccess(Roster roster, int position) {
        adapter.removeItem(position);
        EventBus.getDefault().post(new RemoveRosterEvent(roster.getHouseId(), roster.isEnterprise()));
    }
}
