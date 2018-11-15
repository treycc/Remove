package com.jdp.hls.page.employee.add;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.event.AddEmployeeEvent;
import com.jdp.hls.event.ModifyEmployeeEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.EncryptUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                String password = etPassword.getText().toString().trim();
                String realName = etRealName.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                if (CheckUtil.checkEmpty(password, "请输入密码") && CheckUtil.checkEmpty(realName, "请输入姓名") && CheckUtil
                        .checkEmpty(mobile, "请输入手机号")) {
                    employeeAddPresenter.addEmployee(new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("Password", EncryptUtil.getDoubleMd5(password))
                            .addFormDataPart("RealName", realName)
                            .addFormDataPart("Mobile", mobile)
                            .build());
                }
            }
        });
    }

    @Override
    protected void initNet() {

    }

    @Override
    public void onAddEmployeeSuccess(Employee employee) {
        EventBus.getDefault().post(new AddEmployeeEvent(employee));
        showSuccessToastAndFinish();
    }
}
