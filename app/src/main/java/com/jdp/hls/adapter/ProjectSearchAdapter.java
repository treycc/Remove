package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Project;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.StringTextView;
import com.kingja.supershapeview.view.SuperShapeTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectSearchAdapter extends BaseSearchAdapter<Project> {

    public ProjectSearchAdapter(Context context, List<Project> list) {
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
        Project project = (Project) getItem(position);
        viewHolder.iv_project_sel.setVisibility(SpSir.getInstance().getProjectId().equals(project.getProjectId()) ?
                View.VISIBLE : View.GONE);
        viewHolder.tv_projectName.setString(project.getProjectName());
        viewHolder.tv_projectYear.setString(String.valueOf(project.getYear()));
        viewHolder.tv_address.setString(project.getAddress());
        viewHolder.tv_realName.setString(project.getRealName());
        viewHolder.tv_percentDesc.setText(project.getPercentDesc());
        viewHolder.tv_totalQuantity.setText(project.getTotalQuantity() + "户");
        return convertView;
    }

    @Override
    protected void doSearch(List<Project> list, List<Project> resultList, String keyword) {
        for (Project project : list) {
            if (project.getProjectName().contains(keyword) || project.getAddress().contains(keyword) || project
                    .getRealName().contains(keyword)) {
                resultList.add(project);
            }
        }
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_projectName;
        public StringTextView tv_projectYear;
        public StringTextView tv_address;
        public StringTextView tv_realName;
        public SuperShapeTextView tv_percentDesc;
        public SuperShapeTextView tv_totalQuantity;
        public ImageView iv_project_sel;

        public ViewHolder(View root) {
            this.root = root;
            tv_projectName = root.findViewById(R.id.tv_projectName);
            tv_projectYear = root.findViewById(R.id.tv_projectYear);
            tv_address = root.findViewById(R.id.tv_address);
            tv_realName = root.findViewById(R.id.tv_realName);
            tv_percentDesc = root.findViewById(R.id.tv_percentDesc);
            tv_totalQuantity = root.findViewById(R.id.tv_totalQuantity);
            iv_project_sel = root.findViewById(R.id.iv_project_sel);
        }
    }
}
