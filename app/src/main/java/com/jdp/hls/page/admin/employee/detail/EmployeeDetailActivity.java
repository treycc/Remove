package com.jdp.hls.page.admin.employee.detail;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddEmployeeEvent;
import com.jdp.hls.event.ModifyEmployeeEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AreaDto;
import com.jdp.hls.model.entiy.AreaSupervise;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.page.admin.employee.add.EmployeeAddContract;
import com.jdp.hls.page.admin.employee.add.EmployeeAddPresenter;
import com.jdp.hls.page.admin.employee.add.projectlist.SuperviseProjectListActivity;
import com.jdp.hls.page.admin.employee.areasupervise.list.AreaSuperviseListActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.EncryptUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import lib.kingja.switchbutton.SwitchMultiButton;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/11/15 0015 下午 4:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EmployeeDetailActivity extends BaseTitleActivity implements EmployeeDetailContract.View,
        EmployeeAddContract.View {
    @BindView(R.id.tv_loginName)
    StringTextView tvLoginName;
    @BindView(R.id.et_password)
    EnableEditText etPassword;
    @BindView(R.id.et_realName)
    EnableEditText etRealName;
    @BindView(R.id.et_mobile)
    EnableEditText etMobile;
    @Inject
    EmployeeDetailPresenter employeeDetailPresenter;
    @Inject
    EmployeeAddPresenter employeeAddPresenter;
    @BindView(R.id.smb_accountStatus)
    SwitchMultiButton smbAccountStatus;
    @BindView(R.id.tv_projectSelector)
    StringTextView tvProjectSelector;
    @BindView(R.id.ll_projectSelector)
    LinearLayout llProjectSelector;
    @BindView(R.id.tv_areaSelector)
    StringTextView tvAreaSelector;
    @BindView(R.id.ll_areaSelector)
    LinearLayout llAreaSelector;
    private int employeeId;
    private List<AreaSupervise> areaSuperviseList;
    private boolean isStop;
    private boolean isManageAllProjects;
    private String projectIDs = "";
    private boolean isAdd;
    private String realName;
    private String mobile;
    private String password;

    @OnClick({R.id.ll_projectSelector, R.id.ll_areaSelector})
    public void rl_protocol_otherArea(View view) {
        switch (view.getId()) {
            case R.id.ll_projectSelector:
                SuperviseProjectListActivity.goActivity(this, projectIDs, isManageAllProjects);
                break;
            case R.id.ll_areaSelector:
                AreaSuperviseListActivity.goActivity(this, areaSuperviseList);
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {
        employeeId = getIntent().getIntExtra(Constants.Extra.EmployeeId, 0);
        isAdd = (employeeId == 0);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_employee_modify;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        employeeDetailPresenter.attachView(this);
        employeeAddPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        /*监管项目可见性*/
        llProjectSelector.setVisibility(SpSir.getInstance().isAllowDistributeProjects() ? View.VISIBLE : View.GONE);
        smbAccountStatus.setOnSwitchListener((position, tabText) -> isStop = position != 0);
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                password = etPassword.getText().toString().trim();
                realName = etRealName.getText().toString().trim();
                mobile = etMobile.getText().toString().trim();
                if (CheckUtil.checkEmpty(realName, "请输入姓名") && CheckUtil.checkPhoneFormat(mobile)) {
                    if (isAdd) {
                        employeeAddPresenter.addEmployee(getRequestBody());
                    } else {
                        employeeDetailPresenter.modifyEmployee(getRequestBody());
                    }
                }
            }
        });
    }

    protected String getListJson(List<AreaSupervise> list) {
        if (list != null && list.size() > 0) {
            List<AreaDto> areaDtoList = new ArrayList<>();
            for (AreaSupervise areaSupervise : list) {
                areaDtoList.add(new AreaDto(areaSupervise.getLevel(), areaSupervise.getRegionId()));
            }
            return new Gson().toJson(areaDtoList);
        }
        return "[]";
    }

    public RequestBody getRequestBody() {
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EmployeeId", String.valueOf(employeeId))
                .addFormDataPart("Password", TextUtils.isEmpty(password) ? "" : EncryptUtil.getDoubleMd5
                        (password))
                .addFormDataPart("RealName", realName)
                .addFormDataPart("Mobile", mobile)
                .addFormDataPart("IsStop", String.valueOf(isStop))
                .addFormDataPart("AreaList", getListJson(areaSuperviseList))
                .addFormDataPart("IsManageAllProjects", String.valueOf(isManageAllProjects))
                .addFormDataPart("ProjectIDs", TextUtils.isEmpty(projectIDs) ? "" : projectIDs)
                .build();
    }

    @Override
    public void initNet() {
        employeeDetailPresenter.getEmployeeDetail(employeeId);
    }

    @Override
    public void onModifyEmployeeSuccess(Employee employee) {
        EventBus.getDefault().post(new ModifyEmployeeEvent(employee));
        showSuccessDialogAndFinish();
    }

    @Override
    public void onAddEmployeeSuccess(Employee employee) {
        EventBus.getDefault().post(new AddEmployeeEvent(employee));
        showSuccessDialogAndFinish();
    }

    @Override
    public void onGetEmployeeDetailSuccess(Employee employee) {
        if (isAdd) {
            if (employee.isAreaVisible()) {
                llAreaSelector.setVisibility(View.VISIBLE);
            }
        } else {
            etPassword.setHint("不修改请留空");
            tvLoginName.setString(employee.getLoginName());
            etRealName.setString(employee.getRealName());
            etMobile.setString(employee.getMobilePhone());
            isStop = employee.isStop();
            isManageAllProjects = employee.isManageAllProjects();
            projectIDs = employee.getProjectIDs();
            /*设置区域权限*/
            if (employee.isAreaVisible()) {
                llAreaSelector.setVisibility(View.VISIBLE);
                areaSuperviseList = employee.getAreaList();
                tvAreaSelector.setHint((areaSuperviseList == null || areaSuperviseList.size() == 0) ? "请选择监管区域" :
                        String.format("已选择%d个区域", areaSuperviseList.size()));
            }
            if (isManageAllProjects) {
                tvProjectSelector.setHint("已选择所有项目");
            } else {
                String[] ids = projectIDs.split("#");
                if (!TextUtils.isEmpty(projectIDs) && ids.length > 0) {
                    tvProjectSelector.setHint(String.format("已选择%d个项目", ids.length));
                }
            }
            smbAccountStatus.setSelectedTab(isStop ? 1 : 0);
        }

    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    public static void goActivity(Context context, int employeeId) {
        Intent intent = new Intent(context, EmployeeDetailActivity.class);
        intent.putExtra(Constants.Extra.EmployeeId, employeeId);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.SuperviseProjectList:
                    projectIDs = data.getStringExtra(Constants.Extra.Ids);
                    isManageAllProjects = data.getBooleanExtra(Constants.Extra.IsManageAllProjects, false);
                    if (isManageAllProjects) {
                        tvProjectSelector.setHint("已选择全部项目");
                    } else {
                        String[] selectProjectIds = projectIDs.split("#");
                        if (!TextUtils.isEmpty(projectIDs)&&selectProjectIds.length > 0) {
                            tvProjectSelector.setHint(String.format("已选择%d个项目", selectProjectIds.length));
                            LogUtil.e(TAG, "projectIDs:" + projectIDs);
                        }else{
                            tvProjectSelector.setHint("请选择监管项目");
                        }
                    }
                    break;
                case Constants.RequestCode.AreaSuperviseList:
                    areaSuperviseList = (List<AreaSupervise>) data.getSerializableExtra(Constants.Extra
                            .AreaSuperviseList);
                    tvAreaSelector.setHint((areaSuperviseList == null || areaSuperviseList.size() == 0) ? "请选择监管区域" :
                            String.format("已选择%d个区域", areaSuperviseList.size()));
                    break;
            }
        }
    }


}
