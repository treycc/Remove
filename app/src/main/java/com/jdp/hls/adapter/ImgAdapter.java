package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.ImgInfo;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/7/12 13:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ImgAdapter extends BaseRvAdaper<ImgInfo> {
    public ImgAdapter(Context context, List<ImgInfo> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new ImgInfoViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_roster_img;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, ImgInfo visitor, int position) {
        final ImgInfoViewHolder holder = (ImgInfoViewHolder) baseHolder;
//        holder.tv_visitor_tab.setText(visitor.getName());
    }


    class ImgInfoViewHolder extends ViewHolder {
        ImageView iv_roster_img;

        ImgInfoViewHolder(View itemView) {
            super(itemView);
            iv_roster_img = itemView.findViewById(R.id.iv_roster_img);
        }
    }

}
