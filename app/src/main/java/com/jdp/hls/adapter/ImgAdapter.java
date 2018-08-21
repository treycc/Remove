package com.jdp.hls.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.model.entiy.ImgInfo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/7/12 13:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ImgAdapter extends BaseRvPositionAdapter<ImgInfo> {
    protected final String TAG = getClass().getSimpleName();
    private List<String> deleteImgIds = new ArrayList<>();

    public ImgAdapter(Context context, List<ImgInfo> list) {
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
    protected void bindHolder(ViewHolder baseHolder, List<ImgInfo> list, int position) {
        final ImgInfoViewHolder holder = (ImgInfoViewHolder) baseHolder;
        if (position == getItemCount() - 1) {
            holder.iv_img.setImageResource(R.mipmap.bg_add_photo);
            holder.iv_clear.setVisibility(View.GONE);
        } else {
            String url = list.get(position).getSmallImgUrl();
            if (TextUtils.isEmpty(url)) {
                ImageLoader.getInstance().loadImage(context, list.get(position).getUri(), holder.iv_img);
            } else {
                ImageLoader.getInstance().loadImage(context, url, holder.iv_img);
            }

            holder.iv_clear.setVisibility(View.VISIBLE);
            holder.iv_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteImgIds.add(list.get(position).getId());
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void addData(List<ImgInfo> list) {
        this.list.addAll(list);
        LinkedHashSet<ImgInfo> set = new LinkedHashSet<>(this.list.size());
        set.addAll(this.list);
        this.list.clear();
        this.list.addAll(set);
        this.notifyDataSetChanged();
    }

    public List<ImgInfo> getDate() {
        return list;
    }

    public String getDeleteImgIds() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deleteImgIds.size(); i++) {
            if (i != deleteImgIds.size() - 1) {
                sb.append(deleteImgIds.get(i));
                sb.append("#");
            } else {
                sb.append(deleteImgIds.get(i));
            }
        }
        return sb.toString();
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
