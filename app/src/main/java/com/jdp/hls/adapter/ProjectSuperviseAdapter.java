package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.model.entiy.ProjectSupervise;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectSuperviseAdapter extends BaseLvAdapter<ProjectSupervise> {

    public ProjectSuperviseAdapter(Context context, List<ProjectSupervise> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_supervise_project, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_projectName.setString(list.get(position).getProjectName());
        viewHolder.tv_address.setString(list.get(position).getAddress());
        viewHolder.tv_percentDesc.setString(list.get(position).getPercentDesc());
        viewHolder.tv_totalQuantity.setText(list.get(position).getTotalQuantity());
        return convertView;
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_projectName;
        public StringTextView tv_address;
        public StringTextView tv_percentDesc;
        public TextView tv_totalQuantity;

        public ViewHolder(View root) {
            this.root = root;
            tv_projectName = root.findViewById(R.id.tv_projectName);
            tv_address = root.findViewById(R.id.tv_address);
            tv_percentDesc = root.findViewById(R.id.tv_percentDesc);
            tv_totalQuantity = root.findViewById(R.id.tv_totalQuantity);
        }
    }
}
