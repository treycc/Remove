package com.jdp.hls.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.imgaeloader.ImageLoader;

import java.util.LinkedHashSet;
import java.util.List;


/**
 * Description:TODO
 * Create Time:2018/7/12 13:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ImgUriAdapter extends BaseRvPositionAdapter<Uri> {
    protected  final String TAG = getClass().getSimpleName();

    public ImgUriAdapter(Context context, List<Uri> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new ImgInfoViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_img_clear;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, List<Uri> list, int position) {
        final ImgInfoViewHolder holder = (ImgInfoViewHolder) baseHolder;

        if (position == getItemCount() - 1) {
            holder.iv_img.setImageResource(R.mipmap.bg_add_photo);
            holder.iv_clear.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().loadImage(context, list.get(position), holder.iv_img);
            holder.iv_clear.setVisibility(View.VISIBLE);
            holder.iv_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

    }

    @Override
    public void addData(List<Uri> list) {
        this.list.addAll(list);
        LinkedHashSet<Uri> set = new LinkedHashSet<>(this.list.size());
        set.addAll(this.list);
        this.list.clear();
        this.list.addAll(set);
        this.notifyDataSetChanged();
    }

    public void clearDate() {
        this.list.clear();
        this.notifyDataSetChanged();
    }

    public List<Uri> getDate() {
        return list;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    class ImgInfoViewHolder extends ViewHolder {
        ImageView iv_img;
        ImageView iv_clear;

        ImgInfoViewHolder(View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            iv_clear = itemView.findViewById(R.id.iv_clear);
        }
    }

    public boolean isLastItem(int position) {
        return position == getItemCount() - 1;
    }

}
