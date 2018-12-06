package com.jdp.hls.page.projects;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
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
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshableSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
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
    RefreshableSwipeRefreshLayout srl;
    private List<Project> projects = new ArrayList<>();
    private CommonAdapter adapter;
    private boolean isFromLocal;
    @Inject
    ProjectsPresenter projectsPresenter;


    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Project project = (Project) adapterView.getItemAtPosition(position);
//        LogUtil.e(TAG, "ProjectId:" + project.getProjectId());
//        if (isFromLocal) {
//            SpSir.getInstance().setProjectId(project.getProjectId());
//            SpSir.getInstance().setProjectName(project.getProjectName());
//            GoUtil.goActivityAndFinish(this, HomeActivity.class);
//        } else {
//            SpSir.getInstance().setProjectId(project.getProjectId());
//            SpSir.getInstance().setProjectName(project.getProjectName());
//            EventBus.getDefault().post(new RefreshRostersEvent());
//            Intent intent = new Intent();
//            intent.putExtra("projectName", project.getProjectName());
//            setResult(Activity.RESULT_OK, intent);
//            finish();
//        }

        projectsPresenter.switchProject(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("ProjectId", project.getProjectId())
                .build(), project);
    }

    @Override
    public void initVariable() {
        List<Project> transitivedpProjects = (List<Project>) getIntent().getSerializableExtra("projects");
        if (transitivedpProjects != null) {
            isFromLocal = true;
            projects = transitivedpProjects;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv_sl;
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
        plv.setAdapter(adapter = new CommonAdapter<Project>(this, projects, R.layout.item_supervise_project) {
            @Override
            public void convert(ViewHolder helper, Project item) {
                helper.setText(R.id.tv_projectName, item.getProjectName());
                helper.setText(R.id.tv_projectYear, String.valueOf(item.getYear()));
                helper.setText(R.id.tv_address, item.getAddress());
                helper.setText(R.id.tv_realName, item.getRealName());
                helper.setText(R.id.tv_percentDesc, item.getPercentDesc());
                helper.setText(R.id.tv_totalQuantity, item.getTotalQuantity() + "户");
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean ifHideBack() {
        return isFromLocal;
    }

    @Override
    public void initNet() {
        if (!isFromLocal) {
            projectsPresenter.getProjects(SpSir.getInstance().getEmployeeId());
        }
    }

    public static void goActivity(Activity activity, List<Project> projects) {
        Intent intent = new Intent(activity, ProjectListActivity.class);
        intent.putExtra("projects", (Serializable) projects);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void onGetProjectsSuccess(List<Project> projects) {
        adapter.setData(projects);
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
