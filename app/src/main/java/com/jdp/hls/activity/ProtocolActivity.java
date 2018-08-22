package com.jdp.hls.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.SpSir;

import butterknife.BindView;

/**
 * Description:协议
 * Create Time:2018/8/16 0016 上午 11:15
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProtocolActivity extends BaseTitleActivity {
    @BindView(R.id.wb_protocol)
    WebView wbProtocol;
    @BindView(R.id.pb_protocol)
    ProgressBar pbProtocol;

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_protocol;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "协议";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        wbProtocol.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        wbProtocol.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pbProtocol.setVisibility(View.GONE);
                }

            }
        });
        wbProtocol.loadUrl(SpSir.getInstance().getServerName() + SpSir.getInstance().getProtocolUrl());
    }

    @Override
    protected void initNet() {

    }

}
