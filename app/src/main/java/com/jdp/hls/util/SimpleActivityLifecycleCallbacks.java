package com.jdp.hls.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Description:TODO
 * Create Time:2019/1/15 0015 上午 9:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class SimpleActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    @Override
    public abstract void onActivityCreated(Activity activity, Bundle savedInstanceState);

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}