package com.jdp.hls.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.VersionUtil;
import com.kingja.supershapeview.view.SuperShapeImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/30 0030 上午 10:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AboutUsActivity extends BaseTitleActivity {
    @BindView(R.id.siv_aboutus_logo)
    SuperShapeImageView sivAboutusLogo;
    @BindView(R.id.tv_aboutus_version)
    TextView tvAboutusVersion;
    @BindView(R.id.rl_aboutus_agreement)
    RelativeLayout rlAboutusAgreement;

    @OnClick({R.id.rl_aboutus_agreement})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_aboutus_agreement:
                GoUtil.goActivity(this,ProtocolActivity.class);
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
        return R.layout.activity_aboutus;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "联系我们";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        tvAboutusVersion.setText(String.format("当前版本:%s",VersionUtil.getVerName(this)));
    }

    @Override
    public void initNet() {

    }

}
