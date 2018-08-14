package com.jdp.hls.page.login;

import android.Manifest;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Login;
import com.jdp.hls.model.entiy.Project;
import com.jdp.hls.model.entiy.UserInfo;
import com.jdp.hls.page.projects.ProjectListActivity;
import com.jdp.hls.util.AesUtil;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.EncryptUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SpSir;
import com.kingja.supershapeview.view.SuperShapeEditText;
import com.kingja.supershapeview.view.SuperShapeTextView;
import com.orhanobut.logger.Logger;
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


    @OnClick({R.id.stv_login_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_login_confirm:
                String username = setLoginAccountName.getText().toString().trim();
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
                        Manifest.permission.ACCESS_FINE_LOCATION)
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
            LogUtil.e("取出加密：", SpSir.getInstance().getComeOnBaby());
            LogUtil.e("解码后：", AesUtil.decode(SpSir.getInstance().getComeOnBaby()));
            setLoginPassword.setText(AesUtil.decode(SpSir.getInstance().getComeOnBaby()));
            setLoginAccountName.setText(String.valueOf(SpSir.getInstance().getAccountName()));
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
        if (cbRememberPasswrod.isChecked()) {
            SpSir.getInstance().setIfRememberBaby(true);
            LogUtil.e("加密前：",password);
            LogUtil.e("加密后1：", AesUtil.encrypt(password));
            LogUtil.e("加密后2：", AesUtil.encrypt(password));
            SpSir.getInstance().setComeOnBaby(AesUtil.encrypt(password));
        } else {
            SpSir.getInstance().setIfRememberBaby(false);
            SpSir.getInstance().setComeOnBaby("");
        }
        LogUtil.e(TAG,"token:"+account.getToken());
        saveUserInfo(account);
        List<Project> projects = account.getProjects();
        if (projects != null && projects.size() > 0) {
            if (projects.size() > 1) {
                ProjectListActivity.goActivity(this, projects);
            } else {
                LogUtil.e(TAG, "直接跳转");
            }
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
    }

    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

}
