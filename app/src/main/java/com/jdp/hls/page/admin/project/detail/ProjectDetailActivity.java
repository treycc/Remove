package com.jdp.hls.page.admin.project.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ProjectItem;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.EncryptUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 下午 2:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectDetailActivity extends BaseTitleActivity implements ProjecDetailContract.View {

    @BindView(R.id.et_projectName)
    EnableEditText etProjectName;
    @BindView(R.id.tv_projectArea)
    StringTextView tvProjectArea;
    @BindView(R.id.ll_projectArea)
    LinearLayout llProjectArea;
    @BindView(R.id.tv_projectStreet)
    StringTextView tvProjectStreet;
    @BindView(R.id.ll_projectStreet)
    LinearLayout llProjectStreet;
    @BindView(R.id.et_address)
    EnableEditText etAddress;
    @BindView(R.id.tv_year)
    StringTextView tvYear;
    @BindView(R.id.ll_Year)
    LinearLayout llYear;
    @BindView(R.id.tv_projectEmployee)
    StringTextView tvProjectEmployee;
    @BindView(R.id.ll_projectEmployee)
    LinearLayout llProjectEmployee;
    @BindView(R.id.et_areaRange)
    EnableEditText etAreaRange;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.rl_projectConfig)
    RelativeLayout rlProjectConfig;
    @BindView(R.id.rl_projectGroup)
    RelativeLayout rlProjectGroup;
    @BindView(R.id.ll_projectConfig)
    LinearLayout llProjectConfig;
    private boolean isAddProject;
    @Inject
    ProjectDetailPresenter projectDetailPresenter;
    private String projectId;
    private TimePickerDialog timePickerDialog;

    @OnClick({R.id.ll_projectArea, R.id.ll_projectStreet, R.id.ll_Year, R.id.ll_projectEmployee})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_projectArea:
                break;
            case R.id.ll_projectStreet:
                break;
            case R.id.ll_Year:
                timePickerDialog.show(getSupportFragmentManager(), String.valueOf(llYear.hashCode()));
                break;
            case R.id.ll_projectEmployee:
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {
        projectId = getIntent().getStringExtra(Constants.Extra.PROJECTID);
        isAddProject = TextUtils.isEmpty(projectId);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_project_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        projectDetailPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return isAddProject ? "增加" : "修改";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                saveProject();
            }
        });
        timePickerDialog = new TimePickerDialog.Builder()
                .setType(Type.YEAR)
                .setThemeColor(ContextCompat.getColor(this, R.color.main))
                .setWheelItemTextSize(15)
                .setTitleStringId("请选择年份")
                .setCallBack((timePickerView, millseconds) -> {
                    tvYear.setText(DateUtil.getDateString(millseconds, "yyyy"));
                })
                .build();

    }

    private void saveProject() {
        String projectName = etProjectName.getText().toString().trim();
        String year = tvYear.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String areaRange = etAreaRange.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        projectDetailPresenter.saveProject(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("ProjectId", TextUtils.isEmpty(projectId) ? "" : projectId)
                .addFormDataPart("ProjectName", projectName)
                .addFormDataPart("Year", year)
                .addFormDataPart("ProvinceId", "")
                .addFormDataPart("CityId", "")
                .addFormDataPart("AreaId", "")
                .addFormDataPart("StreetId", "")
                .addFormDataPart("Address", address)
                .addFormDataPart("AreaRange", areaRange)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("ProjectEmployeeIDs", "")
                .build());
    }

    @Override
    public void initNet() {
        if (!isAddProject) {
            projectDetailPresenter.getProjectDetail(projectId);
        }
    }

    public static void goActivity(Context context, String projectId) {
        Intent intent = new Intent(context, ProjectDetailActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onGetProjectDetailSuccess(ProjectItem projectItem) {
        etProjectName.setString(projectItem.getProjectName());
        tvProjectArea.setString(projectItem.getProjectName() + projectItem.getCityName() + projectItem.getAreaName());
        tvProjectStreet.setString(projectItem.getStreetName());
        etAddress.setString(projectItem.getAddress());
        tvYear.setString(projectItem.getYear());
        tvProjectEmployee.setString(projectItem.getProjectEmployeeName());
        etAreaRange.setString(projectItem.getAreaRange());
        etRemark.setString(projectItem.getRemark());
    }

    @Override
    public void onAddProjectSuccess(ProjectItem projectItem) {

    }

    @Override
    public void onSaveProjectSuccess(ProjectItem projectItem) {

    }
}
