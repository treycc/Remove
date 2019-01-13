package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.model.entiy.ServerInfo;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ServerAdapter extends BaseLvAdapter<ServerInfo> {

    public ServerAdapter(Context context, List<ServerInfo> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_server, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ServerInfo serverInfo = (ServerInfo) getItem(position);
        viewHolder.tv_serverName.setText(serverInfo.getServerName());
        viewHolder.tv_serverUrl.setText(serverInfo.getServerUrl());
        viewHolder.iv_sel.setVisibility(SpSir.getInstance().getDebugUrl().equals(serverInfo.getServerUrl()) ? View.VISIBLE : View
                .GONE);
        return convertView;
    }


    public class ViewHolder {
        public final View root;
        public ImageView iv_sel;
        public StringTextView tv_serverName;
        public StringTextView tv_serverUrl;

        public ViewHolder(View root) {
            this.root = root;
            iv_sel = root.findViewById(R.id.iv_sel);
            tv_serverName = root.findViewById(R.id.tv_serverName);
            tv_serverUrl = root.findViewById(R.id.tv_serverUrl);
        }
    }
}
