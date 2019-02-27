package com.jdp.hls.page.admin.query.list;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CountPageAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BusinessQuery;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:数据查询
 * Create Time:2018/9/6 0006 下午 4:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class QueryListActivity extends BaseTitleActivity implements QueryListContract.View {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private String[] tabBusinessTitles = {"住宅业务", "企业业务"};
    private String[] tabBusinessCounts = {"0", "0"};
    private int[] tabBusinessIcons = {R.drawable.selector_tab_personal, R.drawable.selector_tab_company};
    private QueryListFragment mFragmentArr[] = new QueryListFragment[2];
    private List<BusinessQuery> personalBusiness = new ArrayList<>();
    private List<BusinessQuery> companyBusiness = new ArrayList<>();
    @Inject
    QueryListPresenter queryListPresenter;
    private QueryListFragment personalListFragment;
    private QueryListFragment companyListFragment;
    private List<BusinessQuery> queryList = new ArrayList<>();
    private CountPageAdapter mCountPageAdapter;
    private String keyword = "";
    private String projectId;
    private String projectName;

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etKeyword.setText("");
                keyword = "";
                break;
        }
    }

    @Override
    public void initVariable() {
        projectId = getIntent().getStringExtra(Constants.Extra.PROJECTID);
        projectName = getIntent().getStringExtra(Constants.Extra.PROJECTNAME);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_query;
    }


    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        queryListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return projectName;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                checkData(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void checkData(String keyword) {
        doSearch(keyword);
    }

    private void doSearch(String keyword) {
        this.keyword = keyword;
        if (queryList == null || queryList.size() == 0) {
            ToastUtil.showText("暂无信息");
            return;
        }
        refreshData();
    }

    private void refreshData() {
        List<BusinessQuery> selectBusiness = new ArrayList<>();
        for (BusinessQuery businessQuery : queryList) {
            if (businessQuery.getRealName().contains(keyword) || businessQuery.getAddress().contains(keyword) ||
                    businessQuery
                            .getMobilePhone().contains(keyword) || businessQuery.getCusCode().contains(keyword)) {
                selectBusiness.add(businessQuery);
            }
        }
        List<BusinessQuery> personalBusiness = new ArrayList<>();
        List<BusinessQuery> companyBusiness = new ArrayList<>();
        for (BusinessQuery business : selectBusiness) {
            if (business.getBuildingType() == 0) {
                personalBusiness.add(business);
            } else {
                companyBusiness.add(business);
            }
        }
        mFragmentArr[0].refreshData(personalBusiness);
        mFragmentArr[1].refreshData(companyBusiness);
        refreshTitles(personalBusiness.size(), companyBusiness.size());
    }

    private void refreshTitles(int personalRosters, int companyRosters) {
        tabBusinessCounts[0] = String.valueOf(personalRosters);
        tabBusinessCounts[1] = String.valueOf(companyRosters);
        mCountPageAdapter.refreshrosterCount(tabBusinessCounts);
        for (int i = 0; i < tab.getTabCount(); i++) {
            TabLayout.Tab t = tab.getTabAt(i);
            t.setCustomView(mCountPageAdapter.getTabView(i));
        }
    }

    @Override
    public void initNet() {
        queryListPresenter.getQueryList(SpSir.getInstance().getProjectId(), -1);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    public static void GoActivity(Context context, String projectId, String projectName) {
        Intent intent = new Intent(context, QueryListActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        intent.putExtra(Constants.Extra.PROJECTNAME, projectName);
        context.startActivity(intent);
    }

    @Override
    public void onQueryListSuccess(List<BusinessQuery> businessQueryList) {
        queryList = businessQueryList;
        personalBusiness.clear();
        companyBusiness.clear();
        if (queryList == null) {
            return;
        }
        for (BusinessQuery business : queryList) {
            if (business.getBuildingType() == 1) {
                companyBusiness.add(business);
            } else {
                personalBusiness.add(business);
            }
        }
        tabBusinessCounts[0] = String.valueOf(personalBusiness.size());
        tabBusinessCounts[1] = String.valueOf(companyBusiness.size());
        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.addTab(tab.newTab().setText(tabBusinessTitles[0]));
        tab.addTab(tab.newTab().setText(tabBusinessTitles[1]));
        personalListFragment = QueryListFragment.newInstance(personalBusiness, Status.BuildingType.PERSONAL);
        companyListFragment = QueryListFragment.newInstance(companyBusiness, Status.BuildingType.COMPANY);
        mFragmentArr[0] = personalListFragment;
        mFragmentArr[1] = companyListFragment;
        mCountPageAdapter = new CountPageAdapter(this, getSupportFragmentManager(),
                mFragmentArr, tabBusinessTitles, tabBusinessCounts, tabBusinessIcons);
        vp.setAdapter(mCountPageAdapter);
        vp.setOffscreenPageLimit(2);
        tab.setupWithViewPager(vp);
        for (int i = 0; i < tab.getTabCount(); i++) {
            TabLayout.Tab t = tab.getTabAt(i);
            t.setCustomView(mCountPageAdapter.getTabView(i));
        }
    }
}
