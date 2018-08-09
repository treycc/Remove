package com.jdp.hls.injector.component;


import android.app.Application;

import com.jdp.hls.imgaeloader.IImageLoader;
import com.jdp.hls.injector.module.ApiModule;
import com.jdp.hls.injector.module.AppModule;
import com.jdp.hls.injector.module.ImageLoaderModule;
import com.jdp.hls.injector.module.SpModule;
import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.util.SpManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 9:42
 * 修改备注：
 */
@Singleton
@Component(modules = {ApiModule.class, AppModule.class, SpModule.class,ImageLoaderModule.class})
public interface AppComponent {
    UserApi getApi();
    SpManager getSpManager();
    Application getApplication();
    IImageLoader getImageLoader();
}
