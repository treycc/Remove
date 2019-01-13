package com.jdp.hls.page.server;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.ServerAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ServerInfo;
import com.jdp.hls.page.login.LoginActivity;
import com.jdp.hls.util.AppManager;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2019/1/11 0011 上午 9:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ServerSelectorActivity extends BaseTitleActivity {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.rsrl)
    RefreshSwipeRefreshLayout rsrl;
    private ServerAdapter serverAdapter;

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ServerInfo serverInfo = (ServerInfo) adapterView.getItemAtPosition(position);
        SpSir.getInstance().setDebugUrl(serverInfo.getServerUrl());
        ToastUtil.showText("环境切换成功");
//
//        PendingIntent mPendingIntent = PendingIntent.getActivity(this, 123456, new Intent(this,
//                LoginActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
//        AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
//        {
//            mgr.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 50,mPendingIntent);
//        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            mgr.setExact(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+ 50, mPendingIntent);
//        }else{
//            mgr.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+ 50, mPendingIntent);
//        }
//        AppManager.getAppManager().finishAllActivity();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        },100);


        LogUtil.e(TAG, "切换后:" + SpSir.getInstance().getDebugUrl());
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv_rsl;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "环境选择";
    }

    @Override
    protected void initView() {
        serverAdapter = new ServerAdapter(this, null);
    }

    @Override
    protected void initData() {
        rsrl.setOnRefreshListener(() -> rsrl.setRefreshing(false));
    }

    @Override
    public void initNet() {
        List<ServerInfo> serverInfoList = new ArrayList<>();
        serverInfoList.add(new ServerInfo("02测试服", "http://192.168.0.2:8080/"));
        serverInfoList.add(new ServerInfo("05测试服", "http://192.168.0.5:8081/"));
        serverInfoList.add(new ServerInfo("外网正式服", "http://api.fwzspt.cn/"));
        serverAdapter.setData(serverInfoList);
        plv.setAdapter(serverAdapter);
    }
}
