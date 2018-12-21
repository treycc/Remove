package com.jdp.hls.page.admin.project.list;

import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.ProjectAdminAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.event.AddProjectEvent;
import com.jdp.hls.event.ModifyProjectEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ProjectItem;
import com.jdp.hls.model.entiy.ProjectListInfo;
import com.jdp.hls.page.admin.project.detail.ProjectDetailActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
public class ProjectListAdminActivity extends BaseTitleActivity implements ProjectListContract.View {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @Inject
    ProjectListPresenter projectListPresenter;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    private ProjectAdminAdapter projectAdapter;
    private boolean isAllowEdit;
    private boolean isAllowView;

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
        if (isAllowView) {
            ProjectItem projectItem = (ProjectItem) adapterView.getItemAtPosition(position);
            ProjectDetailActivity.goActivity(this, projectItem.getProjectId());
        }else{
            ToastUtil.showText("没有操作权限");
        }
    }

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
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
        projectListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "项目管理";
    }

    @Override
    protected void initView() {
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                LogUtil.e(TAG, "afterTextChanged:" + s.toString());
                projectAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    protected void initData() {
        etKeyword.setHint("请输入项目名称/地址/负责人/系统编号");
        srl.stepRefresh(this);
        projectAdapter = new ProjectAdminAdapter(this, null);
        plv.setAdapter(projectAdapter);
    }

    @Override
    public void initNet() {
        projectListPresenter.getProjectList();
    }

    @Override
    public void onGetProjectListSuccess(ProjectListInfo projectListInfo) {
        isAllowEdit = projectListInfo.isIsAllowEdit();
        isAllowView = projectListInfo.isIsAllowView();
        String keyword = etKeyword.getText().toString().trim();
        setSearchListView(projectListInfo.getLstProject(), projectAdapter, keyword);

        if (isAllowEdit) {
            setRightClick("创建项目", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    GoUtil.goActivity(ProjectListAdminActivity.this, ProjectDetailActivity.class);
                }
            });
        }
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addProject(AddProjectEvent event) {
        LogUtil.e(TAG, "增加项目");
        projectAdapter.addFirst(event.getProjectItem());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyProject(ModifyProjectEvent event) {
        LogUtil.e(TAG, "修改项目");
        projectAdapter.modifyProject(event.getProjectItem());
    }
}
