package com.jdp.hls.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jdp.hls.R;
import com.jdp.hls.adapter.RosterPageAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.fragment.RosterPersonFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Roster;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    private String[] rosterArr = {"个人", "企业"};
    private String[] rosterCountArr = {"0户", "0家"};
    private Fragment mFragmentArr[] = new Fragment[2];
    private List<Roster> rosters;
    private List<Roster> personalRosters = new ArrayList<>();
    private List<Roster> companyRosters = new ArrayList<>();


    @Override
    public void initVariable() {
        rosters = (List<Roster>) getIntent().getSerializableExtra("rosters");
        for (Roster roster : rosters) {
            if (roster.isEnterprise()) {
                companyRosters.add(roster);
            } else {
                personalRosters.add(roster);
            }
        }
        rosterCountArr[0]=personalRosters.size()+"户";
        rosterCountArr[1]=companyRosters.size()+"家";
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
        tabRoster.addTab(tabRoster.newTab().setText(rosterArr[0]));
        tabRoster.addTab(tabRoster.newTab().setText(rosterArr[1]));
        mFragmentArr[0] = RosterPersonFragment.newInstance(personalRosters);
        mFragmentArr[1] = RosterPersonFragment.newInstance(companyRosters);
        RosterPageAdapter mRosterPageAdapter = new RosterPageAdapter(this, getSupportFragmentManager(), mFragmentArr,
                rosterArr,rosterCountArr);
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

    public static void goActivity(Activity activity, List<Roster> rosters) {
        Intent intent = new Intent(activity, RosterListActivity.class);
        intent.putExtra("rosters", (Serializable) rosters);
        activity.startActivity(intent);
    }

}
