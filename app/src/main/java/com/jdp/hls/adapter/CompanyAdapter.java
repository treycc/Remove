package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Company;
import com.jdp.hls.model.entiy.Employee;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/20 0020 下午 1:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CompanyAdapter extends BaseSearchAdapter<Company> {

    private List<Company> selectedList;

    public CompanyAdapter(Context context, List<Company> list, List<Company> selectedList) {
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
        Company company = (Company) getItem(position);
        viewHolder.tv_name.setText(company.getCompanyName());
        viewHolder.iv_operate.setBackgroundResource(selectedList.contains(company) ? R.mipmap.ic_add_nor : R.mipmap
                .ic_add);
        return convertView;
    }

    @Override
    protected void doSearch(List<Company> list, List<Company> resultList, String keyword) {
        for (Company company : list) {
            if (company.getCompanyName().contains(keyword)) {
                resultList.add(company);
            }
        }
    }

    public void addSelected(Company company) {
        selectedList.add(company);
        notifyDataSetChanged();
    }

    public void removeSelected(Company company) {
        selectedList.remove(company);
        notifyDataSetChanged();
    }

    public boolean isSelected(Company company) {
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
