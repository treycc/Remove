package com.jdp.hls.page.admin.group.list;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.GroupAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddGroupEvent;
import com.jdp.hls.event.ModifyGroupEvent;
import com.jdp.hls.event.RefreshBusinessListEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Group;
import com.jdp.hls.model.entiy.GroupDetail;
import com.jdp.hls.model.entiy.Member;
import com.jdp.hls.page.admin.group.detail.GroupDetailActivity;
import com.jdp.hls.page.admin.group.member.MemberSelectActivity;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/21 0021 下午 7:26
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GroupListActivity extends BaseTitleActivity implements GroupListContract.View {

    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.rsrl)
    RefreshSwipeRefreshLayout rsrl;
    @Inject
    GroupListPresenter groupListPresenter;
    private String projectId;
    private GroupAdapter groupAdapter;

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Group group = (Group) adapterView.getItemAtPosition(position);
        GroupDetailActivity.goActivity(this, projectId, group.getGroupId());
    }

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        projectId = getIntent().getStringExtra(Constants.Extra.PROJECTID);
    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv_rsl;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        groupListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "小组信息";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        rsrl.stepRefresh(this);
        groupAdapter = new GroupAdapter(this, null);
        plv.setAdapter(groupAdapter);
        setRightClick("增加", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                GroupDetailActivity.goActivity(GroupListActivity.this, projectId, 0);
            }
        });
    }

    @Override
    public void initNet() {
        groupListPresenter.getGroupList(projectId);
    }

    @Override
    public void onGetGroupListSuccess(List<Group> groupList) {
        setListView(groupList, groupAdapter);

    }

    public static void goActivity(Context context, String projectId) {
        Intent intent = new Intent(context, GroupListActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyGroup(ModifyGroupEvent event) {
        groupAdapter.modifyItem(event.getGroup());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addGroup(AddGroupEvent event) {
        groupAdapter.addFirst(event.getGroup());
    }
}
