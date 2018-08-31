package com.jdp.hls.page.setting;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jdp.hls.R;
import com.jdp.hls.activity.AboutUsActivity;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.login.LoginActivity;
import com.jdp.hls.page.modifyPassword.ModifyPasswordActivity;
import com.jdp.hls.page.suggest.SuggestActivity;
import com.jdp.hls.util.AppManager;
import com.jdp.hls.util.DataCleanManager;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.ToastUtil;
import com.kingja.supershapeview.view.SuperShapeTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/30 0030 上午 10:26
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SettingActivity extends BaseTitleActivity implements SettingContract.View {
    @BindView(R.id.switch_setting_msg)
    Switch switchSettingMsg;
    @BindView(R.id.rl_setting_modifypwd)
    RelativeLayout rlSettingModifypwd;
    @BindView(R.id.rl_setting_suggest)
    RelativeLayout rlSettingSuggest;
    @BindView(R.id.rl_setting_aboutus)
    RelativeLayout rlSettingAboutus;
    @BindView(R.id.tv_setting_cache)
    TextView tvSettingCache;
    @BindView(R.id.rl_setting_clearCache)
    RelativeLayout rlSettingClearCache;
    @BindView(R.id.stv_setting_quit)
    SuperShapeTextView stvSettingQuit;
    @Inject
    SettingPresenter settingPresenter;

    @OnClick({R.id.rl_setting_modifypwd, R.id.rl_setting_suggest, R.id.rl_setting_aboutus, R.id
            .rl_setting_clearCache, R.id.stv_setting_quit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_setting_modifypwd:
                GoUtil.goActivity(this, ModifyPasswordActivity.class);
                break;
            case R.id.rl_setting_suggest:
                GoUtil.goActivity(this, SuggestActivity.class);
                break;
            case R.id.rl_setting_aboutus:
                GoUtil.goActivity(this, AboutUsActivity.class);
                break;
            case R.id.rl_setting_clearCache:
                ToastUtil.showText("清理成功");
                DataCleanManager.clearAppCache(this);
                tvSettingCache.setText(DataCleanManager.getCacheSize(getCacheDir()));
                break;
            case R.id.stv_setting_quit:
                checkQuit();
                break;

            default:
                break;
        }
    }

    private void checkQuit() {
        settingPresenter.logout();
        AppManager.getAppManager().finishAllActivity();
        GoUtil.goActivity(SettingActivity.this, LoginActivity.class);

    }

    @Override
    public void initVariable() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
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
        return "设置";
    }

    @Override
    protected void initView() {
        settingPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        tvSettingCache.setText(DataCleanManager.getCacheSize(getCacheDir()));
    }

    @Override
    protected void initNet() {

    }

    @Override
    public void onLogoutSuccess() {

    }
}
