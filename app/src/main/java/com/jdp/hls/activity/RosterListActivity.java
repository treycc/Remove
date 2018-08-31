package com.jdp.hls.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.RosterPageAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.page.rosteradd.RosterAddActivity;
import com.jdp.hls.page.rosterlist.RosterListFragment;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.et_rosters_keyword)
    EditText etRostersKeyword;
    @BindView(R.id.iv_rosters_search)
    ImageView ivRostersSearch;
    private String[] rosterArr = {"个人", "企业"};
    private String[] rosterCountArr = {"0户", "0家"};
    private RosterListFragment mFragmentArr[] = new RosterListFragment[2];
    private List<Roster> rosters;
    private List<Roster> personalRosters = new ArrayList<>();
    private List<Roster> companyRosters = new ArrayList<>();
    private RosterPageAdapter mRosterPageAdapter;

    @OnClick({R.id.iv_rosters_search})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_rosters_search:
                break;
        }
    }


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
        rosterCountArr[0] = personalRosters.size() + "户";
        rosterCountArr[1] = companyRosters.size() + "家";
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
        mFragmentArr[0] = RosterListFragment.newInstance(personalRosters, 0);
        mFragmentArr[1] = RosterListFragment.newInstance(companyRosters, 1);
        mRosterPageAdapter = new RosterPageAdapter(this, getSupportFragmentManager(), mFragmentArr,
                rosterArr, rosterCountArr);
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
        setRightClick("添加", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                GoUtil.goActivity(RosterListActivity.this, RosterAddActivity.class);
            }
        });
        etRostersKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkData(s.toString());
            }
        });
    }

    private void checkData(String keyword) {
        doSearch(keyword);
    }

    private void doSearch(String keyword) {
        if (rosters == null && rosters.size() == 0) {
            ToastUtil.showText("暂无花名册信息");
            return;
        }
        List<Roster> selectRosters = new ArrayList<>();
        for (Roster roster : rosters) {
            if (roster.getRealName().contains(keyword) || roster.getHouseAddress().contains(keyword) || roster
                    .getMobilePhone().contains(keyword)) {
                selectRosters.add(roster);
            }
        }
        List<Roster> personalRosters = new ArrayList<>();
        List<Roster> companyRosters = new ArrayList<>();
        for (Roster roster : selectRosters) {
            if (roster.isEnterprise()) {
                companyRosters.add(roster);
            } else {
                personalRosters.add(roster);
            }
        }
        mFragmentArr[0].refreshData(personalRosters);
        mFragmentArr[1].refreshData(companyRosters);

        rosterCountArr[0] = personalRosters.size() + "户";
        rosterCountArr[1] = companyRosters.size() + "家";

        mRosterPageAdapter.refreshrosterCount(rosterCountArr);

        for (int i = 0; i < tabRoster.getTabCount(); i++) {
            TabLayout.Tab tab = tabRoster.getTabAt(i);
            tab.setCustomView(mRosterPageAdapter.getTabView(i));
        }
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
