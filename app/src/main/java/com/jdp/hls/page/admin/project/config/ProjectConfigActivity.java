package com.jdp.hls.page.admin.project.config;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.ProjectConfigAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Company;
import com.jdp.hls.model.entiy.ConfigCompany;
import com.jdp.hls.page.admin.project.company.CompanyListActivity;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.FixedListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/20 0020 下午 7:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectConfigActivity extends BaseTitleActivity implements ProjecConfigContract.View {
    @BindView(R.id.flv)
    FixedListView flv;
    @Inject
    ProjectConfigPresenter projectConfigPresenter;
    private String projectId;
    private Map<Integer, List<Company>> comanyMap = new HashMap<>();

    @OnItemClick({R.id.flv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ConfigCompany company = (ConfigCompany) adapterView.getItemAtPosition(position);
        CompanyListActivity.goActivity(this, company.getCompanyTypeID(), comanyMap.get(company.getCompanyTypeID()));
    }

    @Override
    public void initVariable() {
        projectId = getIntent().getStringExtra(Constants.Extra.PROJECTID);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_project_config;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        projectConfigPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "项目配置表";
    }

    @Override
    protected void initView() {

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
        projectConfigPresenter.getProjectConfig(projectId);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onGetProjectConfigSuccess(List<ConfigCompany> companyList) {
        if (companyList != null && companyList.size() > 0) {
            flv.setAdapter(new ProjectConfigAdapter(this, companyList));
        }
    }

    @Override
    public void onModifyProjectConfigSuccess() {

    }

    public static void goActivity(Context context, String projectId) {
        Intent intent = new Intent(context, ProjectConfigActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        context.startActivity(intent);
    }
}
