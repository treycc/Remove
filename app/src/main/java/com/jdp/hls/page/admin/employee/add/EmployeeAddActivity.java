package com.jdp.hls.page.admin.employee.add;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddEmployeeEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.page.admin.employee.add.projectlist.SuperviseProjectListActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.EncryptUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import org.greenrobot.eventbus.EventBus;

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
public class EmployeeAddActivity extends BaseTitleActivity implements EmployeeAddContract.View {
    @BindView(R.id.tv_loginName)
    StringTextView tvLoginName;
    @BindView(R.id.et_password)
    EnableEditText etPassword;
    @BindView(R.id.et_realName)
    EnableEditText etRealName;
    @BindView(R.id.et_mobile)
    EnableEditText etMobile;
    @Inject
    EmployeeAddPresenter employeeAddPresenter;
    @BindView(R.id.tv_projectSelector)
    StringTextView tvProjectSelector;
    @BindView(R.id.ll_projectSelector)
    LinearLayout llProjectSelector;
    @BindView(R.id.smb_accountStatus)
    SwitchMultiButton smbAccountStatus;
    private String projectIDs = "";

    @OnClick({R.id.ll_projectSelector})
    public void rl_protocol_otherArea(View view) {
        switch (view.getId()) {
            case R.id.ll_projectSelector:
                SuperviseProjectListActivity.goActivity(this, projectIDs,isManageAllProjects);
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_employee_add;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        employeeAddPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "增加";
    }

    @Override
    protected void initView() {

    }

    private boolean isStop;
    private boolean isManageAllProjects;

    @Override
    protected void initData() {
        smbAccountStatus.setOnSwitchListener((position, tabText) -> isStop = position != 0);

        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                String password = etPassword.getText().toString().trim();
                String realName = etRealName.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                if (CheckUtil.checkEmpty(password, "请输入密码") && CheckUtil.checkEmpty(realName, "请输入姓名") && CheckUtil
                        .checkPhoneFormat(mobile)) {
                    employeeAddPresenter.addEmployee(new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("Password", EncryptUtil.getDoubleMd5(password))
                            .addFormDataPart("RealName", realName)
                            .addFormDataPart("Mobile", mobile)
                            .addFormDataPart("ProjectIDs", TextUtils.isEmpty(projectIDs)?"": projectIDs)
                            .addFormDataPart("IsStop", String.valueOf(isStop))
                            .addFormDataPart("IsManageAllProjects", String.valueOf(isManageAllProjects))
                            .build());
                }
            }
        });
    }

    @Override
    public void initNet() {

    }

    @Override
    public void onAddEmployeeSuccess(Employee employee) {
        EventBus.getDefault().post(new AddEmployeeEvent(employee));
        showSuccessDialogAndFinish();
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
}
