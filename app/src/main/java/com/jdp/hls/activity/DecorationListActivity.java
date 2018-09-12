package com.jdp.hls.activity;

import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;

/**
 * Description:装修明细表|附属物及其它构筑物明细表
 * Create Time:2018/9/12 0012 上午 9:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DecorationListActivity extends BaseTitleActivity{
    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_decoration_list;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "内装饰装修明细表";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setRightClick("增加", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                ToastUtil.showText("增加");
            }
        });

    }

    @Override
    protected void initNet() {

    }
}
