package com.jdp.hls.page.admin.project.config;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.jdp.hls.view.FixedListView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;
import okhttp3.MultipartBody;

/**
 * Description:项目配置表
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
    private ProjectConfigAdapter projectConfigAdapter;

    @OnItemClick({R.id.flv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ConfigCompany company = (ConfigCompany) adapterView.getItemAtPosition(position);
        CompanyListActivity.goActivity(this, company);
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
        projectConfigAdapter = new ProjectConfigAdapter(this, null);
        flv.setAdapter(projectConfigAdapter);
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                List<ConfigCompany> configCompanies = projectConfigAdapter.getData();
                StringBuilder companyIdsSb = new StringBuilder();
                StringBuilder companyTypeIdsSb = new StringBuilder();
                for (int i = 0; i < configCompanies.size(); i++) {
                    if (!TextUtils.isEmpty(configCompanies.get(i).getCompanyId())) {
                        companyIdsSb.append(configCompanies.get(i).getCompanyId());
                        companyIdsSb.append("#");

                        companyTypeIdsSb.append(configCompanies.get(i).getCompanyTypeIDS());
                        companyTypeIdsSb.append("#");
                    }
                }
                projectConfigPresenter.modifyProjectConfig(new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("projectId", projectId)
                        .addFormDataPart("companyIds", companyIdsSb.toString())
                        .addFormDataPart("companyTypeIds", companyTypeIdsSb.toString())
                        .build());
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
            projectConfigAdapter.setData(companyList);
        }
    }

    @Override
    public void onModifyProjectConfigSuccess() {
        showSuccessToastAndFinish();
    }

    public static void goActivity(Context context, String projectId) {
        Intent intent = new Intent(context, ProjectConfigActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.COMPANY_LIST:
                    ConfigCompany configCompany = (ConfigCompany) data.getSerializableExtra(Constants.Extra
                            .ConfigCompany);
                    projectConfigAdapter.modifyItem(configCompany);
                    break;
            }
        }
    }
}
