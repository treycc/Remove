package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.ConfigCompany;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectConfigAdapter extends BaseLvAdapter<ConfigCompany> {

    public ProjectConfigAdapter(Context context, List<ConfigCompany> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_project_config, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_companyTypeName.setText(list.get(position).getCompanyTypeName());
        viewHolder.tv_companyName.setHint(list.get(position).getMarkedWords());
        String companyName = list.get(position).getCompanyName().replace(",","\n");
        viewHolder.tv_companyName.setString(companyName);
        return convertView;
    }

    public void modifyItem(ConfigCompany configCompany) {
        for (ConfigCompany company : list) {
            if (company.getCompanyTypeID() == configCompany.getCompanyTypeID()) {
                company.setCompanyId(configCompany.getCompanyId());
                company.setCompanyName(configCompany.getCompanyName());
                company.setCompanyTypeIDS(configCompany.getCompanyTypeIDS());
            }
        }
        notifyDataSetChanged();
    }


    public class ViewHolder {
        public final View root;
        public StringTextView tv_companyName;
        public TextView tv_companyTypeName;

        public ViewHolder(View root) {
            this.root = root;
            tv_companyTypeName = root.findViewById(R.id.tv_companyTypeName);
            tv_companyName = root.findViewById(R.id.tv_companyName);
        }
    }
}
