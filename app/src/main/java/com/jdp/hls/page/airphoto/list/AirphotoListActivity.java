package com.jdp.hls.page.airphoto.list;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.NormalPageAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.airphoto.building.AirPhotoBuildingActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.SpSir;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:航拍复查首页
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
    private String[] tabTitles = {"待办复查", "已办复查", "办结复查"};
    private int[] tabIcons = {R.drawable.selector_tab_airphoto_todo, R.drawable.selector_tab_airphoto_done, R
            .drawable.selector_tab_airphoto_finish};
    private AirPhotoListFragment mFragmentArr[] = new AirPhotoListFragment[3];
    private int airPhotoType;


    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etAirphotoKeyword.setText("");
                break;
        }
    }

    @Override
    public void initVariable() {
        airPhotoType = getIntent().getIntExtra(Constants.Extra.AIRPHOTO_TYPE, 0);

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
        mFragmentArr[0] = AirPhotoListFragment.newInstance(Constants.AirPhotoType.TODO);
        mFragmentArr[1] = AirPhotoListFragment.newInstance(Constants.AirPhotoType.DONE);
        mFragmentArr[2] = AirPhotoListFragment.newInstance(Constants.AirPhotoType.FINISH);
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
        vpAirphoto.setCurrentItem(airPhotoType);
    }

    @Override
    protected void initData() {
        if (SpSir.getInstance().isOperatorAccount()) {
            setRightClick("发起", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    GoUtil.goActivity(AirphotoListActivity.this, AirPhotoBuildingActivity.class);
                }
            });
        }
        etAirphotoKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                search(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });

    }


    private void search(String keyword) {
        for (int i = 0; i < mFragmentArr.length; i++) {
            mFragmentArr[i].search(keyword);
        }
    }

    @Override
    public void initNet() {

    }

    public static void goActivity(Context context, int airPhotoType) {
        Intent intent = new Intent(context, AirphotoListActivity.class);
        intent.putExtra(Constants.Extra.AIRPHOTO_TYPE, airPhotoType);
        context.startActivity(intent);
    }

}
