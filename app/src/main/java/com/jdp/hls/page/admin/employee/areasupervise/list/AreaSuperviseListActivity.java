package com.jdp.hls.page.admin.employee.areasupervise.list;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.adapter.AreaSuperviseAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AreaSupervise;
import com.jdp.hls.page.admin.employee.areasupervise.add.AreaSuperviseAddActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.PullToBottomListView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2019/1/21 0021 下午 3:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaSuperviseListActivity extends BaseTitleActivity {

    @BindView(R.id.plv)
    PullToBottomListView plv;
    private List<AreaSupervise> areaSuperviseList;
    private AreaSuperviseAdapter areaSuperviseAdapter;

    @Override
    public void initVariable() {
        areaSuperviseList = (List<AreaSupervise>) getIntent().getSerializableExtra(Constants.Extra
                .AreaSuperviseList);
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
        return "监管区域";
    }

    @Override
    protected void initView() {
        areaSuperviseAdapter = new AreaSuperviseAdapter(this, areaSuperviseList);
        plv.setAdapter(areaSuperviseAdapter);
    }

    @Override
    protected void initData() {
        setRightClick("增加区域", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                GoUtil.goActivity(AreaSuperviseListActivity.this,AreaSuperviseAddActivity.class);
            }
        });
    }

    @Override
    public void initNet() {

    }

    public static void goActivity(Activity activity, List<AreaSupervise> areaSuperviseList) {
        Intent intent = new Intent(activity, AreaSuperviseListActivity.class);
        intent.putExtra(Constants.Extra.AreaSuperviseList, (Serializable) areaSuperviseList);
        activity.startActivityForResult(intent, Constants.RequestCode.AreaSuperviseList);
    }
}
