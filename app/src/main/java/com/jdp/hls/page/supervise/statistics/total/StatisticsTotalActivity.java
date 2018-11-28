package com.jdp.hls.page.supervise.statistics.total;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jdp.hls.R;
import com.jdp.hls.adapter.NormalPageAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.supervise.statistics.total.company.StatisticsTotalCompanyFragment;
import com.jdp.hls.page.supervise.statistics.total.personal.StatisticsTotalPersonalFragment;

import butterknife.BindView;

/**
 * Description:汇总统计
 * Create Time:2018/11/27 0027 下午 4:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsTotalActivity extends BaseTitleActivity {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private String[] tabNames;
    private Fragment[] fragments = new Fragment[2];
    private int[] tabIcons = {R.drawable.selector_tab_airphoto_todo, R.drawable.selector_tab_airphoto_done};

    @Override
    public void initVariable() {
        tabNames = getResources().getStringArray(R.array.statistics_type);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_statistics_total;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "汇总统计";
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

        fragments[0] = new StatisticsTotalPersonalFragment();
        fragments[1] = new StatisticsTotalCompanyFragment();
        NormalPageAdapter fragmentAdapter = new NormalPageAdapter(this, fragments, tabNames, tabIcons);
        vp.setAdapter(fragmentAdapter);
    }

    @Override
    public void initNet() {

    }
}
