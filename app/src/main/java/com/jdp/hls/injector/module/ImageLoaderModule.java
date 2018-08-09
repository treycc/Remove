package com.jdp.hls.injector.module;


import com.jdp.hls.imgaeloader.GlideLoader;
import com.jdp.hls.imgaeloader.IImageLoader;

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
