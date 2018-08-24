package com.jdp.hls.activity;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.login.LoginActivity;
import com.jdp.hls.util.GoUtil;

/**
 * Description:启动页
 * Create Time:2018/8/23 0023 上午 10:26
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SplashActivity  extends BaseActivity{
    private int DELAY_MILLIS = 2000;
    private Handler dispatchHander;
    private DispatcherRunnable dispatcherRunnable;
    @Override
    public void initVariable() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public View getContentId() {
        return View.inflate(this,R.layout.activity_splash,null);
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        dispatchHander = new Handler();
        dispatcherRunnable = new DispatcherRunnable();
        dispatchHander.postDelayed(dispatcherRunnable, DELAY_MILLIS);
    }

    @Override
    protected void initNet() {

    }
    class DispatcherRunnable implements Runnable {

        @Override
        public void run() {
            GoUtil.goActivityAndFinish(SplashActivity.this, LoginActivity.class);
        }
    }

    @Override
    protected void onDestroy() {
        dispatchHander.removeCallbacks(dispatcherRunnable);
        super.onDestroy();
    }
}
