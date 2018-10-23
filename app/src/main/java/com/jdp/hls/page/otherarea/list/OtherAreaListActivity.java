package com.jdp.hls.page.otherarea.list;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.adapter.OtherAreaAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.callback.EmptyCallback;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddOtherEvent;
import com.jdp.hls.event.ModifyOtherEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.OtherArea;
import com.jdp.hls.page.otherarea.add.OtherAreaAddActivity;
import com.jdp.hls.page.otherarea.detail.OtherAreaDetailActivity;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:其它面积-列表
 * Create Time:2018/9/17 0017 下午 4:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherAreaListActivity extends BaseTitleActivity implements OhterAreaContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private List<OtherArea> otherAreas = new ArrayList<>();
    private OtherAreaAdapter otherAreaAdapter;
    @Inject
    OtherAreaPresenter otherAreaPresenter;
    private String pcdId;
    private String buildingType;

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        pcdId = getIntent().getStringExtra(Constants.Extra.ID);
        buildingType = getIntent().getStringExtra(Constants.Extra.BUILDING_TYPE);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv_sl;
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
        return "其它面积";
    }

    @Override
    protected void initView() {
        otherAreaPresenter.attachView(this);
        otherAreaAdapter = new OtherAreaAdapter(this, otherAreas);
        plv.setAdapter(otherAreaAdapter);
    }

    @Override
    protected void initData() {
        setRightClick("增加", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                OtherAreaAddActivity.goActivity(OtherAreaListActivity.this, pcdId, buildingType);
            }
        });
        otherAreaAdapter.setOnDeleteOtherAreaListener(new OtherAreaAdapter.OnDeleteOtherAreaListener() {
            @Override
            public void onDeleteOtherArea(String id, int position) {
                DialogUtil.showDoubleDialog(OtherAreaListActivity.this, "是否确定删除该项?", (dialog, which) -> {
                    otherAreaPresenter.deleteOtherArea(new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("ids", id)
                            .addFormDataPart("buildingType", buildingType)
                            .build(), position);
                });
            }

            @Override
            public void onOtherAreaClick(OtherArea otherArea) {
                OtherAreaDetailActivity.goActivity(OtherAreaListActivity.this, otherArea, buildingType);
            }
        });
        srl.setOnRefreshListener(() -> {
            srl.setRefreshing(false);
        });
    }

    @Override
    protected void initNet() {
        otherAreaPresenter.getOtherAreaList(pcdId, buildingType);
    }

    @Override
    public void onGetOtherAreaListSuccess(List<OtherArea> otherAreaList) {
        this.otherAreas=otherAreaList;
        checkListsize(otherAreaList);
    }

    private void checkListsize(List<OtherArea> otherAreaList) {
        if (otherAreas!= null&&otherAreas.size() > 0) {
            showSuccessCallback();
            otherAreaAdapter.setData(otherAreas);
        } else {
            mBaseLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void onDeleteOtherAreaSuccess(int position) {
        otherAreaAdapter.remove(position);
        checkListsize(otherAreaAdapter.getData());
    }

    public static void goActivity(Context context, String id, String buildingType) {
        Intent intent = new Intent(context, OtherAreaListActivity.class);
        intent.putExtra(Constants.Extra.ID, id);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addOtherArea(AddOtherEvent event) {
        showSuccessCallback();
        otherAreaAdapter.addFirst(event.getOtherArea());
        LogUtil.e(TAG,"刷新新增其它面积");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyOtherArea(ModifyOtherEvent event) {
        otherAreaAdapter.modify(event.getOtherArea());
    }
}
