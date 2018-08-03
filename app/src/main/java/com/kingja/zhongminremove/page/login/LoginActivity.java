package com.kingja.zhongminremove.page.login;

import android.view.View;

import com.kingja.supershapeview.view.SuperShapeEditText;
import com.kingja.supershapeview.view.SuperShapeTextView;
import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.base.BaseTitleActivity;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.util.CheckUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
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

    @OnClick({R.id.stv_login_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_login_confirm:
                String username = setLoginAccountName.getText().toString().trim();
                String password = setLoginPassword.getText().toString().trim();
                if (CheckUtil.checkEmpty(username, "请输入用户名") && CheckUtil.checkEmpty(password, "请输入密码")) {
                    mLoginPresenter.login(username, password, 0);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerLoginCompnent.builder()
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
