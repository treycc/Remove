package com.kingja.zhongminremove.activity;

import android.view.View;
import android.widget.AdapterView;

import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.adapter.CommonAdapter;
import com.kingja.zhongminremove.adapter.ViewHolder;
import com.kingja.zhongminremove.base.BaseTitleActivity;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.model.entiy.Project;
import com.kingja.zhongminremove.util.GoUtil;
import com.kingja.zhongminremove.view.PullToBottomListView;
import com.kingja.zhongminremove.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/7/26 0026 下午 3:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectListActivity extends BaseTitleActivity {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private List<Project> projects = new ArrayList<>();
    private CommonAdapter adapter;


    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        GoUtil.goActivityAndFinish(this,HomeActivity.class);
    }
    @Override
    public void initVariable() {
        for (int i = 0; i < 10; i++) {
            projects.add(new Project("三垟湿地二期" + i, "浙江省温州市龙湾区三垟湿地，大龙街道200号-354号" + i, "89", "司马懿" + i));
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "项目选择";
    }


    @Override
    protected void initView() {
        plv.setAdapter(adapter = new CommonAdapter<Project>(this, projects, R.layout.item_project) {
            @Override
            public void convert(ViewHolder helper, Project item) {
                helper.setText(R.id.tv_projectName, item.getName());
                helper.setText(R.id.tv_projectYear, item.getYear());
                helper.setText(R.id.tv_projectAddress, item.getAddress());
                helper.setText(R.id.tv_projectManager, item.getPerson());
            }
        });

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initNet() {

    }

}
