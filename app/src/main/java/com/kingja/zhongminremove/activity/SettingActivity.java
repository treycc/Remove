package com.kingja.zhongminremove.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.supershapeview.view.SuperShapeTextView;
import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.base.BaseTitleActivity;
import com.kingja.zhongminremove.constant.Constants;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.util.GoUtil;
import com.kingja.zhongminremove.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/30 0030 上午 10:26
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SettingActivity extends BaseTitleActivity {
    @BindView(R.id.cb_setting_jpush)
    CheckBox cbSettingJpush;
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
                GoUtil.goActivity(this, ModifyPwdActivity.class);
                break;
            case R.id.rl_setting_suggest:
                GoUtil.goActivity(this, SuggestActivity.class);
                break;
            case R.id.rl_setting_aboutus:
                GoUtil.goActivity(this, AboutUsActivity.class);
                break;
            case R.id.rl_setting_clearCache:
                ToastUtil.showText("清理缓存");
                break;
            case R.id.stv_setting_quit:
                ToastUtil.showText("退出");
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

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}