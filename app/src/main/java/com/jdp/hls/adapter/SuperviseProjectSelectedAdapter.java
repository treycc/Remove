package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Project;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/20 0020 下午 1:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SuperviseProjectSelectedAdapter extends BaseLvAdapter<Project> {

    public SuperviseProjectSelectedAdapter(Context context, List<Project> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_manager, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getProjectName());
        viewHolder.iv_operate.setBackgroundResource(R.mipmap.ic_reduce);
        return convertView;
    }

    public String getIds() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                sb.append(String.valueOf(list.get(i).getProjectId()));
                sb.append("#");
            } else {
                sb.append(String.valueOf(list.get(i).getProjectId()));
            }
        }
        return sb.toString();
    }

    public String getNames() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                sb.append(String.valueOf(list.get(i).getProjectName()));
                sb.append(",");
            } else {
                sb.append(String.valueOf(list.get(i).getProjectName()));
            }
        }
        return sb.toString();
    }


    public class ViewHolder {
        public final View root;
        TextView tv_name;
        ImageView iv_operate;

        public ViewHolder(View root) {
            this.root = root;
            tv_name = root.findViewById(R.id.tv_name);
            iv_operate = root.findViewById(R.id.iv_operate);
        }
    }
}
