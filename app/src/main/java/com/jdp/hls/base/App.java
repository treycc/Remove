package com.jdp.hls.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.jdp.hls.callback.EmptyCallback;
import com.jdp.hls.callback.ErrorCallback;
import com.jdp.hls.callback.ErrorMessageCallback;
import com.jdp.hls.callback.LoadingCallback;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.injector.component.DaggerAppComponent;
import com.jdp.hls.injector.module.ApiModule;
import com.jdp.hls.injector.module.AppModule;
import com.jdp.hls.injector.module.SpModule;
import com.jdp.hls.page.crash.CrashActivity;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleActivityLifecycleCallbacks;
import com.kingja.loadsir.core.LoadSir;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.eventbus.EventBus;

import cat.ereza.customactivityoncrash.config.CaocConfig;


/**
 * Description：App
 * Create Time：2016/10/14:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class App extends MultiDexApplication {
    private static App sInstance;
    private AppComponent appComponent;
    private static SharedPreferences mSharedPreferences;
    private AppModule appModule;
    private static int activityCount;

    @Override
    public void onCreate() {
        super.onCreate();
        initLoadSir();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        sInstance = this;
        mSharedPreferences = getSharedPreferences(Constants.APPLICATION_NAME, MODE_PRIVATE);
        setupComponent();
        CaocConfig.Builder.create()
                .errorActivity(CrashActivity.class)
                .apply();
        registerActivityLifecycleCallbacks(new SimpleActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activityCount++;
                if (activityCount == 1) {
                    LogUtil.e("App", "回到前台");
                }
//                LogUtil.e("App", "栈深度:" + activityCount);
            }
        });
    }

    public static int getActivityCount() {
        return activityCount;
    }

    public static SharedPreferences getSp() {
        return mSharedPreferences;
    }

    private void initLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new EmptyCallback())
                .addCallback(new ErrorMessageCallback())
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .commit();
    }

    private void setupComponent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .spModule(new SpModule()).build();
        appModule = new AppModule(this);
    }

    public static App getContext() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public AppModule getAppModule() {
        return appModule;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);/*64K说拜拜*/
    }
}
