package com.jdp.hls.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;


/**
 * Description:TODO
 * Create Time:2018/1/22 16:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CommonFragmentAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;
    private String[] titles;

    public CommonFragmentAdapter(AppCompatActivity context, Fragment[] fragments, String[] titles) {
        super(context.getSupportFragmentManager());
        this.fragments = fragments;
        this.titles = titles;
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
}
