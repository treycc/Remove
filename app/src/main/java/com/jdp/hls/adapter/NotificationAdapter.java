package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.model.entiy.Notification;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NotificationAdapter extends BaseLvAdapter<Notification> {

    public NotificationAdapter(Context context, List<Notification> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_msg_notification, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_count.setString(list.get(position).getCount());
        viewHolder.tv_messageTypeName.setString(list.get(position).getMessageTypeName());
        viewHolder.tv_lastTime.setString(list.get(position).getLastTime());
        viewHolder.tv_content.setString(String.format("您有%d条%s，请查看处理", list.get(position).getCount(), list.get
                (position).getMessageTypeName()));
        ImageLoader.getInstance().loadCircleImage(context, list.get(position).getIconUrl(), viewHolder.iv_icon);
        return convertView;
    }


    public class ViewHolder {
        public final View root;
        public ImageView iv_icon;
        public StringTextView tv_count;
        public StringTextView tv_messageTypeName;
        public StringTextView tv_content;
        public StringTextView tv_lastTime;

        public ViewHolder(View root) {
            this.root = root;
            iv_icon = root.findViewById(R.id.iv_icon);
            tv_count = root.findViewById(R.id.tv_count);
            tv_messageTypeName = root.findViewById(R.id.tv_messageTypeName);
            tv_content = root.findViewById(R.id.tv_content);
            tv_lastTime = root.findViewById(R.id.tv_lastTime);
        }
    }
}
