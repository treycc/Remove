package com.jdp.hls.page.familyrelation;

import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;

/**
 * Description:增加成员
 * Create Time:2018/9/14 0014 下午 4:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FamilyRememberActivity extends BaseTitleActivity{
    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_family_member;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "增加成员";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                ToastUtil.showText("保存");
            }
        });

    }

    @Override
    public void initNet() {

    }
}
