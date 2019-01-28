package com.jdp.hls.page.admin.employee.areasupervise.list;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.adapter.AreaSuperviseAdapter;
import com.jdp.hls.adapter.BaseLvAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AreaSupervise;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.page.admin.employee.areasupervise.add.AreaSuperviseAddActivity;
import com.jdp.hls.page.node.protocol.personal.lastst.pay.list.PayListActivity;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.PullToBottomListView;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2019/1/21 0021 下午 3:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaSuperviseListActivity extends BaseTitleActivity {

    @BindView(R.id.plv)
    PullToBottomListView plv;
    private List<AreaSupervise> selectedAreaList;
    private AreaSuperviseAdapter areaSuperviseAdapter;

    @Override
    public void initVariable() {
        selectedAreaList = (List<AreaSupervise>) getIntent().getSerializableExtra(Constants.Extra
                .AreaSuperviseList);
    }

    @OnClick({R.id.set_confirm})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.set_confirm:
                setResultData();
                finish();
                break;
        }
    }

    private void setResultData() {
        Intent intent = new Intent();
        intent.putExtra(Constants.Extra.AreaSuperviseList, (Serializable) areaSuperviseAdapter.getData());
        setResult(Activity.RESULT_OK, intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_list_areasupervise;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "监管区域";
    }

    @Override
    protected void initView() {
        areaSuperviseAdapter = new AreaSuperviseAdapter(this, selectedAreaList);
        plv.setAdapter(areaSuperviseAdapter);
        areaSuperviseAdapter.setOnItemOperListener(new BaseLvAdapter.OnItemOperListener<AreaSupervise>() {
            @Override
            public void onItemDelete(AreaSupervise areaSupervise, int position) {
                DialogUtil.showDoubleDialog(AreaSuperviseListActivity.this, "是否确定删除该项?", (dialog, which) -> {
                    areaSuperviseAdapter.removeItem(position);
                    showCallback();
                    setResultData();
                });
            }

            @Override
            public void onItemClick(AreaSupervise item) {

            }
        });
    }

    @Override
    protected void initData() {
        setRightClick("配置", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                AreaSuperviseAddActivity.goActivity(AreaSuperviseListActivity.this, selectedAreaList);
            }
        });
        showCallback();

    }

    private void showCallback() {
        if (selectedAreaList != null && selectedAreaList.size() > 0) {
            showSuccessCallback();
        }else{
            showEmptyCallback();
        }
    }

    @Override
    public void initNet() {

    }

    public static void goActivity(Activity activity, List<AreaSupervise> areaSuperviseList) {
        Intent intent = new Intent(activity, AreaSuperviseListActivity.class);
        intent.putExtra(Constants.Extra.AreaSuperviseList, (Serializable) areaSuperviseList);
        activity.startActivityForResult(intent, Constants.RequestCode.AreaSuperviseList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.AreaSuperviseAddList:
                    selectedAreaList = (List<AreaSupervise>) data.getSerializableExtra(Constants.Extra
                            .AreaSuperviseList);
                    LogUtil.e(TAG, "修改后的数量:" + selectedAreaList.size());
                    LogUtil.e(TAG, "修改后的集合:" + selectedAreaList.toString());
                    areaSuperviseAdapter.setData(selectedAreaList);
                    showCallback();
                    break;
            }
        }
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
