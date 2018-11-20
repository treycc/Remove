package com.jdp.hls.page.admin.project.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.event.AddProjectEvent;
import com.jdp.hls.event.ModifyProjectEvent;
import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.model.entiy.ProjectItem;
import com.jdp.hls.page.admin.manager.ManagerListActivity;
import com.jdp.hls.page.admin.project.config.ProjectConfigActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;
import com.jdp.hls.view.dialog.AreaDialog;
import com.jdp.hls.view.dialog.StreetDialog;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
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
    private String projectEmployeeIDs = "";
    private List<Employee> selectedEmployees = new ArrayList<>();
    private int provinceId;
    private int cityId;
    private int areaId;
    private int streetId;
    private AreaDialog areaDialog;

    @OnClick({R.id.ll_projectArea, R.id.ll_projectStreet, R.id.ll_Year, R.id.ll_projectEmployee, R.id
            .rl_projectConfig, R.id.rl_projectGroup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_projectArea:
                areaDialog.show();
                break;
            case R.id.ll_projectStreet:
                if (areaId != 0 && DBManager.getInstance().getStreets(areaId).size() > 0) {
                    StreetDialog streetDialog = new StreetDialog(this, areaId);
                    streetDialog.setOnAreaSelectedListener(street -> {
                        tvProjectStreet.setText(street.getRegionName());
                        streetId = street.getRegionIntId();

                    });
                    streetDialog.show();
                } else {

                    ToastUtil.showText("所在地区没有街道");
                }
                break;
            case R.id.ll_Year:
                timePickerDialog.show(getSupportFragmentManager(), String.valueOf(llYear.hashCode()));
                break;
            case R.id.ll_projectEmployee:
                ManagerListActivity.goActivity(this, selectedEmployees);
                break;
            case R.id.rl_projectConfig:
                ProjectConfigActivity.goActivity(this, projectId);
                break;
            case R.id.rl_projectGroup:
                ToastUtil.showText("小组信息");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.MANAGER_LIST:
                    projectEmployeeIDs = data.getStringExtra(Constants.Extra.Ids);
                    String names = data.getStringExtra(Constants.Extra.Names);
                    selectedEmployees = (List<Employee>) data.getSerializableExtra(Constants.Extra.Employees);
                    LogUtil.e(TAG, "带来会数量:" + selectedEmployees.size());
                    tvProjectEmployee.setString(names);
                    break;
            }
        }
    }

    @Override
    public void initVariable() {
        projectId = getIntent().getStringExtra(Constants.Extra.PROJECTID);
        isAddProject = TextUtils.isEmpty(projectId);
        String aresJson = SpSir.getInstance().getAresJson();
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
        areaDialog = new AreaDialog(this);
        areaDialog.setOnAreaSelectedListener((province, city, area) -> {
            provinceId = province.getRegionIntId();
            cityId = city.getRegionIntId();
            areaId = area.getRegionIntId();
            LogUtil.e(TAG, "省：" + province.getRegionName() + provinceId);
            LogUtil.e(TAG, "市：" + city.getRegionName() + cityId);
            LogUtil.e(TAG, "区：" + area.getRegionName() + areaId);
//            LogUtil.e(TAG, "街道：" + DBManager.getInstance().getStreets(areaId).size());
            tvProjectArea.setText(province.getRegionName() + city.getRegionName() + area.getRegionName());

        });

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
                .addFormDataPart("ProvinceId", String.valueOf(provinceId))
                .addFormDataPart("CityId", String.valueOf(cityId))
                .addFormDataPart("AreaId", String.valueOf(areaId))
                .addFormDataPart("StreetId", String.valueOf(streetId))
                .addFormDataPart("Address", address)
                .addFormDataPart("AreaRange", areaRange)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("ProjectEmployeeIDs", projectEmployeeIDs)
                .build());
    }

    @Override
    public void initNet() {
        if (!isAddProject) {
            projectDetailPresenter.getProjectDetail(projectId);
        } else {
            showSuccessCallback();
            llProjectConfig.setVisibility(View.GONE);
        }
    }

    public static void goActivity(Context context, String projectId) {
        Intent intent = new Intent(context, ProjectDetailActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        context.startActivity(intent);
    }

    @Override
    public void onGetProjectDetailSuccess(ProjectItem projectItem) {
        boolean isAllowEdit = projectItem.isIsAllowEdit();
        boolean isAllowView = projectItem.isIsAllowView();
        llProjectConfig.setVisibility(isAllowView ? View.VISIBLE : View.GONE);
        etProjectName.setString(projectItem.getProjectName());
        tvProjectArea.setString(projectItem.getProvinceName() + projectItem.getCityName() + projectItem.getAreaName());
        tvProjectStreet.setString(projectItem.getStreetName());
        etAddress.setString(projectItem.getAddress());
        tvYear.setString(projectItem.getYear());
        tvProjectEmployee.setString(projectItem.getProjectEmployeeName());
        etAreaRange.setString(projectItem.getAreaRange());
        etRemark.setString(projectItem.getRemark());
        provinceId = projectItem.getProvinceId();
        cityId = projectItem.getCityId();
        areaId = projectItem.getAreaId();
        streetId = projectItem.getStreetId();
        projectEmployeeIDs = projectItem.getProjectEmployeeIDs();

        etProjectName.setEnabled(isAllowEdit);
        etAddress.setEnabled(isAllowEdit);
        etAreaRange.setEnabled(isAllowEdit);
        etRemark.setEnabled(isAllowEdit);

        llYear.setEnabled(isAllowEdit);
        llProjectArea.setEnabled(isAllowEdit);
        llProjectStreet.setEnabled(isAllowEdit);
        llProjectEmployee.setEnabled(isAllowEdit);

    }

    @Override
    public void onSaveProjectSuccess(ProjectItem projectItem) {
        if (isAddProject) {
            EventBus.getDefault().post(new AddProjectEvent(projectItem));
        } else {
            EventBus.getDefault().post(new ModifyProjectEvent(projectItem));
        }
        showSuccessDialogAndFinish();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
