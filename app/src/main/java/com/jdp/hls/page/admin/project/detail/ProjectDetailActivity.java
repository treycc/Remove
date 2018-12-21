package com.jdp.hls.page.admin.project.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.event.AddProjectEvent;
import com.jdp.hls.event.ModifyProjectEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.model.entiy.ProjectItem;
import com.jdp.hls.model.entiy.TaoType;
import com.jdp.hls.page.admin.contrast.ProjectContrastDetailActivity;
import com.jdp.hls.page.admin.group.list.GroupListActivity;
import com.jdp.hls.page.admin.manager.ManagerListActivity;
import com.jdp.hls.page.admin.project.config.ProjectConfigActivity;
import com.jdp.hls.page.admin.project.detail.tao.TaoTypeListAddActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;
import com.jdp.hls.view.dialog.AreaDialog;
import com.jdp.hls.view.dialog.ConfirmDialog;
import com.jdp.hls.view.dialog.StreetDialog;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.et_estimateTotalBuildings)
    EnableEditText etEstimateTotalBuildings;
    @BindView(R.id.tv_taoType)
    StringTextView tvTaoType;
    @BindView(R.id.ll_taoType)
    LinearLayout llTaoType;
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
    private String year;
    private StreetDialog streetDialog;
    private List<TaoType> taoTypeList = new ArrayList<>();
    private String deletePatternIDs;
    private String jsonProjectPatterns = "";

    @OnClick({R.id.ll_projectArea, R.id.ll_projectStreet, R.id.ll_Year, R.id.ll_projectEmployee, R.id
            .rl_projectConfig, R.id.rl_projectGroup, R.id.rl_projectContrast, R.id.ll_taoType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_taoType:
                TaoTypeListAddActivity.goActivity(this, taoTypeList);
                break;
            case R.id.ll_projectArea:
                areaDialog.show();
                break;
            case R.id.ll_projectStreet:
                if (areaId != 0 && DBManager.getInstance().getStreets(areaId).size() > 0) {
                    streetDialog.show();
                } else {
                    ToastUtil.showText("所在地区没有街道");
                }
                break;
            case R.id.ll_Year:
                showYearSelector();
                break;
            case R.id.ll_projectEmployee:
                ManagerListActivity.goActivity(this, selectedEmployees);
                break;
            case R.id.rl_projectConfig:
                ProjectConfigActivity.goActivity(this, projectId);
                break;
            case R.id.rl_projectGroup:
                GroupListActivity.goActivity(this, projectId);
                break;
            case R.id.rl_projectContrast:
                ProjectContrastDetailActivity.goActivity(this, projectId);
                break;
            default:
                break;
        }
    }

    private void showYearSelector() {
        year = tvYear.getText().toString().trim();
        timePickerDialog = new TimePickerDialog.Builder()
                .setType(Type.YEAR)
                .setThemeColor(ContextCompat.getColor(this, R.color.main))
                .setWheelItemTextSize(15)
                .setCurrentMillseconds(!TextUtils.isEmpty(year) ? DateUtil.getMillSeconds(year, "yyyy") : DateUtil
                        .getYearMillSeconds(+1))
                .setMinMillseconds(DateUtil.getYearMillSeconds(-20))
                .setMaxMillseconds(DateUtil.getYearMillSeconds(+1))
                .setTitleStringId("请选择年份")
                .setCallBack((timePickerView, millseconds) -> {
                    tvYear.setText(DateUtil.getDateString(millseconds, "yyyy"));
                })
                .build();
        timePickerDialog.show(getSupportFragmentManager(), String.valueOf(llYear.hashCode()));
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
                case Constants.RequestCode.TaoTypeListAdd:
                    deletePatternIDs = data.getStringExtra(Constants.Extra.DELETEIDS);
                    taoTypeList = (List<TaoType>) data.getSerializableExtra(Constants.Extra.TaoTypeList);
                    if (taoTypeList != null && taoTypeList.size() > 0) {
                        tvTaoType.setHint(String.format("已配置%d个套型", taoTypeList.size()));
                    }

                    break;
            }
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
        if (isAddProject) {
            initAreaDialog();
            initStreetDialog();
        }
    }

    private void saveProject() {
        String projectName = etProjectName.getText().toString().trim();
        year = tvYear.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String areaRange = etAreaRange.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        String estimateTotalBuildings = etEstimateTotalBuildings.getText().toString().trim();
        if (taoTypeList != null && taoTypeList.size() > 0) {
            jsonProjectPatterns = new Gson().toJson(taoTypeList);
        }
        if (CheckUtil.checkEmpty(projectName, "请输入项目名称")
                && CheckUtil.checkEmpty(String.valueOf(provinceId), "请选择项目区域")
                && CheckUtil.checkEmpty(year, "请选择年份")
                && CheckUtil.checkEmpty(projectEmployeeIDs, "请选择负责人")) {
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
                    .addFormDataPart("EstimateTotalBuildings", estimateTotalBuildings)
                    .addFormDataPart("ProjectEmployeeIDs", projectEmployeeIDs)
                    .addFormDataPart("JsonProjectPatterns", jsonProjectPatterns)
                    .addFormDataPart("DeletePatternIDs", TextUtils.isEmpty(deletePatternIDs) ? "" : deletePatternIDs)
                    .build());
        }
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
        String projectEmployeeNames = projectItem.getProjectEmployeeName();
        projectEmployeeIDs = projectItem.getProjectEmployeeIDs();

        fillEmployeeList(projectEmployeeIDs, projectEmployeeNames);

        tvProjectEmployee.setString(projectEmployeeNames);
        etAreaRange.setString(projectItem.getAreaRange());
        etRemark.setString(projectItem.getRemark());
        provinceId = projectItem.getProvinceId();
        cityId = projectItem.getCityId();
        lastAreaId = areaId = projectItem.getAreaId();
        streetId = projectItem.getStreetId();
        etEstimateTotalBuildings.setString(projectItem.getEstimateTotalBuildings());
        etProjectName.setEnabled(isAllowEdit);
        etAddress.setEnabled(isAllowEdit);
        etAreaRange.setEnabled(isAllowEdit);
        etRemark.setEnabled(isAllowEdit);
        initAreaDialog();
        initStreetDialog();
        llYear.setEnabled(isAllowEdit);
        llProjectArea.setEnabled(isAllowEdit);
        llProjectStreet.setEnabled(isAllowEdit);
        llProjectEmployee.setEnabled(isAllowEdit);
        etEstimateTotalBuildings.setEnabled(isAllowEdit);
        taoTypeList = projectItem.getProjectPatternNames();

        if (taoTypeList != null && taoTypeList.size() > 0) {
            tvTaoType.setHint(String.format("已配置%d个套型", taoTypeList.size()));
        }

    }

    private void fillEmployeeList(String projectEmployeeIDs, String projectEmployeeNames) {
        if (TextUtils.isEmpty(projectEmployeeIDs) || TextUtils.isEmpty(projectEmployeeNames)) {
            return;
        }
        String[] employeeIds = projectEmployeeIDs.split("#");
        String[] employeeNames = projectEmployeeNames.split(",");

        if (employeeIds.length != employeeNames.length) {
            return;
        }

        selectedEmployees.clear();
        for (int i = 0; i < employeeIds.length; i++) {
            Employee employee = new Employee();
            employee.setRealName(employeeNames[i]);
            employee.setEmployeeId(Integer.valueOf(employeeIds[i]));
            selectedEmployees.add(employee);
        }

    }

    private int lastAreaId;

    private void initAreaDialog() {
        areaDialog = new AreaDialog(this, provinceId, cityId, areaId);
        areaDialog.setOnAreaSelectedListener((province, city, area) -> {
            provinceId = province.getRegionIntId();
            cityId = city.getRegionIntId();
            areaId = area.getRegionIntId();
            tvProjectArea.setText(province.getRegionName() + city.getRegionName() + area.getRegionName());
            if (lastAreaId != areaId) {
                streetId = 0;
                tvProjectStreet.setText("");
            }
            if (areaId != 0) {
                streetDialog.nodifySetData(areaId, streetId);
            }
            lastAreaId = areaId;

        });
    }

    private void initStreetDialog() {
        streetDialog = new StreetDialog(this, areaId, streetId);
        streetDialog.setOnAreaSelectedListener(street -> {
            tvProjectStreet.setText(street.getRegionName());
            streetId = street.getRegionIntId();
        });
    }

    @Override
    public void onSaveProjectSuccess(ProjectItem projectItem) {
        if (isAddProject) {
            EventBus.getDefault().post(new AddProjectEvent(projectItem));
            llProjectConfig.setVisibility(View.VISIBLE);
            projectId = projectItem.getProjectId();
            DialogUtil.createSingleDialog(this, "创建成功，您可以进行项目配置了", new ConfirmDialog.OnConfirmListener() {
                @Override
                public void onConfirm() {
                }
            });
        } else {
            EventBus.getDefault().post(new ModifyProjectEvent(projectItem));
            showSuccessDialogAndFinish();
        }
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
