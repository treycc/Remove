package com.jdp.hls.page.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.event.ResetLoginStatusEvent;
import com.jdp.hls.fragment.HomeFragment;
import com.jdp.hls.fragment.MessageFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.login.LoginActivity;
import com.jdp.hls.page.mine.MineFragment;
import com.jdp.hls.service.initialize.InitializeService;
import com.jdp.hls.util.AppManager;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 上午 11:53
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class HomeActivity extends BaseTitleActivity {

    @BindView(R.id.fl_home)
    FrameLayout flHome;
    @BindView(R.id.tv_tab_map)
    TextView tvTabMap;
    @BindView(R.id.tv_tab_message)
    TextView tvTabMessage;
    @BindView(R.id.tv_tab_mine)
    TextView tvTabMine;
    @BindView(R.id.iv_tab_map)
    ImageView ivTabMap;
    @BindView(R.id.ll_tab_map)
    LinearLayout llTabMap;
    @BindView(R.id.iv_tab_message)
    ImageView ivTabMessage;
    @BindView(R.id.ll_tab_message)
    LinearLayout llTabMessage;
    @BindView(R.id.iv_tab_mine)
    ImageView ivTabMine;
    @BindView(R.id.ll_tab_mine)
    LinearLayout llTabMine;
    private FragmentManager supportFragmentManager;
    private Fragment currentFragment;
    private static SparseArray<Fragment> fragmentMap = new SparseArray<>();
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_MESSAGE = 1;
    private static final int FRAGMENT_MINE = 2;
    private int currentTabIndex = 0;

    @OnClick({R.id.ll_tab_map, R.id.ll_tab_message, R.id.ll_tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab_map:
                switchFragment(FRAGMENT_HOME);
                break;
            case R.id.ll_tab_message:
                switchFragment(FRAGMENT_MESSAGE);
                break;
            case R.id.ll_tab_mine:
                switchFragment(FRAGMENT_MINE);
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "三垟湿地二期";
    }

    @Override
    protected void initView() {
        supportFragmentManager = getSupportFragmentManager();
        fragmentMap.put(FRAGMENT_HOME, currentFragment =new HomeFragment());
        fragmentMap.put(FRAGMENT_MESSAGE, MessageFragment.newInstance());
        fragmentMap.put(FRAGMENT_MINE, MineFragment.newInstance());
        getSupportFragmentManager().beginTransaction().add(R.id.fl_home, currentFragment).commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected boolean ifHideBack() {
        return true;
    }

    @Override
    protected boolean ifHideTitle() {
        return true;
    }

    private void switchFragment(int fragmentId) {
        if (fragmentId == currentTabIndex) {
            return;
        }
        Fragment targetFragment = fragmentMap.get(fragmentId);
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.hide(currentFragment).add(R.id.fl_home, targetFragment).commit();
        } else {
            transaction.hide(currentFragment).show(targetFragment).commit();
        }
        currentFragment = targetFragment;
        currentTabIndex = fragmentId;
        setTabStatus(fragmentId);
    }

    private void setTabStatus(int fragmentId) {
        tvTabMap.setTextColor(fragmentId == FRAGMENT_HOME ? ContextCompat.getColor(this, R.color.main) : ContextCompat
                .getColor(this, R.color.c_9));
        tvTabMessage.setTextColor(fragmentId == FRAGMENT_MESSAGE ? ContextCompat.getColor(this, R.color.main) :
                ContextCompat
                        .getColor(this, R.color.c_9));
        tvTabMine.setTextColor(fragmentId == FRAGMENT_MINE ? ContextCompat.getColor(this, R.color.main) : ContextCompat
                .getColor(this, R.color.c_9));
        ivTabMap.setBackgroundResource(fragmentId == FRAGMENT_HOME ? R.mipmap.ic_tab_home_sel : R.mipmap.ic_tab_home_nor);
        ivTabMessage.setBackgroundResource(fragmentId == FRAGMENT_MESSAGE ? R.mipmap.ic_tab_msg_sel : R.mipmap
                .ic_tab_msg_nor);
        ivTabMine.setBackgroundResource(fragmentId == FRAGMENT_MINE ? R.mipmap.ic_tab_mine_sel : R.mipmap
                .ic_tab_mine_nor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragmentMap.clear();
        EventBus.getDefault().unregister(this);
        LogUtil.e(TAG, "关闭页面: " );
    }

    //防止Fragment重生重叠
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    private long mLastTime;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastTime < 500) {
            finish();
            System.exit(0);
        } else {
            ToastUtil.showText("连续点击退出");
            mLastTime = currentTime;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reLogin(ResetLoginStatusEvent resetLoginStatusEvent) {
        AppManager.getAppManager().finishAllActivity();
        GoUtil.goActivity(this, LoginActivity.class);
    }
}
