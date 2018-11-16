package com.jdp.hls.page.employee.detail;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
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
    private String employeeId;

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

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                String password = etPassword.getText().toString().trim();
                String realName = etRealName.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                if (CheckUtil.checkEmpty(realName, "请输入姓名") && CheckUtil.checkEmpty(mobile, "请输入手机号")) {
                    employeeDetailPresenter.modifyEmployee(new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("EmployeeId", employeeId)
                            .addFormDataPart("Password", TextUtils.isEmpty(password) ? "" : EncryptUtil.getDoubleMd5
                                    (password))
                            .addFormDataPart("RealName", realName)
                            .addFormDataPart("Mobile", mobile)
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
        showSuccessToastAndFinish();
    }

    @Override
    public void onGetEmployeeDetailSuccess(Employee employee) {
        if (employee != null) {
            tvLoginName.setString(employee.getLoginName());
            etRealName.setString(employee.getRealName());
            etMobile.setString(employee.getMobilePhone());
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
}
