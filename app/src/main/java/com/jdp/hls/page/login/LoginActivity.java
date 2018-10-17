package com.jdp.hls.page.login;

import android.Manifest;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.jdp.hls.R;
import com.jdp.hls.page.home.HomeActivity;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Login;
import com.jdp.hls.model.entiy.Project;
import com.jdp.hls.model.entiy.UserInfo;
import com.jdp.hls.page.projects.ProjectListActivity;
import com.jdp.hls.service.initialize.InitializeService;
import com.jdp.hls.util.AesUtil;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.EncryptUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.kingja.supershapeview.view.SuperShapeEditText;
import com.kingja.supershapeview.view.SuperShapeTextView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Description:登录界面
 * Create Time:2018/7/26 0026 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoginActivity extends BaseTitleActivity implements LoginContract.View {

    @BindView(R.id.set_login_accountName)
    SuperShapeEditText setLoginAccountName;
    @BindView(R.id.set_login_password)
    SuperShapeEditText setLoginPassword;
    @BindView(R.id.stv_login_confirm)
    SuperShapeTextView stvLoginConfirm;
    @Inject
    LoginPresenter mLoginPresenter;
    @BindView(R.id.cb_remember_passwrod)
    CheckBox cbRememberPasswrod;
    private String password;
    private String username;


    @OnClick({R.id.stv_login_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_login_confirm:
                username = setLoginAccountName.getText().toString().trim();
                password = setLoginPassword.getText().toString().trim();
                if (CheckUtil.checkEmpty(username, "请输入用户名") && CheckUtil.checkEmpty(password, "请输入密码")) {
                    mLoginPresenter.login(username, EncryptUtil.getDoubleMd5(password), 0);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {
        checkPermissions();
    }

    public void checkPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        Disposable disposable = rxPermission
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.d(TAG, permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.d(TAG, permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.d(TAG, permission.name + " is denied.");
                        }
                    }
                });
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
        return "";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mLoginPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        boolean ifRememberBaby = SpSir.getInstance().getIfRememberBaby();
        if (ifRememberBaby) {
            cbRememberPasswrod.setChecked(true);
            setLoginPassword.setText(AesUtil.decode(SpSir.getInstance().getComeOnBaby()));
            setLoginAccountName.setText(String.valueOf(SpSir.getInstance().getUserName()));
        }
    }

    @Override
    protected void initNet() {

    }


    @Override
    protected boolean ifHideTitle() {
        return true;
    }

    @Override
    public void onLoginSuccess(Login account) {
        saveUserInfo(account);
        startService(new Intent(this, InitializeService.class));
        if (cbRememberPasswrod.isChecked()) {
            SpSir.getInstance().setIfRememberBaby(true);
            SpSir.getInstance().setComeOnBaby(AesUtil.encrypt(password));
            SpSir.getInstance().setUserName(username);
        } else {
            SpSir.getInstance().setIfRememberBaby(false);
            SpSir.getInstance().setComeOnBaby("");
        }
        LogUtil.e(TAG, "token:" + account.getToken());
        List<Project> projects = account.getProjects();
        if (projects != null && projects.size() > 0) {
            if (projects.size() > 1) {
                ProjectListActivity.goActivity(this, projects);
            } else {
                Project project = projects.get(0);
                SpSir.getInstance().setProjectId(project.getProjectId());
                SpSir.getInstance().setProjectName(project.getProjectName());
                GoUtil.goActivityAndFinish(this, HomeActivity.class);
            }
        } else {
            ToastUtil.showText("您的账号下没有项目");
        }
    }

    private void saveUserInfo(Login account) {
        SpSir.getInstance().setToken(account.getToken());
        UserInfo userInfo = account.getUserInfo();
        SpSir.getInstance().setEmployeeId(userInfo.getEmployeeId());
        SpSir.getInstance().setCompanyId(userInfo.getCompanyId());
        SpSir.getInstance().setCompanyName(userInfo.getCompanyName());
        SpSir.getInstance().setRealName(userInfo.getRealName());
        SpSir.getInstance().setHeadUrl(userInfo.getHeadUrl());
        SpSir.getInstance().setMobilePhone(userInfo.getMobilePhone());
        SpSir.getInstance().setAccountName(userInfo.getAccountName());
        SpSir.getInstance().setAccountAlias(userInfo.getAccountAlias());
        SpSir.getInstance().setServerName(account.getServerName());
        SpSir.getInstance().setProtocolUrl(account.getProtocolUrl());
        SpSir.getInstance().setAccountType(userInfo.getAccountType());
    }

}
