package com.jdp.hls.page.admin.employee.areasupervise.add;

import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.AreaSuperviseConfigAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AreaSupervise;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.FixedListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    FixedListView lv;
    private AreaSuperviseConfigAdapter areaSuperviseConfigAdapter;
    private Map<Integer, List<Integer>> sourceMap = new HashMap<>();
    private List<Area> areasList;
    @Inject
    AreaSuperviseListPresenter areaSuperviseListPresenter;

    @OnItemClick({R.id.lv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {

        LogUtil.e(TAG, "position:" + position);
        AreaSupervise areaSupervise = (AreaSupervise) adapterView.getItemAtPosition(position);
        List<Integer> areaIndexs = sourceMap.get(areaSupervise.getRegionId());


        // 1.如果选中则不打开(已选中子节点)
        if (areaSupervise.isSelected()) {
            ToastUtil.showText("已经选中所有子节点");
            return;
        }

        // 2.如果没有孩子则不打开(没有下级区域)

        if (areaIndexs == null || areaIndexs.size() == 0) {
            ToastUtil.showText("没有子节点");
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
                List<AreaSupervise> children = getChildren(areaIndexs);
                for (AreaSupervise child : children) {
                    child.setParent(areaSupervise);
                }
                areaSupervise.setChildren(children);
                areaSuperviseConfigAdapter.addChildren(areaSupervise, children);
            }

        }
        //刷新
        areaSuperviseConfigAdapter.refresh();


    }

    private List<AreaSupervise> getChildren(List<Integer> areaIndexs) {
        List<AreaSupervise> areaList = new ArrayList<>();
        for (Integer index : areaIndexs) {
            Area area = areasList.get(index);
            areaList.add(new AreaSupervise(area.getLevel(), area.getRegionIntId(), area.getRegionName(), area
                    .getParentId()));
        }
        return areaList;
    }

    private void initAreas() {
        areasList = DBManager.getInstance().getAreas();
        for (int i = 0; i < areasList.size(); i++) {
            Area area = areasList.get(i);
            if (sourceMap.get(area.getParentId()) == null) {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                sourceMap.put(area.getParentId(), indexList);
            } else {
                List<Integer> indexList = sourceMap.get(area.getParentId());
                indexList.add(i);
                sourceMap.put(area.getParentId(), indexList);
            }
        }
    }

    @Override
    public void initVariable() {
        initAreas();
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
        areaSuperviseConfigAdapter = new AreaSuperviseConfigAdapter(this, null);
        lv.setAdapter(areaSuperviseConfigAdapter);
    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {

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
}
