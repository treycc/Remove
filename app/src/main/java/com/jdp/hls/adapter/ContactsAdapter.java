package com.jdp.hls.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.ContactsItem;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsAdapter extends BaseLvAdapter<ContactsItem> {
    private  boolean allowEdit;

    public ContactsAdapter(Context context, List<ContactsItem> list, boolean allowEdit) {
        super(context, list);
        this.allowEdit = allowEdit;
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
        ContactsItem contacts = list.get(position);
        viewHolder.iv_main.setBackgroundResource(contacts.getIsMainContact() == 1 ? R.mipmap.ic_confirm_sel : R
                .mipmap.ic_confirm_nor);
        viewHolder.tv_setMain.setText(contacts.getIsMainContact() == 1 ? "主联系人" : editable? "设为主联系人" : "");
        viewHolder.tv_setMain.setTextColor(contacts.getIsMainContact() == 1 ? ContextCompat.getColor(context, R.color
                .main) : ContextCompat.getColor(context, R.color.c_9));
        viewHolder.tv_name.setString(contacts.getRealName());
        viewHolder.tv_mobile.setString(contacts.getMobilePhone());
        viewHolder.tv_delete.setOnClickListener(v -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemDelete(contacts, position);
            }
        });
        viewHolder.drawHelperLayout.close(false);
        viewHolder.drawHelperLayout.setDragable(editable);
        viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemClick(contacts);
            }
        });
        viewHolder.ll_setMain.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (onItemOperListener != null) {
                    onItemOperListener.onAction1(contacts,position);
                }
            }
        });

        return convertView;
    }


    @Override
    public void modifyItem(ContactsItem item) {
        for (ContactsItem contactsItem : list) {
            if (contactsItem.getPersonId().equals(item.getPersonId())) {
                contactsItem.setIsMainContact(item.getIsMainContact());
                contactsItem.setRealName(item.getRealName());
                contactsItem.setMobilePhone(item.getMobilePhone());
            }
        }
        notifyDataSetChanged();
    }

    public void setMainContacts(int position) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIsMainContact(i==position?1:0);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_name;
        public StringTextView tv_mobile;
        public DrawHelperLayout drawHelperLayout;
        public TextView tv_delete;
        public LinearLayout ll_setMain;
        public TextView tv_setMain;
        public ImageView iv_main;

        public ViewHolder(View root) {
            this.root = root;
            tv_name = root.findViewById(R.id.tv_name);
            tv_mobile = root.findViewById(R.id.tv_mobile);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
            ll_setMain = root.findViewById(R.id.ll_setMain);
            tv_setMain = root.findViewById(R.id.tv_setMain);
            iv_main = root.findViewById(R.id.iv_main);
        }
    }
}
