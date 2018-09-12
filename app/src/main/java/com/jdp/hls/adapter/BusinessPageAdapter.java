package com.jdp.hls.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;


/**
 * Description:TODO
 * Create Time:2018/1/22 16:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessPageAdapter extends FragmentPagerAdapter {
    private final Context context;
    private Fragment[] fragments;
    private String[] titles;
    private  String[] rosterCountArr;
    private int[] imgArr;

    public BusinessPageAdapter(Context context, FragmentManager fm, Fragment[] fragments, String[] titles, String[] rosterCountArr, int[] imgArr) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
        this.rosterCountArr = rosterCountArr;
        this.imgArr = imgArr;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tab_business, null);
        TextView tv_businessType = view.findViewById(R.id.tv_businessType);
        TextView tv_businessCount = view.findViewById(R.id.tv_businessCount);
        ImageView iv_icon = view.findViewById(R.id.iv_icon);
        iv_icon.setBackgroundResource(imgArr[position]);
        tv_businessType.setText(titles[position]);
        tv_businessCount.setText(rosterCountArr[position]);
        return view;
    }

    public void refreshrosterCount(String[] rosterCountArr) {
        this.rosterCountArr=rosterCountArr;
        notifyDataSetChanged();
    }
}
