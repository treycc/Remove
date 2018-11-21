package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.ProjectItem;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectSelectAdapter extends BaseSearchAdapter<ProjectItem> {

    public ProjectSelectAdapter(Context context, List<ProjectItem> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_project, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ProjectItem project = (ProjectItem) getItem(position);
        viewHolder.tv_projectName.setText(project.getProjectName());
        viewHolder.tv_projectYear.setText(project.getYear());
        viewHolder.tv_projectManager.setText(project.getProjectEmployeeName());
        viewHolder.tv_projectAddress.setText(project.getAddress());
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
        public TextView tv_projectName;
        public TextView tv_projectYear;
        public TextView tv_projectManager;
        public TextView tv_projectAddress;

        public ViewHolder(View root) {
            this.root = root;
            tv_projectName = root.findViewById(R.id.tv_projectName);
            tv_projectYear = root.findViewById(R.id.tv_projectYear);
            tv_projectManager = root.findViewById(R.id.tv_projectManager);
            tv_projectAddress = root.findViewById(R.id.tv_projectAddress);
        }
    }
}
