package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.i.OnSearchListener;
import com.jdp.hls.model.entiy.Employee;
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
        ProjectItem project = (ProjectItem) getItem(position);
        viewHolder.tv_address.setString(project.getAddress());
        viewHolder.tv_projectName.setString(project.getProjectName());
        viewHolder.tv_projectSysCode.setString(project.getProjectSysCode());
        viewHolder.tv_projectEmployeeName.setString(project.getProjectEmployeeName());
        return convertView;
    }

    @Override
    protected void doSearch(List<ProjectItem> list, List<ProjectItem> resultList, String keyword) {
        for (ProjectItem project : list) {
            if (project.getProjectName().contains(keyword) || project.getAddress().contains(keyword) || project
                    .getProjectEmployeeName().contains(keyword) || project.getProjectSysCode().contains(keyword)) {
                resultList.add(project);
            }
        }
    }

    public void modifyProject(ProjectItem projectItem) {
        for (ProjectItem item : resultList) {
            if (item.getProjectId().equals(projectItem.getProjectId())) {
                item.setAddress(projectItem.getAddress());
                item.setProjectName(projectItem.getProjectName());
                item.setProjectSysCode(projectItem.getProjectSysCode());
                item.setProjectEmployeeName(projectItem.getProjectEmployeeName());
            }
        }
        notifyDataSetChanged();
    }

    public void addFirst(ProjectItem projectItem) {
        this.resultList.add(0, projectItem);
        this.notifyDataSetChanged();
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
