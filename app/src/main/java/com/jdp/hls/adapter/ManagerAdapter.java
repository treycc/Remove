package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Employee;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/20 0020 下午 1:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ManagerAdapter extends BaseSearchAdapter<Employee> {

    private List<Employee> selectedList;

    public ManagerAdapter(Context context, List<Employee> list, List<Employee> selectedList) {
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
        Employee employee = (Employee) getItem(position);
        viewHolder.tv_name.setText(employee.getRealName());
        viewHolder.iv_operate.setBackgroundResource(selectedList.contains(employee) ? R.mipmap.ic_add_nor : R.mipmap
                .ic_add);
        return convertView;
    }

    @Override
    protected void doSearch(List<Employee> list, List<Employee> resultList, String keyword) {
        for (Employee employee : list) {
            if (employee.getRealName().contains(keyword) || employee.getMobilePhone().contains(keyword)) {
                resultList.add(employee);
            }
        }
    }

    public void addSelected(Employee employee) {
        selectedList.add(employee);
        notifyDataSetChanged();
    }

    public void removeSelected(Employee employee) {
        selectedList.remove(employee);
        notifyDataSetChanged();
    }

    public boolean isSelected(Employee employee) {
        return selectedList.contains(employee);
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
