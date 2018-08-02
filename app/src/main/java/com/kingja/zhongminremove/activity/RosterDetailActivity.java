package com.kingja.zhongminremove.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.base.BaseTitleActivity;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.model.entiy.Roster;
import com.kingja.zhongminremove.util.NoDoubleClickListener;

/**
 * Description:TODO
 * Create Time:2018/7/30 0030 上午 10:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterDetailActivity extends BaseTitleActivity {
    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_roster_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "详情";
    }

    @Override
    protected void initView() {

    }

    private NoDoubleClickListener noDoubleClickListener=new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {

        }
    };

    @Override
    protected void initData() {
//        setRightClick("保存", noDoubleClickListener );
    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Context context, Roster roster) {
        Intent intent = new Intent(context, RosterDetailActivity.class);
        intent.putExtra("roster", roster);
        context.startActivity(intent);
    }
}
