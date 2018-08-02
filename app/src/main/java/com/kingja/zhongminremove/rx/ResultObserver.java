package com.kingja.zhongminremove.rx;

import android.util.Log;

import com.google.gson.Gson;
import com.kingja.zhongminremove.base.BaseView;
import com.kingja.zhongminremove.model.HttpResult;
import com.kingja.zhongminremove.util.ToastUtil;
import com.orhanobut.logger.Logger;

import io.reactivex.observers.DefaultObserver;

/**
 * Description：TODO
 * Create Time：2016/10/12 15:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class ResultObserver<T> extends DefaultObserver<HttpResult<T>> {
    private static final String TAG = "ResultObserver";
    private BaseView baseView;

    public ResultObserver(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        baseView.showLoading();
        RxRe.getInstance().add(baseView, this);
        Log.e(TAG, "onStart: ");
    }

    @Override
    public void onNext(HttpResult<T> httpResult) {
        Logger.json(new Gson().toJson(httpResult));
        baseView.hideLoading();
        if (httpResult.getCode() == 0) {
            onSuccess(httpResult.getData());
        } else if (httpResult.getCode() == 1) {
            ToastUtil.showText("系统错误，请联系客服");
        } else if (httpResult.getCode() == -1) {
            ToastUtil.showText("登录失效");
        } else {
            ToastUtil.showText(httpResult.getMessage());
        }
    }

    protected abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {
        //记录错误
        Log.e(TAG, "onError: " + e.toString());
        baseView.hideLoading();
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: ");
    }

    public void cancleRequest() {
        Log.e(TAG, "cancleRequest: ");
        cancel();
    }
}
