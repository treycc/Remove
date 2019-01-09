package com.jdp.hls.page.projects;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ProjectSearchAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.event.RefreshRostersEvent;
import com.jdp.hls.event.SwitchProjectEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Project;
import com.jdp.hls.page.home.HomeActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.InputMethodManagerUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.MultipartBody;

/**
 * Description:项目列表
 * Create Time:2018/7/26 0026 下午 3:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectListActivity extends BaseTitleActivity implements ProjectsContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout rsrl;
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @Inject
    ProjectsPresenter projectsPresenter;
    private ProjectSearchAdapter projectSearchAdapter;

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etKeyword.setText("");
                break;
        }
    }
    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Project project = (Project) adapterView.getItemAtPosition(position);
        projectsPresenter.switchProject(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("ProjectId", project.getProjectId())
                .build(), project);
    }

    @Override
    public void initVariable() {
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search_list;
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
        return "项目选择";
    }


    @Override
    protected void initView() {
        projectsPresenter.attachView(this);
        projectSearchAdapter = new ProjectSearchAdapter(this, null);
        plv.setAdapter(projectSearchAdapter);
    }

    @Override
    public void showLoading() {
        rsrl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        rsrl.setRefreshing(false);
    }

    @Override
    protected void initData() {
        etKeyword.setHint("请输入项目名称/地址/负责人");
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                projectSearchAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
        rsrl.stepRefresh(this);
    }


    @Override
    public void initNet() {
        projectsPresenter.getProjects(SpSir.getInstance().getEmployeeId());
    }


    @Override
    public void onGetProjectsSuccess(List<Project> projects) {
        String keyword = etKeyword.getText().toString().trim();
       setSearchListView(projects,projectSearchAdapter,keyword);
    }

    @Override
    public void onSwitchProjectSuccess(Project project) {
        SpSir.getInstance().setProjectId(project.getProjectId());
        SpSir.getInstance().setProjectName(project.getProjectName());
        EventBus.getDefault().post(new RefreshRostersEvent());
        EventBus.getDefault().post(new SwitchProjectEvent());
        GoUtil.goActivityAndFinish(this, HomeActivity.class);
    }

    @Override
    protected void onDestroy() {
        InputMethodManagerUtil.fixInputMethodManagerLeak(this);
        super.onDestroy();
    }
}
