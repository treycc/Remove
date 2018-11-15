package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EmployeeAdapter extends BaseLvAdapter<Employee> {

    public EmployeeAdapter(Context context, List<Employee> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_employee, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.stv_realName.setText(list.get(position).getRealName());
        viewHolder.stv_mobilePhone.setText(list.get(position).getMobilePhone());
        viewHolder.stv_accountAlias.setText(list.get(position).getAccountAlias());
        viewHolder.stv_loginName.setText(list.get(position).getLoginName());
        ImageLoader.getInstance().loadCircleImage(context, list.get(position).getHeadUrl(), viewHolder.iv_headUrl);
        return convertView;
    }

    public void modifyEmployee(Employee employee) {
        for (Employee item : list) {
            if (item.getEmployeeId() == employee.getEmployeeId()) {
                item.setRealName(employee.getRealName());
                item.setMobilePhone(employee.getMobilePhone());
                item.setAccountAlias(employee.getAccountAlias());
                item.setLoginName(employee.getLoginName());
            }
        }
        notifyDataSetChanged();
    }

    public void AddEmployee(Employee employee) {
        Employee item = new Employee();
        item.setRealName(employee.getRealName());
        item.setMobilePhone(employee.getMobilePhone());
        item.setAccountAlias(employee.getAccountAlias());
        item.setLoginName(employee.getLoginName());
        list.add(0, item);
        notifyDataSetChanged();
    }


    public class ViewHolder {
        public final View root;
        public ImageView iv_headUrl;
        public StringTextView stv_realName;
        public StringTextView stv_mobilePhone;
        public StringTextView stv_accountAlias;
        public StringTextView stv_loginName;

        public ViewHolder(View root) {
            this.root = root;
            iv_headUrl = root.findViewById(R.id.iv_headUrl);
            stv_realName = root.findViewById(R.id.stv_realName);
            stv_mobilePhone = root.findViewById(R.id.stv_mobilePhone);
            stv_loginName = root.findViewById(R.id.stv_loginName);
            stv_accountAlias = root.findViewById(R.id.stv_accountAlias);
        }
    }
}
