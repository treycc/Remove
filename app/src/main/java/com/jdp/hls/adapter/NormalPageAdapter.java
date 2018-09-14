package com.jdp.hls.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
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
public class NormalPageAdapter extends FragmentPagerAdapter {
    private final Context context;
    private Fragment[] fragments;
    private String[] titles;
    private int[] icons;

    public NormalPageAdapter(AppCompatActivity context, Fragment[] fragments, String[] titles, int[] icons) {
        super(context.getSupportFragmentManager());
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
        this.icons = icons;
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
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tab_airphoto, null);
        TextView tv_tab_title = view.findViewById(R.id.tv_tab_title);
        ImageView iv_tab_icon = view.findViewById(R.id.iv_tab_icon);
        iv_tab_icon.setBackgroundResource(icons[position]);
        tv_tab_title.setText(titles[position]);
        return view;
    }
}
