package com.jdp.hls.base;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BaseView {
    public default void showLoading(){}

    public default  void hideLoading(){}

    public default void showEmpty(){}

    public default void showError(){}

    public default void showNetError(){}

    public default void showSuccess(){}
}
