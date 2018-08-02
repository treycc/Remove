package com.kingja.zhongminremove.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.adapter.RosterPageAdapter;
import com.kingja.zhongminremove.base.BaseTitleActivity;
import com.kingja.zhongminremove.fragment.MessageFragment;
import com.kingja.zhongminremove.fragment.MineFragment;
import com.kingja.zhongminremove.fragment.RosterPersonFragment;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.util.GoUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/7/30 0030 上午 10:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterListActivity extends BaseTitleActivity {
    @BindView(R.id.tab_roster)
    TabLayout tabRoster;
    @BindView(R.id.vp_roster)
    ViewPager vpRoster;
    private String[] rosters = {"个人", "企业"};
    private Fragment mFragmentArr[] = new Fragment[2];


    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_roster_list;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "花名册";
    }

    @Override
    protected void initView() {
        tabRoster.setTabMode(TabLayout.MODE_FIXED);
        tabRoster.addTab(tabRoster.newTab().setText(rosters[0]));
        tabRoster.addTab(tabRoster.newTab().setText(rosters[1]));
        mFragmentArr[0] = new RosterPersonFragment();
        mFragmentArr[1] = new RosterPersonFragment();
        RosterPageAdapter mRosterPageAdapter = new RosterPageAdapter(this, getSupportFragmentManager(), mFragmentArr,
                rosters);
        vpRoster.setAdapter(mRosterPageAdapter);
        vpRoster.setOffscreenPageLimit(2);
        tabRoster.setupWithViewPager(vpRoster);

        for (int i = 0; i < tabRoster.getTabCount(); i++) {
            TabLayout.Tab tab = tabRoster.getTabAt(i);
            tab.setCustomView(mRosterPageAdapter.getTabView(i));
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
