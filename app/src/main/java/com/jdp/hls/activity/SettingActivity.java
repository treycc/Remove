package com.jdp.hls.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.login.LoginActivity;
import com.jdp.hls.page.modifyPassword.ModifyPasswordActivity;
import com.jdp.hls.page.suggest.SuggestActivity;
import com.jdp.hls.util.AppManager;
import com.jdp.hls.util.DataCleanManager;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.ToastUtil;
import com.kingja.supershapeview.view.SuperShapeTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/30 0030 上午 10:26
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SettingActivity extends BaseTitleActivity {
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
                ToastUtil.showText("清理缓存");
                DataCleanManager.cleanInternalCache(this);
                tvSettingCache.setText(DataCleanManager.getCacheSize(getCacheDir()));
                break;
            case R.id.stv_setting_quit:
                ToastUtil.showText("退出");
                AppManager.getAppManager().finishAllActivity();
                GoUtil.goActivity(this, LoginActivity.class);
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
        return R.layout.activity_setting;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "设置";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        tvSettingCache.setText(DataCleanManager.getCacheSize(getCacheDir()));
    }

    @Override
    protected void initNet() {

    }

}
