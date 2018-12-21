package com.jdp.hls.page.admin.employee.add.projectlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.jdp.hls.R;
import com.jdp.hls.adapter.SuperviseProjectAdapter;
import com.jdp.hls.adapter.SuperviseProjectSelectedAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Project;
import com.jdp.hls.page.projects.ProjectsContract;
import com.jdp.hls.page.projects.ProjectsPresenter;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.FixedListView;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;
import com.jdp.hls.view.StringTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/19 0019 下午 1:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SuperviseProjectListActivity extends BaseTitleActivity implements ProjectsContract.View {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    @BindView(R.id.bottomSheetLayout)
    BottomSheetLayout bottomSheetLayout;
    @BindView(R.id.tv_count)
    StringTextView tvCount;
    @Inject
    ProjectsPresenter projectsPresenter;
    @BindView(R.id.cb_allProjects)
    CheckBox cbAllProjects;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private SuperviseProjectAdapter projectAdapter;
    private View bottomSheetView;
    private SuperviseProjectSelectedAdapter selectedProjectAdapter;
    private List<Project> selectedProjects = new ArrayList<>();
    private List<String> selectProjectIds = new ArrayList<>();
    private boolean isManageAllProjects;

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Project project = (Project) adapterView.getItemAtPosition(position);
        if (!projectAdapter.isSelected(project)) {
            projectAdapter.addSelected(project);
            tvCount.setString(selectedProjectAdapter.getCount());
            cbAllProjects.setChecked(false);
        }
    }

    @OnClick({R.id.ll_bottom, R.id.tv_confirm, R.id.iv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_bottom:
                if (selectedProjectAdapter.getCount() == 0) {
                    return;
                }
                if (bottomSheetLayout.isSheetShowing()) {
                    bottomSheetLayout.dismissSheet();
                } else {
                    bottomSheetLayout.showWithSheetView(bottomSheetView);
                }
                break;

            case R.id.tv_confirm:
                if (selectedProjectAdapter.getCount() == 0) {
                    ToastUtil.showText("请选择监管项目");
                    return;
                } else {
                    List<Project> selectedProjects = selectedProjectAdapter.getData();
                    StringBuilder selectedProjectIds = new StringBuilder();
                    for (int i = 0; i < selectedProjects.size(); i++) {
                        selectedProjectIds.append(selectedProjects.get(i).getProjectId());
                        if (i != selectedProjects.size() - 1) {
                            selectedProjectIds.append("#");
                        }
                    }
                    Intent intent = new Intent();
                    intent.putExtra(Constants.Extra.Ids, selectedProjectIds.toString());
                    intent.putExtra(Constants.Extra.IsManageAllProjects, false);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }

                break;
            case R.id.iv_clear:
                etKeyword.setText("");
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {
        String selectedProjectIds = getIntent().getStringExtra(Constants.Extra.Ids);
        isManageAllProjects = getIntent().getBooleanExtra(Constants.Extra.IsManageAllProjects, false);
        if (TextUtils.isEmpty(selectedProjectIds)) {
            return;
        }
        String[] ids = selectedProjectIds.split("#");
        if (ids.length > 0) {
            selectProjectIds = Arrays.asList(ids);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_supervise_project_list;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        projectsPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "选择监管项目";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        cbAllProjects.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!buttonView.isPressed()) {
                return;
            }
            isManageAllProjects = isChecked;
            if (isChecked) {
                Intent intent = new Intent();
                intent.putExtra(Constants.Extra.Ids, "");
                intent.putExtra(Constants.Extra.IsManageAllProjects, true);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        cbAllProjects.setChecked(isManageAllProjects);

        bottomSheetView = View.inflate(this, R.layout.layout_manager_list, null);
        FixedListView selectedLv = bottomSheetView.findViewById(R.id.flv_selected);
        projectAdapter = new SuperviseProjectAdapter(this, null, selectedProjects);

        selectedProjectAdapter = new SuperviseProjectSelectedAdapter(this, selectedProjects);
        plv.setAdapter(projectAdapter);
        selectedLv.setAdapter(selectedProjectAdapter);
        selectedLv.setOnItemClickListener((parent, view, position, id) -> {
            Project project = (Project) parent.getItemAtPosition(position);
            projectAdapter.removeSelected(project);
            selectedProjectAdapter.notifyDataSetChanged();
            tvCount.setString(selectedProjectAdapter.getCount());
        });
        tvCount.setString(selectProjectIds.size());
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                projectAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
        srl.stepRefresh(this);
    }

    @Override
    public void initNet() {
        projectsPresenter.getProjects(SpSir.getInstance().getEmployeeId());
    }

    public static void goActivity(Activity activity, String projectIds, boolean isManageAllProjects) {
        Intent intent = new Intent(activity, SuperviseProjectListActivity.class);
        intent.putExtra(Constants.Extra.Ids, projectIds);
        intent.putExtra(Constants.Extra.IsManageAllProjects, isManageAllProjects);
        activity.startActivityForResult(intent, Constants.RequestCode.SuperviseProjectList);
    }

    @Override
    public void showLoading() {
        srl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srl.setRefreshing(false);
    }

    @Override
    public void onGetProjectsSuccess(List<Project> projects) {
        selectedProjects.clear();
        for (Project project : projects) {
            if (selectProjectIds.contains(project.getProjectId())) {
                selectedProjects.add(project);
            }
        }
        selectedProjectAdapter.setData(selectedProjects);
        String keyword = etKeyword.getText().toString().trim();
        setSearchListView(projects, projectAdapter, keyword);
    }

    @Override
    public void onSwitchProjectSuccess(Project project) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
