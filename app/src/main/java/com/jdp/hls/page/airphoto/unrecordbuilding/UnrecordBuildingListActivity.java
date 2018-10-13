package com.jdp.hls.page.airphoto.unrecordbuilding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.activity.UnrecordBuildingDetailActivity;
import com.jdp.hls.adapter.UnrecordBuildingAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddUnrecordBuildingEvent;
import com.jdp.hls.event.ModifyUnrecordBuildingEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.UnRecordBuilding;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:未登记建筑-列表
 * Create Time:2018/9/17 0017 下午 4:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UnrecordBuildingListActivity extends BaseTitleActivity {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private UnrecordBuildingAdapter unrecordBuildingAdapter;
    private List<UnRecordBuilding> unRecordBuildingList = new ArrayList<>();

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        unRecordBuildingList = (List<UnRecordBuilding>) getIntent().getSerializableExtra(Constants.Extra
                .UNRECORD_BUILDING_LIST);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "未登记建筑基本信息";
    }

    @Override
    protected void initView() {
        unrecordBuildingAdapter = new UnrecordBuildingAdapter(this, unRecordBuildingList);
        plv.setAdapter(unrecordBuildingAdapter);
    }

    @Override
    protected void initData() {
        setRightClick("增加", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                GoUtil.goActivity(UnrecordBuildingListActivity.this, UnrecordBuildingDetailActivity.class);
            }
        });
        unrecordBuildingAdapter.setOnItemOperListener(new UnrecordBuildingAdapter.OnItemOperListener() {
            @Override
            public void onItemDelete(String id, int position) {
                DialogUtil.showDoubleDialog(UnrecordBuildingListActivity.this, "是否确定删除该项?", (dialog, which) -> {
                    unrecordBuildingAdapter.remove(position);
                });
            }

            @Override
            public void onItemClick(UnRecordBuilding unRecordBuilding) {
                UnrecordBuildingDetailActivity.goActivity(UnrecordBuildingListActivity.this, unRecordBuilding);
            }
        });
        srl.setOnRefreshListener(() -> {
            srl.setRefreshing(false);
        });
    }

    @Override
    protected void initNet() {
    }


    public static void goActivity(Activity context, List<UnRecordBuilding> unRecordBuildingList) {
        Intent intent = new Intent(context, UnrecordBuildingListActivity.class);
        intent.putExtra(Constants.Extra.UNRECORD_BUILDING_LIST, (Serializable) unRecordBuildingList);
        context.startActivityForResult(intent, Constants.RequestCode.UNRECORDBUILDING);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addUnrecordBuilding(AddUnrecordBuildingEvent event) {
        unrecordBuildingAdapter.addFirst(event.getUnRecordBuilding());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyUnrecordBuilding(ModifyUnrecordBuildingEvent event) {
        unrecordBuildingAdapter.modify(event.getUnRecordBuilding());
    }

    @Override
    protected void onBack() {
        String deleteIds = unrecordBuildingAdapter.getDeleteIds();
        String editedBase64 = unrecordBuildingAdapter.getEditedBase64();
        Intent intent = new Intent();
        intent.putExtra(Constants.Extra.DELETEIDS, deleteIds);
        intent.putExtra(Constants.Extra.EDITEDBASE64, editedBase64);
        setResult(Activity.RESULT_OK, intent);
        super.onBack();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBack();
    }
}
