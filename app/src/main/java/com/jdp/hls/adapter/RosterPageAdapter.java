package com.jdp.hls.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jdp.hls.R;


/**
 * Description:TODO
 * Create Time:2018/1/22 16:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterPageAdapter extends FragmentPagerAdapter {
    private final Context context;
    private Fragment[] fragments;
    private String[] titles;
    private  String[] rosterCountArr;

    public RosterPageAdapter(Context context, FragmentManager fm, Fragment[] fragments, String[] titles,String[] rosterCountArr) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
        this.rosterCountArr = rosterCountArr;
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
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tab_roster, null);
        TextView tv_rosterType = view.findViewById(R.id.tv_rosterType);
        TextView tv_rosterCount = view.findViewById(R.id.tv_rosterCount);
        tv_rosterType.setText(titles[position]);
        tv_rosterCount.setText(rosterCountArr[position]);
        return view;
    }

    public void refreshrosterCount(String[] rosterCountArr) {
        this.rosterCountArr=rosterCountArr;
        notifyDataSetChanged();
    }
}
