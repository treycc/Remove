package com.jdp.hls.page.supervise.statistics.progress.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonFragmentAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/11/27 0027 下午 2:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressActivity extends BaseTitleActivity {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private String[] tabNames;
    private Fragment[] fragments = new Fragment[2];

    @Override
    public void initVariable() {
        tabNames = getResources().getStringArray(R.array.building_type);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_statistics_progress;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
    }

    @Override
    protected String getContentTitle() {
        return "进度统计";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        tab.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tabNames.length; i++) {
            tab.addTab(tab.newTab().setText(tabNames[i]));
        }
        fragments[0] = StatisticsProgressFragment.newInstance(0);
        fragments[1] = StatisticsProgressFragment.newInstance(1);
        CommonFragmentAdapter fragmentAdapter = new CommonFragmentAdapter(this, fragments, tabNames);
        vp.setAdapter(fragmentAdapter);
        tab.setupWithViewPager(vp);
    }

    @Override
    public void initNet() {

    }
}
