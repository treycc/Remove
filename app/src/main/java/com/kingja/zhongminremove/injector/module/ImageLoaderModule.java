package com.kingja.zhongminremove.injector.module;



import com.kingja.zhongminremove.imgaeloader.GlideLoader;
import com.kingja.zhongminremove.imgaeloader.IImageLoader;

import dagger.Module;
import dagger.Provides;

/**
 * Description：TODO
 * Create Time：2017/3/9 11:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Module
public class ImageLoaderModule {
    @Provides
    public IImageLoader provideImageLoader() {
        return new GlideLoader();
    }
}
