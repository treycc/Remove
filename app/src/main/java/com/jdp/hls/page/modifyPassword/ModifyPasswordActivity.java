package com.jdp.hls.page.modifyPassword;

import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.kingja.supershapeview.view.SuperShapeEditText;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:修改密码
 * Create Time:2018/7/30 0030 上午 10:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyPasswordActivity extends BaseTitleActivity implements ModifyPasswordContract.View {
    @BindView(R.id.set_oldPassword)
    SuperShapeEditText setOldPassword;
    @BindView(R.id.set_newPassword)
    SuperShapeEditText setNewPassword;
    @BindView(R.id.set_repeatPassword)
    SuperShapeEditText setRepeatPassword;

    @Inject
    ModifyPasswordPresenter modifyPasswordPresenter;

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_modify_pwd;
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
        return "修改密码";
    }

    @Override
    protected void initView() {
        modifyPasswordPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                modifyPassword();
            }
        });

    }

    private void modifyPassword() {
        String oldPassword = setOldPassword.getText().toString().trim();
        String newPassword = setNewPassword.getText().toString().trim();
        String repeatPassword = setRepeatPassword.getText().toString().trim();
        if (CheckUtil.checkEmpty(oldPassword, "请输入旧密码") && CheckUtil.checkEmpty(newPassword, "请输入新密码") && CheckUtil
                .checkEmpty(repeatPassword, "请输入确认密码") && CheckUtil.checkSame(newPassword, repeatPassword,
                "两次输入密码不一致，请重新输入")) {
            modifyPasswordPresenter.modifyPassword(oldPassword, newPassword);
        }

    }

    @Override
    protected void initNet() {

    }

    @Override
    public void onModifyPasswordSuccess() {
        ToastUtil.showText(getString(R.string.success_modify_password));
        finish();
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
