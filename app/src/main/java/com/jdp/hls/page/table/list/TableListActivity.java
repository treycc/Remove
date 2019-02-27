package com.jdp.hls.page.table.list;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CountPageAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Table;
import com.jdp.hls.page.table.TableSearchActivity;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SpSir;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:一览表首页
 * Create Time:2018/9/6 0006 下午 4:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TableListActivity extends BaseTitleActivity implements TableListContract.View {
    @BindView(R.id.tab_table)
    TabLayout tabTable;
    @BindView(R.id.vp_table)
    ViewPager vpTable;
    private String[] tabBusinessTitles = {"住宅一览表", "企业一览表"};
    private String[] tabBusinessCounts = {"0", "0"};
    private int[] tabBusinessIcons = {R.drawable.selector_tab_personal, R.drawable.selector_tab_company};
    private TableListFragment mFragmentArr[] = new TableListFragment[2];
    private List<Table> personalTables = new ArrayList<>();
    private List<Table> companyTables = new ArrayList<>();
    @Inject
    TableListPresenter tableListPresenter;
    private List<Table> tables;
    private String statisId;


    @Override
    public void initVariable() {
        statisId = getIntent().getStringExtra(Constants.Extra.STATIS_ID);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_table;
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
        return "征收一览表";
    }

    @Override
    protected void initView() {
        tableListPresenter.attachView(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initNet() {
        tableListPresenter.getTables(SpSir.getInstance().getProjectId(), -1, statisId == null ? "" : statisId);
    }

    @Override
    public void onGetTablesSuccess(List<Table> tables) {
        this.tables = tables;
        if (tables != null && tables.size() > 0) {
            fillData(tables);
            setRightClick("搜索", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    TableSearchActivity.goActivity(TableListActivity.this, tables);
                }
            });
        } else {
            showEmptyCallback();
        }
    }

    private void fillData(List<Table> tables) {
        personalTables.clear();
        companyTables.clear();
        for (Table table : tables) {
            if (table.getBuildingType() == 1) {
                companyTables.add(table);
            } else {
                personalTables.add(table);
            }
        }
        tabBusinessCounts[0] = String.valueOf(personalTables.size());
        tabBusinessCounts[1] = String.valueOf(companyTables.size());

        tabTable.setTabMode(TabLayout.MODE_FIXED);
        tabTable.addTab(tabTable.newTab().setText(tabBusinessTitles[0]));
        tabTable.addTab(tabTable.newTab().setText(tabBusinessTitles[1]));
        mFragmentArr[0] = TableListFragment.newInstance(personalTables, 0);
        mFragmentArr[1] = TableListFragment.newInstance(companyTables, 1);
        CountPageAdapter mCountPageAdapter = new CountPageAdapter(this, getSupportFragmentManager(),
                mFragmentArr, tabBusinessTitles, tabBusinessCounts, tabBusinessIcons);
        vpTable.setAdapter(mCountPageAdapter);
        vpTable.setOffscreenPageLimit(2);
        tabTable.setupWithViewPager(vpTable);
        for (int i = 0; i < tabTable.getTabCount(); i++) {
            TabLayout.Tab tab = tabTable.getTabAt(i);
            tab.setCustomView(mCountPageAdapter.getTabView(i));
        }
    }

    public static void goActivity(Context context, String statisId) {
        Intent intent = new Intent(context, TableListActivity.class);
        intent.putExtra(Constants.Extra.STATIS_ID, statisId);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
