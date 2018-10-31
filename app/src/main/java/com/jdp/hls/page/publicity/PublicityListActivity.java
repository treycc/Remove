package com.jdp.hls.page.publicity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.adapter.NormalPageAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.publicity.apply.PublicityApplyActivity;
import com.jdp.hls.page.publicity.list.PublicityListFragment;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;

import butterknife.BindView;

/**
 * Description:公示管理首页
 * Create Time:2018/9/13 0013 下午 7:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityListActivity extends BaseTitleActivity {
    @BindView(R.id.tab_publicity)
    TabLayout tabPublicity;
    @BindView(R.id.vp_publicity)
    ViewPager vpPublicity;

    private String[] tabTitles = {"调查公示", "认定公示"};
    private int[] tabIcons = {R.drawable.selector_tab_publicity_survey, R.drawable.selector_tab_publicity_affirm};
    private Fragment tabFragmetns[] = new PublicityListFragment[2];

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_publicity;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "公示管理";
    }

    @Override
    protected void initView() {
        tabPublicity.setTabMode(TabLayout.MODE_FIXED);
        tabFragmetns[0] = PublicityListFragment.newInstance(0);
        tabFragmetns[1] = PublicityListFragment.newInstance(1);
        for (String tabTitle : tabTitles) {
            tabPublicity.addTab(tabPublicity.newTab().setText(tabTitle));
        }
        NormalPageAdapter normalPageAdapter = new NormalPageAdapter(this, tabFragmetns, tabTitles, tabIcons);
        vpPublicity.setAdapter(normalPageAdapter);
        vpPublicity.setOffscreenPageLimit(tabTitles.length);
        tabPublicity.setupWithViewPager(vpPublicity);

        for (int i = 0; i < tabTitles.length; i++) {
            TabLayout.Tab tab = tabPublicity.getTabAt(i);
            tab.setCustomView(normalPageAdapter.getTabView(i));
        }
    }

    @Override
    protected void initData() {
        setRightClick("申请", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                GoUtil.goActivity(PublicityListActivity.this, PublicityApplyActivity.class);
            }
        });
    }

    @Override
    protected void initNet() {

    }


}
