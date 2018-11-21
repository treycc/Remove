package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EmployeeCompanyAdapter extends BaseSearchAdapter<Employee> {

    public EmployeeCompanyAdapter(Context context, List<Employee> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_employ_company, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Employee employee = (Employee) getItem(position);
        viewHolder.tv_companyName.setString(employee.getCompanyName());
        viewHolder.tv_companyName.setVisibility(isTitle(position)?View.VISIBLE:View.GONE);
        viewHolder.tv_realName.setString(employee.getRealName());
        return convertView;
    }

    public boolean isTitle(int position) {
        return position == 0 || !resultList.get(position).getCompanyName().equals(resultList.get(position - 1)
                .getCompanyName());
    }

    @Override
    protected void doSearch(List<Employee> list, List<Employee> resultList, String keyword) {
        for (Employee employee : list) {
            if (employee.getRealName().contains(keyword) || employee.getMobilePhone().contains(keyword) || employee
                    .getCompanyName().contains(keyword)) {
                resultList.add(employee);
            }
        }
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_companyName;
        public StringTextView tv_realName;

        public ViewHolder(View root) {
            this.root = root;
            tv_companyName = root.findViewById(R.id.tv_companyName);
            tv_realName = root.findViewById(R.id.tv_realName);
        }
    }
}
