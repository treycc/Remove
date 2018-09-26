package com.jdp.hls.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.util.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/1/22 16:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PhotoPreviewAdapter extends BaseLvAdapter<DTOImgInfo> {
    private boolean showCheckbox;
    private ArrayList<String> deleteIds = new ArrayList<>();

    public PhotoPreviewAdapter(Context context, List<DTOImgInfo> list) {
        super(context, list);
    }

    public void showCheckbox(boolean showCheckbox) {
        this.showCheckbox = showCheckbox;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size() + 1;
    }


    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_gv_preview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position == getCount() - 1) {
            viewHolder.iv_img.setImageResource(R.mipmap.bg_add_photo);
            viewHolder.cb_check.setVisibility(View.GONE);
        } else {
            String url = list.get(position).getSmallUrl();
            if (TextUtils.isEmpty(url)) {
                ImageLoader.getInstance().loadImage(context, Uri.parse(list.get(position).getUriStr()), viewHolder
                        .iv_img);
            } else {
                ImageLoader.getInstance().loadImage(context, url, viewHolder.iv_img);
            }
            viewHolder.iv_img.setColorFilter(list.get(position).isChecked() ? ContextCompat.getColor(context, R.color
                    .half_t) : Color.TRANSPARENT);
            viewHolder.cb_check.setVisibility(showCheckbox ? View.VISIBLE : View.GONE);
            viewHolder.cb_check.setChecked(list.get(position).isChecked());
            viewHolder.cb_check.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (buttonView.isPressed()) {
                    list.get(position).setChecked(isChecked);
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }

    public void deleteImgs() {
        Iterator<DTOImgInfo> it = list.iterator();
        while (it.hasNext()) {
            DTOImgInfo next = it.next();
            if (next.isChecked()) {
                deleteIds.add(next.getId());
                it.remove();
            }
        }
        notifyDataSetChanged();
    }

    public ArrayList<String> getDeleteIds() {
        return deleteIds;
    }

    public List<DTOImgInfo> getAddImgs() {
        List<DTOImgInfo> addImgs = new ArrayList<>();
        for (DTOImgInfo imgInfo : list) {
            if (!TextUtils.isEmpty(imgInfo.getUriStr())) {
                addImgs.add(imgInfo);
            }
        }
        return addImgs;
    }

    public static List<DTOImgInfo> getDTOImgInfoFromImgInfo(List<ImgInfo> imgInfos) {
        List<DTOImgInfo> dtoImgInfos = new ArrayList<>();
        for (ImgInfo imgInfo : imgInfos) {
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

    @Override
    public void addData(List<DTOImgInfo> dtoImgInfos) {
        list.addAll(dtoImgInfos);
        LinkedHashSet<DTOImgInfo> set = new LinkedHashSet<>(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
        notifyDataSetChanged();
    }

    public void addUris(List<Uri> uris) {
        addData(getDTODataFromUri(uris));
    }

    public List<DTOImgInfo> getDTODataFromUri(List<Uri> uris) {
        List<DTOImgInfo> dtoImgInfos = new ArrayList<>();
        for (Uri uri : uris) {
            DTOImgInfo dtoImgInfo = new DTOImgInfo();
            dtoImgInfo.setUriStr(uri.toString());
            dtoImgInfos.add(dtoImgInfo);
        }
        return dtoImgInfos;
    }

    public void setCheckedAll(boolean checked) {
        for (DTOImgInfo imgInfo : list) {
            imgInfo.setChecked(checked);
        }
        notifyDataSetChanged();
    }

    public List<DTOImgInfo> getDate() {
        return list;
    }

    public boolean isLastItem(int position) {
        return position == getCount() - 1;
    }

    public class ViewHolder {
        public final View root;
        public ImageView iv_img;
        public CheckBox cb_check;

        public ViewHolder(View root) {
            this.root = root;
            iv_img = root.findViewById(R.id.iv_img);
            cb_check = root.findViewById(R.id.cb_check);
        }
    }
}
