package com.kingja.zhongminremove.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.kingja.loadsir.core.LoadSir;
import com.kingja.zhongminremove.constant.Constants;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.injector.component.DaggerAppComponent;
import com.kingja.zhongminremove.injector.module.ApiModule;
import com.kingja.zhongminremove.injector.module.AppModule;
import com.kingja.zhongminremove.injector.module.SpModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;


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

    @Override
    public void onCreate() {
        super.onCreate();
        initLoadSir();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        Logger.addLogAdapter(new AndroidLogAdapter());
        LeakCanary.install(this);
        sInstance = this;
        mSharedPreferences = getSharedPreferences(Constants.APPLICATION_NAME, MODE_PRIVATE);
        setupComponent();
    }

    public static SharedPreferences getSp() {
        return mSharedPreferences;
    }
    private void initLoadSir() {
        LoadSir.beginBuilder()
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