package com.jdp.hls.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.NormalPageAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.airphoto.list.AirPhotoListFragment;
import com.jdp.hls.page.airphoto.person.AirPhotoPersonActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;

import butterknife.BindView;

/**
 * Description:航拍复查
 * Create Time:2018/9/12 0012 上午 9:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AirphotoListActivity extends BaseTitleActivity {
    @BindView(R.id.iv_airphoto_search)
    ImageView ivAirphotoSearch;
    @BindView(R.id.et_airphoto_keyword)
    EditText etAirphotoKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.tab_airphoto)
    TabLayout tabAirphoto;
    @BindView(R.id.vp_airphoto)
    ViewPager vpAirphoto;
    private String[] tabTitles = {"待办航拍", "已办航拍", "办结航拍"};
    private int[] tabIcons = {R.drawable.selector_tab_airphoto_todo, R.drawable.selector_tab_airphoto_done, R
            .drawable.selector_tab_airphoto_finish};
    private Fragment mFragmentArr[] = new AirPhotoListFragment[3];

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_airphoto_list;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
    }

    @Override
    protected String getContentTitle() {
        return "航拍复查";
    }

    @Override
    protected void initView() {
        tabAirphoto.setTabMode(TabLayout.MODE_FIXED);
        mFragmentArr[0] = new AirPhotoListFragment();
        mFragmentArr[1] = new AirPhotoListFragment();
        mFragmentArr[2] = new AirPhotoListFragment();
        for (String tabTitle : tabTitles) {
            tabAirphoto.addTab(tabAirphoto.newTab().setText(tabTitle));
        }
        NormalPageAdapter normalPageAdapter = new NormalPageAdapter(this, mFragmentArr, tabTitles, tabIcons);
        vpAirphoto.setAdapter(normalPageAdapter);
        vpAirphoto.setOffscreenPageLimit(tabTitles.length);
        tabAirphoto.setupWithViewPager(vpAirphoto);

        for (int i = 0; i < tabTitles.length; i++) {
            TabLayout.Tab tab = tabAirphoto.getTabAt(i);
            tab.setCustomView(normalPageAdapter.getTabView(i));
        }
    }

    @Override
    protected void initData() {
        setRightClick("初审", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                GoUtil.goActivity(AirphotoListActivity.this,AirPhotoPersonActivity.class);
            }
        });
    }

    @Override
    protected void initNet() {

    }
}
