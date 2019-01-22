package com.jdp.hls.page.admin.employee.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.ModifyEmployeeEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AreaSupervise;
import com.jdp.hls.model.entiy.Employee;
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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lib.kingja.switchbutton.SwitchMultiButton;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/11/15 0015 下午 4:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EmployeeDetailActivity extends BaseTitleActivity implements EmployeeDetailContract.View {
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
    private String employeeId;
    private List<AreaSupervise> areaSuperviseList;

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
        employeeId = getIntent().getStringExtra(Constants.Extra.EmployeeId);
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
    }

    @Override
    protected String getContentTitle() {
        return "";
    }

    @Override
    protected void initView() {

    }

    private boolean isStop;
    private boolean isManageAllProjects;
    private String projectIDs = "";

    @Override
    protected void initData() {
        smbAccountStatus.setOnSwitchListener((position, tabText) -> isStop = position != 0);
        llProjectSelector.setVisibility(SpSir.getInstance().isAllowDistributeProjects() ? View.VISIBLE : View.GONE);
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                String password = etPassword.getText().toString().trim();
                String realName = etRealName.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                if (CheckUtil.checkEmpty(realName, "请输入姓名") && CheckUtil.checkPhoneFormat(mobile)) {
                    employeeDetailPresenter.modifyEmployee(new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("EmployeeId", employeeId)
                            .addFormDataPart("Password", TextUtils.isEmpty(password) ? "" : EncryptUtil.getDoubleMd5
                                    (password))
                            .addFormDataPart("RealName", realName)
                            .addFormDataPart("Mobile", mobile)
                            .addFormDataPart("IsStop", String.valueOf(isStop))
                            .addFormDataPart("IsManageAllProjects", String.valueOf(isManageAllProjects))
                            .addFormDataPart("ProjectIDs", TextUtils.isEmpty(projectIDs) ? "" : projectIDs)
                            .build());
                }
            }
        });
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
    public void onGetEmployeeDetailSuccess(Employee employee) {
        if (employee != null) {
            tvLoginName.setString(employee.getLoginName());
            etRealName.setString(employee.getRealName());
            etMobile.setString(employee.getMobilePhone());
            isStop = employee.isStop();
            isManageAllProjects = employee.isManageAllProjects();
            projectIDs = employee.getProjectIDs();

            /*设置区域权限*/
//            if (employee.isAreaVisible()) {
                llAreaSelector.setVisibility(View.VISIBLE);
                areaSuperviseList = employee.getAreaList();
//            }

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

    public static void goActivity(Context context, String employeeId) {
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
                        if (selectProjectIds.length > 0) {
                            tvProjectSelector.setHint(String.format("已选择%d个项目", selectProjectIds.length));
                            LogUtil.e(TAG, "projectIDs:" + projectIDs);
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
