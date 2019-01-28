package com.jdp.hls.page.rosterdetail.contacts.detail;

import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.NoDoubleClickListener;

/**
 * Description:TODO
 * Create Time:2019/1/28 0028 下午 2:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsDetailActivity extends BaseTitleActivity{
    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_contacts_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "人员信息";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                saveData();
            }
        });

    }

    private void saveData() {

    }

    @Override
    public void initNet() {

    }
}
