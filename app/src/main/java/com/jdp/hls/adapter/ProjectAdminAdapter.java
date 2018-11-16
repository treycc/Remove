package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.i.OnSearchListener;
import com.jdp.hls.model.entiy.Person;
import com.jdp.hls.model.entiy.ProjectItem;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.view.FixedListView;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectAdminAdapter extends BaseSearchAdapter<ProjectItem> {

    public ProjectAdminAdapter(Context context, List<ProjectItem> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_project_admin, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_address.setString(list.get(position).getAddress());
        viewHolder.tv_projectName.setString(list.get(position).getProjectName());
        viewHolder.tv_projectSysCode.setString(list.get(position).getProjectSysCode());
        viewHolder.tv_projectEmployeeName.setString(list.get(position).getProjectEmployeeName());
        return convertView;
    }

    @Override
    protected void doSearch(List<ProjectItem> list, List<ProjectItem> resultList, String keyword) {
        for (ProjectItem project : list) {
            if (project.getProjectName().contains(keyword) || project.getAddress().contains(keyword) || project
                    .getProjectEmployeeName().contains(keyword)) {
                resultList.add(project);
            }
        }
    }


    public class ViewHolder {
        public final View root;
        public StringTextView tv_projectSysCode;
        public StringTextView tv_projectName;
        public StringTextView tv_address;
        public StringTextView tv_projectEmployeeName;

        public ViewHolder(View root) {
            this.root = root;
            tv_projectSysCode = root.findViewById(R.id.tv_projectSysCode);
            tv_projectName = root.findViewById(R.id.tv_projectName);
            tv_address = root.findViewById(R.id.tv_address);
            tv_projectEmployeeName = root.findViewById(R.id.tv_projectEmployeeName);
        }
    }
}
