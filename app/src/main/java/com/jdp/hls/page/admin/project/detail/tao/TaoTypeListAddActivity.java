package com.jdp.hls.page.admin.project.detail.tao;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.adapter.TaoTypeAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.TaoTypeAddEvent;
import com.jdp.hls.event.TaoTypeModifyEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.TaoType;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.PullToBottomListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/12/17 0017 下午 2:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TaoTypeListAddActivity extends BaseTitleActivity {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    private List<TaoType> taoTypeList = new ArrayList<>();
    private TaoTypeAdapter taoTypeAdapter;

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        taoTypeList = (List<TaoType>) getIntent().getSerializableExtra(Constants.Extra.TaoTypeList);
    }

    @Override
    protected int getContentView() {
        return R.layout.common_plv;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
    }

    @Override
    protected String getContentTitle() {
        return "套型";
    }

    @Override
    protected void initView() {
        taoTypeAdapter = new TaoTypeAdapter(this, taoTypeList);
        taoTypeAdapter.setOnItemOperListener(new TaoTypeAdapter.OnItemOperListener() {
            @Override
            public void onItemDelete(String id, int position) {
                DialogUtil.showDoubleDialog(TaoTypeListAddActivity.this, "是否确定删除该项?", (dialog, which) -> {
                    taoTypeAdapter.removeItem(position);
                });
            }

            @Override
            public void onItemClick(TaoType taoType) {

            }
        });
        plv.setAdapter(taoTypeAdapter);
    }

    @Override
    protected void initData() {
        setRightClick("增加", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                TaoTypeDetailActivity.goActivity(TaoTypeListAddActivity.this, null);
            }
        });

    }

    @Override
    public void initNet() {

    }

    public static void goActivity(Activity activity, List<TaoType> taoTypeList) {
        Intent intent = new Intent(activity, TaoTypeListAddActivity.class);
        intent.putExtra(Constants.Extra.TaoTypeList, (Serializable) taoTypeList);
        activity.startActivityForResult(intent, Constants.RequestCode.TaoTypeListAdd);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addTaoType(TaoTypeAddEvent event) {
        taoTypeAdapter.addFirst(event.getTaoType());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyTaoType(TaoTypeModifyEvent event) {
        taoTypeAdapter.modifyItem(event.getTaoType());
    }

    @Override
    protected void onBack() {
        String deleteIds = taoTypeAdapter.getDeleteIds();
        List<TaoType> taoTypeList = taoTypeAdapter.getData();
        Intent intent = new Intent();
        intent.putExtra(Constants.Extra.DELETEIDS, deleteIds);
        intent.putExtra(Constants.Extra.TaoTypeList, (Serializable) taoTypeList);
        setResult(Activity.RESULT_OK, intent);
        LogUtil.e(TAG, "保存：" + taoTypeList.size());
        finish();
    }

    @Override
    public void onBackPressed() {
        onBack();
    }
}
