package com.jdp.hls.base;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BaseView {
    public default void showLoading() {
    }

    public default void hideLoading() {
    }

    public default boolean ifShowCusLoading() {
        return false;
    }

    public default void showCusLoading() {
    }

    public default void hideCusLoading() {
    }

    public default void showError() {
    }

    public default void showLoadingCallback() {
    }

    public default void showEmptyCallback() {
    }

    public default void showErrorCallback() {
    }


    public default void showSuccessCallback() {
    }

    public default void showErrorMessage(int code, String message) {

    }

    public default boolean ifRegisterLoadSir() {
        return false;
    }
}
