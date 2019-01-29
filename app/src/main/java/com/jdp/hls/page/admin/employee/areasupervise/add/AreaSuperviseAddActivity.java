package com.jdp.hls.page.admin.employee.areasupervise.add;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.AreaSuperviseConfigAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AreaSupervise;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2019/1/21 0021 下午 3:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaSuperviseAddActivity extends BaseTitleActivity implements AreaSuperviseListContract.View {
    @BindView(R.id.lv)
    ListView lv;
    private AreaSuperviseConfigAdapter areaSuperviseConfigAdapter;
    private List<AreaSupervise> selectedAreaList;
    @Inject
    AreaSuperviseListPresenter areaSuperviseListPresenter;

    @OnItemClick({R.id.lv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        AreaSupervise areaSupervise = (AreaSupervise) adapterView.getItemAtPosition(position);
        List<Integer> areaIndexs = areaSuperviseConfigAdapter.getChildrenIndexs(areaSupervise.getRegionId());
        // 1.如果选中则不打开(已选中子节点)
        if (areaSupervise.isSelected()) {
            ToastUtil.showText("已选择该级权限");
            return;
        }
        // 2.如果没有孩子则不打开(没有下级区域)

        if (areaIndexs == null || areaIndexs.size() == 0) {
            ToastUtil.showText("没有下级区域");
            return;
        }

        // 3.如果已经打开则关闭
        if (areaSupervise.isExpand()) {
            // 展开=》关闭
            areaSupervise.setExpand(false);
        } else {
            // 关闭=》展开
            areaSupervise.setExpand(true);
            //添加子节点
            if (areaSupervise.getChildren() == null) {
                List<AreaSupervise> children = areaSuperviseConfigAdapter.getChildrenList(areaIndexs);
                areaSuperviseConfigAdapter.addChildren(areaSupervise, children);
            }
        }
        //刷新
        areaSuperviseConfigAdapter.refresh();
    }

    private boolean isSelected(int regionIntId) {
        if (selectedAreaList != null && selectedAreaList.size() > 0) {
            for (AreaSupervise areaSupervise : selectedAreaList) {
                if (areaSupervise.getRegionId() == regionIntId) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void initVariable() {
        selectedAreaList = (List<AreaSupervise>) getIntent().getSerializableExtra(Constants.Extra
                .AreaSuperviseList);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_areasupervise_add;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        areaSuperviseListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "监管区域";
    }

    @Override
    protected void initView() {
        areaSuperviseConfigAdapter = new AreaSuperviseConfigAdapter(this, null,selectedAreaList);
        lv.setAdapter(areaSuperviseConfigAdapter);
    }

    @Override
    protected void initData() {
        setRightClick("确定", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                List<AreaSupervise> selectedArea = areaSuperviseConfigAdapter.getSelectedArea();
                Intent intent = new Intent();
                intent.putExtra(Constants.Extra.AreaSuperviseList, (Serializable) selectedArea);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void initNet() {
        areaSuperviseListPresenter.getAreaSuperviseList();
    }

    @Override
    public void onGetAreaSuperviseList(List<AreaSupervise> areaSuperviseList) {
        areaSuperviseConfigAdapter.setData(areaSuperviseList);
    }

    public static void goActivity(Activity activity, List<AreaSupervise> areaSuperviseList) {
        Intent intent = new Intent(activity, AreaSuperviseAddActivity.class);
        intent.putExtra(Constants.Extra.AreaSuperviseList, (Serializable) areaSuperviseList);
        activity.startActivityForResult(intent, Constants.RequestCode.AreaSuperviseAddList);
    }
}
