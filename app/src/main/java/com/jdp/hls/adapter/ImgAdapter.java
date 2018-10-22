package com.jdp.hls.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.util.LogUtil;

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
    private boolean editable = true;
    private List<String> deleteImgIds = new ArrayList<>();
    private OnImgDeletedListener onImgDeletedListener;

    public ImgAdapter(Context context, List<ImgInfo> list) {
        super(context, list);
    }

    public ImgAdapter(Context context, List<ImgInfo> list, boolean editable) {
        super(context, list);
        this.editable = editable;
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
        if (editable && (position == getItemCount() - 1)) {
            holder.iv_img.setImageResource(R.mipmap.bg_add_photo);
            holder.iv_clear.setVisibility(View.GONE);
        } else {
            String url = list.get(position).getSmallImgUrl();

            if (TextUtils.isEmpty(url)) {
                ImageLoader.getInstance().loadImage(context, list.get(position).getUri(), holder.iv_img);
            } else {

                ImageLoader.getInstance().loadImage(context, url, holder.iv_img);
            }

            holder.iv_clear.setVisibility(editable ? View.VISIBLE : View.GONE);
            holder.iv_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onImgDeletedListener != null) {
                        onImgDeletedListener.onImgDeleted();
                    }
                    deleteImgIds.add(list.get(position).getId());
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface OnImgDeletedListener {
        void onImgDeleted();
    }

    public void setOnImgDeletedListener(OnImgDeletedListener onImgDeletedListener) {
        this.onImgDeletedListener = onImgDeletedListener;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        notifyDataSetChanged();
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

    public List<DTOImgInfo> getDTOData() {
        List<DTOImgInfo> dtoImgInfos = new ArrayList<>();
        for (ImgInfo imgInfo : list) {
            DTOImgInfo dtoImgInfo = new DTOImgInfo();
            if (imgInfo.getUri() == null) {
                dtoImgInfo.setUrl(imgInfo.getFileUrl());
            } else {
                dtoImgInfo.setUriStr(imgInfo.getUri().toString());
            }
            dtoImgInfos.add(dtoImgInfo);
        }
        return dtoImgInfos;
    }

    public void addUris(List<Uri> uris) {
        addData(getImgInfoFromUri(uris));
    }

    public static List<ImgInfo> getImgInfoFromUri(List<Uri> uris) {
        List<ImgInfo> imgInfos = new ArrayList<>();
        for (Uri uri : uris) {
            ImgInfo imgInfo = new ImgInfo();
            imgInfo.setUri(uri);
            imgInfos.add(imgInfo);
        }
        return imgInfos;
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
        return editable ? super.getItemCount() + 1 : super.getItemCount();
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
