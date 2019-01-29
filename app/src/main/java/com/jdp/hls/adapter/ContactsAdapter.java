package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Contacts;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsAdapter extends BaseLvAdapter<Contacts> {
    public ContactsAdapter(Context context, List<Contacts> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_contacts, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contacts contacts = list.get(position);
        viewHolder.tv_name.setString(contacts.getName());
        viewHolder.tv_mobile.setString(contacts.getMobile());
        viewHolder.iv_main_contacts.setVisibility(contacts.isMain() ? View.VISIBLE : View.GONE);
        viewHolder.tv_delete.setOnClickListener(v -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemDelete(contacts, position);
            }
        });
        viewHolder.drawHelperLayout.close(false);
        viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemClick(contacts);
            }
        });

        return convertView;
    }


    public class ViewHolder {
        public final View root;
        public StringTextView tv_name;
        public StringTextView tv_mobile;
        public ImageView iv_main_contacts;
        public DrawHelperLayout drawHelperLayout;
        public TextView tv_delete;

        public ViewHolder(View root) {
            this.root = root;
            tv_name = root.findViewById(R.id.tv_name);
            tv_mobile = root.findViewById(R.id.tv_mobile);
            iv_main_contacts = root.findViewById(R.id.iv_main_contacts);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
        }
    }
}
