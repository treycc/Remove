package com.jdp.hls.base;

import android.support.annotation.NonNull;

import com.jdp.hls.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(@NonNull T view);
    void detachView();
}
