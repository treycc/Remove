package com.jdp.hls.page.admin.query;

import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.ProjectSelectAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ProjectItem;
import com.jdp.hls.page.admin.query.list.QueryListActivity;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 下午 2:19
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectSelectActivity extends BaseTitleActivity implements ProjectSelectContract.View {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @Inject
    ProjectSelectPresenter projectSelectPresenter;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    private ProjectSelectAdapter projectAdapter;

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
        ProjectItem projectItem = (ProjectItem) adapterView.getItemAtPosition(position);
        QueryListActivity.GoActivity(this, projectItem.getProjectId(), projectItem.getProjectName());
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
        projectSelectPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "选择项目";
    }

    @Override
    protected void initView() {
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                projectAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    protected void initData() {
        srl.stepRefresh(this);
        projectAdapter = new ProjectSelectAdapter(this, null);
        plv.setAdapter(projectAdapter);
    }

    @Override
    public void initNet() {
        projectSelectPresenter.getQueryProjectList();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onGetProjectListSuccess(List<ProjectItem> projectItemList) {
        String keyword = etKeyword.getText().toString().trim();
        setSearchListView(projectItemList, projectAdapter, keyword);
    }
}
