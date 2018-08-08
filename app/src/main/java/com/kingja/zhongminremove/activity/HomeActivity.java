package com.kingja.zhongminremove.activity;

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

import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.base.BaseTitleActivity;
import com.kingja.zhongminremove.fragment.MapFragment;
import com.kingja.zhongminremove.fragment.MessageFragment;
import com.kingja.zhongminremove.fragment.MineFragment;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private static final int FRAGMENT_MAP = 0;
    private static final int FRAGMENT_MESSAGE = 1;
    private static final int FRAGMENT_MINE = 2;
    private int currentTabIndex = 0;

    @OnClick({R.id.tv_tab_map, R.id.tv_tab_message, R.id.tv_tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tab_map:
                switchFragment(FRAGMENT_MAP);
                break;
            case R.id.tv_tab_message:
                switchFragment(FRAGMENT_MESSAGE);
                break;
            case R.id.tv_tab_mine:
                switchFragment(FRAGMENT_MINE);
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {

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
        fragmentMap.put(FRAGMENT_MAP, currentFragment = MapFragment.newInstance());
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
        tvTabMap.setTextColor(fragmentId == FRAGMENT_MAP ? ContextCompat.getColor(this, R.color.main) : ContextCompat
                .getColor(this, R.color.c_9));
        tvTabMessage.setTextColor(fragmentId == FRAGMENT_MESSAGE ? ContextCompat.getColor(this, R.color.main) :
                ContextCompat
                        .getColor(this, R.color.c_9));
        tvTabMine.setTextColor(fragmentId == FRAGMENT_MINE ? ContextCompat.getColor(this, R.color.main) : ContextCompat
                .getColor(this, R.color.c_9));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragmentMap.clear();
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
        } else {
            ToastUtil.showText("连续点击退出");
            mLastTime = currentTime;

        }
    }

}
