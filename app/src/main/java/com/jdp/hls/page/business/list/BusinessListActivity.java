package com.jdp.hls.page.business.list;

import android.content.Context;
import android.content.Intent;
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
import com.jdp.hls.util.SpSir;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/9/6 0006 下午 4:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessListActivity extends BaseTitleActivity implements BussinessContract.View {
    @BindView(R.id.et_business_keyword)
    EditText etBusinessKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.tab_business)
    TabLayout tabBusiness;
    @BindView(R.id.vp_business)
    ViewPager vpBusiness;
    private String[] tabBusinessTitles = {"个人业务", "企业业务"};
    private String[] tabBusinessCounts = {"0", "0"};
    private int[] tabBusinessIcons = {R.drawable.selector_tab_personal, R.drawable.selector_tab_company};
    private BusinessListFragment mFragmentArr[] = new BusinessListFragment[2];
    private List<Business> personalBusiness = new ArrayList<>();
    private List<Business> companyBusiness = new ArrayList<>();
    @Inject
    BusinessPresenter businessPresenter;
    private String taskTypeName;
    private int taskType;

    @Override
    public void initVariable() {
        taskTypeName = getIntent().getStringExtra("taskTypeName");
        taskType = getIntent().getIntExtra("taskType", 0);
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
        return taskTypeName != null ? taskTypeName : "";
    }

    @Override
    protected void initView() {
        businessPresenter.attachView(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {
        businessPresenter.getBusinessList(SpSir.getInstance().getProjectId(), -1, taskType);
    }

    @Override
    public void onGetBusinessSuccess(List<Business> businesses) {
        personalBusiness.clear();
        companyBusiness.clear();
        for (Business business : businesses) {
            if (business.getBuildingType() == 1) {
                companyBusiness.add(business);
            } else {
                personalBusiness.add(business);
            }
        }
        tabBusinessCounts[0] = String.valueOf(personalBusiness.size());
        tabBusinessCounts[1] = String.valueOf(companyBusiness.size());

        tabBusiness.setTabMode(TabLayout.MODE_FIXED);
        tabBusiness.addTab(tabBusiness.newTab().setText(tabBusinessTitles[0]));
        tabBusiness.addTab(tabBusiness.newTab().setText(tabBusinessTitles[1]));
        mFragmentArr[0] = BusinessListFragment.newInstance(personalBusiness, taskType, 0);
        mFragmentArr[1] = BusinessListFragment.newInstance(companyBusiness, taskType, 1);
        BusinessPageAdapter mBusinessPageAdapter = new BusinessPageAdapter(this, getSupportFragmentManager(),
                mFragmentArr, tabBusinessTitles, tabBusinessCounts, tabBusinessIcons);
        vpBusiness.setAdapter(mBusinessPageAdapter);
        vpBusiness.setOffscreenPageLimit(2);
        tabBusiness.setupWithViewPager(vpBusiness);
        for (int i = 0; i < tabBusiness.getTabCount(); i++) {
            TabLayout.Tab tab = tabBusiness.getTabAt(i);
            tab.setCustomView(mBusinessPageAdapter.getTabView(i));
        }
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }

    public static void GoActivity(Context context, int taskType, String taskTypeName) {
        Intent intent = new Intent(context, BusinessListActivity.class);
        intent.putExtra("taskType", taskType);
        intent.putExtra("taskTypeName", taskTypeName);
        context.startActivity(intent);
    }
}
