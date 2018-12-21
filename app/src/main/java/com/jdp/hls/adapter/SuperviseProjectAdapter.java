package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Company;
import com.jdp.hls.model.entiy.Project;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/20 0020 下午 1:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SuperviseProjectAdapter extends BaseSearchAdapter<Project> {

    private List<Project> selectedList;

    public SuperviseProjectAdapter(Context context, List<Project> list, List<Project> selectedList) {
        super(context, list);
        this.selectedList = selectedList;
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
        Project project = (Project) getItem(position);
        viewHolder.tv_name.setText(project.getProjectName());
        viewHolder.iv_operate.setBackgroundResource(selectedList.contains(project) ? R.mipmap.ic_add_nor : R.mipmap
                .ic_add);
        return convertView;
    }

    @Override
    protected void doSearch(List<Project> list, List<Project> resultList, String keyword) {
        for (Project project : list) {
            if (project.getProjectName().contains(keyword)) {
                resultList.add(project);
            }
        }
    }

    public void addSelected(Project company) {
        selectedList.add(company);
        notifyDataSetChanged();
    }

    public void removeSelected(Project company) {
        selectedList.remove(company);
        notifyDataSetChanged();
    }

    public boolean isSelected(Project company) {
        return selectedList.contains(company);
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
