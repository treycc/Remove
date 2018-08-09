package com.jdp.hls.adapter;

import android.content.Context;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Project;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 上午 10:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectAdapter extends CommonAdapter<Project> {

    public ProjectAdapter(Context context, List<Project> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ViewHolder helper, Project item) {
    }

    @Override
    protected int getItemLayoutId() {
        return  R.layout.item_project;
    }
}
