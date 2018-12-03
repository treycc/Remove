package com.jdp.hls.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.jdp.hls.R;


/**
 * Description:TODO
 * Create Time:2018/1/22 16:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LineChartPageAdapter extends FragmentPagerAdapter {
    private final Context context;
    private Fragment[] fragments;
    private int[] tabImgs;

    public LineChartPageAdapter(AppCompatActivity context, Fragment[] fragments, int[] tabImgs) {
        super(context.getSupportFragmentManager());
        this.context = context;
        this.fragments = fragments;
        this.tabImgs = tabImgs;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }


    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tab_linechart, null);
        ImageView iv_icon = view.findViewById(R.id.iv_icon);
        iv_icon.setBackgroundResource(tabImgs[position]);
        return view;
    }
}
