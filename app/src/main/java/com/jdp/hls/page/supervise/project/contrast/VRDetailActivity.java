package com.jdp.hls.page.supervise.project.contrast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.SpSir;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:TODO
 * Create Time:2018/12/12 0012 上午 10:03
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class VRDetailActivity extends BaseTitleActivity {
    @BindView(R.id.wb)
    WebView wb;
    @BindView(R.id.pb)
    ProgressBar pb;
    private String vrUrl = "http://m.detu.com/zh/pano/show/519372";
    private String title;

    @Override
    public void initVariable() {
        vrUrl = getIntent().getStringExtra(Constants.Extra.VrUrl);
        title = getIntent().getStringExtra(Constants.Extra.TITLE);

    }

    @Override
    protected int getContentView() {
        return R.layout.layout_wb;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return title;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        wb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pb.setVisibility(View.GONE);
                }

            }
        });
        wb.loadUrl("http://m.detu.com/zh/pano/show/519372");
    }

    @Override
    public void initNet() {

    }

    public static void goActivity(Context context, String vrUrl, String title) {
        Intent intent = new Intent(context, VRDetailActivity.class);
        intent.putExtra(Constants.Extra.VrUrl, vrUrl);
        intent.putExtra(Constants.Extra.TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (wb != null) {
            wb.destroy();
            wb = null;
        }
        super.onDestroy();
    }
}
