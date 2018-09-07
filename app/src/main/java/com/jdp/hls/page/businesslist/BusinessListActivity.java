package com.jdp.hls.page.businesslist;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BusinessPageAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/9/6 0006 下午 4:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessListActivity extends BaseTitleActivity implements Observer {
    @BindView(R.id.et_business_keyword)
    EditText etBusinessKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.tab_business)
    TabLayout tabBusiness;
    @BindView(R.id.vp_business)
    ViewPager vpBusiness;
    private String[] tabBusinessTitles = {"个人", "企业"};
    private String[] tabBusinessCounts = {"0户", "0家"};
    private int[] tabBusinessIcons = {R.mipmap.ic_location_personal, R.mipmap.ic_location_enterprise};
    private BusinessListFragment mFragmentArr[] = new BusinessListFragment[2];
    private List<Business> personalBusiness = new ArrayList<>();
    private List<Business> companyBusiness = new ArrayList<>();

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_business;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "业务";
    }

    @Override
    protected void initView() {
        tabBusiness.setTabMode(TabLayout.MODE_FIXED);
        tabBusiness.addTab(tabBusiness.newTab().setText(tabBusinessTitles[0]));
        tabBusiness.addTab(tabBusiness.newTab().setText(tabBusinessTitles[1]));
        mFragmentArr[0] = BusinessListFragment.newInstance(personalBusiness, 0);
        mFragmentArr[1] = BusinessListFragment.newInstance(companyBusiness, 1);
        BusinessPageAdapter   mBusinessPageAdapter = new BusinessPageAdapter(this, getSupportFragmentManager(), mFragmentArr,
                tabBusinessTitles, tabBusinessCounts, tabBusinessIcons);
        vpBusiness.setAdapter(mBusinessPageAdapter);
        vpBusiness.setOffscreenPageLimit(2);
        tabBusiness.setupWithViewPager(vpBusiness);
        for (int i = 0; i < tabBusiness.getTabCount(); i++) {
            TabLayout.Tab tab = tabBusiness.getTabAt(i);
            tab.setCustomView(mBusinessPageAdapter.getTabView(i));
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
