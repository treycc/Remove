package com.jdp.hls.model.entiy;

import com.google.gson.Gson;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.ResetLoginStatusEvent;
import com.jdp.hls.rx.RxRe;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.observers.DefaultObserver;

/**
 * Description：TODO
 * Create Time：2016/10/12 15:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class ResultObserver<T> extends DefaultObserver<HttpResult<T>> {
    private static final String TAG = "ResultObserver";
    protected BaseView baseView;

    public ResultObserver(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoading();
        RxRe.getInstance().add(baseView, this);
    }

    protected void showLoading() {
        baseView.showLoading();
    }

    protected void hideLoading() {
        baseView.hideLoading();
    }

    protected void showError() {
        baseView.hideLoading();
    }

    @Override
    public void onNext(HttpResult<T> httpResult) {
        Logger.json(new Gson().toJson(httpResult));
        hideLoading();
        if (httpResult.getCode() == Status.ResultCode.SUCCESS) {
            onSuccess(httpResult.getData());
        } else if (httpResult.getCode() == Status.ResultCode.ERROR_SERVER) {
            onServerError(httpResult.getCode(), httpResult.getMessage());
        } else if (httpResult.getCode() == Status.ResultCode.ERROR_LOGIN_FAIL) {
            onLoginFail();
        } else {
            onError(httpResult.getCode(), httpResult.getMessage());
        }
    }

    protected abstract void onSuccess(T t);

    protected void onError(int code, String message) {
        ToastUtil.showText(message);
    }

    protected void onServerError(int code, String message) {

    }

    protected void onLoginFail() {
        ToastUtil.showText("用户未登录或登录失效，请重新登录");
        SpSir.getInstance().clearData();
        EventBus.getDefault().post(new ResetLoginStatusEvent());
    }

    @Override
    public void onError(Throwable e) {
        //记录错误
        LogUtil.e(TAG, "onError: " + e.toString());
        showError();
    }

    @Override
    public void onComplete() {
        LogUtil.e(TAG, "onComplete: ");
    }

    public void cancleRequest() {
        LogUtil.e(TAG, "cancleRequest: ");
        cancel();
    }
}
