package com.jdp.hls.page.node.protocol;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.gson.Gson;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.TaoType;
import com.jdp.hls.util.SimpleTextWatcher;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/12/18 0018 上午 9:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BasePayFragment extends BaseFragment {

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initNet() {

    }

    protected abstract MultipartBody.Builder getRequestBuilder();
    protected TextWatcher calculateWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            calculate();
        }
    };

    protected void calculate() {

    }

    protected String getTaoTypeJson(List<TaoType> taoTypeList) {
        if (taoTypeList != null && taoTypeList.size() > 0) {
            return new Gson().toJson(taoTypeList);
        }
        return "";
    }

    protected int getTaoTypeCount(List<TaoType> taoTypeList) {
        int count = 0;
        for (TaoType taoType : taoTypeList) {
            count += taoType.getQuantity();
        }
        return count;
    }

}
