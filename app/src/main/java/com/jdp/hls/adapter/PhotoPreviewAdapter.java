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
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.StringUtil;
import com.jdp.hls.util.ToastUtil;

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
public class PhotoPreviewAdapter extends BaseLvAdapter<ImgInfo> {
    private boolean editable;
    private boolean showCheckbox;
    private ArrayList<String> deleteIds = new ArrayList<>();

    public PhotoPreviewAdapter(Context context, List<ImgInfo> list) {
        super(context, list);
    }

    public PhotoPreviewAdapter(Context context, List<ImgInfo> list, boolean editable) {
        super(context, list);
        this.editable = editable;
    }

    public void showCheckbox(boolean showCheckbox) {
        this.showCheckbox = showCheckbox;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return editable ? list.size() + 1 : list.size();
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
        if (editable && position == getCount() - 1) {
            viewHolder.iv_img.setVisibility(View.GONE);
            viewHolder.iv_img_add.setVisibility(View.VISIBLE);
            viewHolder.cb_check.setVisibility(View.GONE);
        } else {
            viewHolder.iv_img.setVisibility(View.VISIBLE);
            viewHolder.iv_img_add.setVisibility(View.GONE);
            String url = list.get(position).getSmallImgUrl();
            if (TextUtils.isEmpty(url)) {
                ImageLoader.getInstance().loadImage(context, list.get(position).getUri(), viewHolder.iv_img);
            } else {
                ImageLoader.getInstance().loadImage(context, url, viewHolder.iv_img);
            }
            viewHolder.cb_check.setVisibility(showCheckbox ? View.VISIBLE : View.GONE);
            viewHolder.cb_check.setChecked(list.get(position).isChecked());
            viewHolder.cb_check.setOnCheckedChangeListener((buttonView, isChecked) -> {

            });
        }
        return convertView;
    }

    public void deleteImgs() {
        Iterator<ImgInfo> it = list.iterator();
        while (it.hasNext()) {
            ImgInfo next = it.next();
            if (next.isChecked()) {
                it.remove();
                String id = next.getId();
                if (!TextUtils.isEmpty(id)) {
                    deleteIds.add(id);
                }
            }
        }
        notifyDataSetChanged();
    }

    public String getDeleteIds() {
        return StringUtil.getIds(deleteIds);
    }

    @Override
    public void addData(List<ImgInfo> photos) {
        list.addAll(photos);
        LinkedHashSet<ImgInfo> set = new LinkedHashSet<>(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
        notifyDataSetChanged();
    }

    public List<ImgInfo> getAddedPhotos() {
        List<ImgInfo> addedPhotos = new ArrayList<>();
        for (ImgInfo imgInfo : list) {
            if (imgInfo.getUri() != null) {
                addedPhotos.add(imgInfo);
            }
        }
        return addedPhotos;
    }

    public void addUris(List<Uri> uris) {
        addData(getPhotoFromUri(uris));
    }

    public List<ImgInfo> getPhotoFromUri(List<Uri> uris) {
        List<ImgInfo> imgInfos = new ArrayList<>();
        for (Uri uri : uris) {
            ImgInfo dtoImgInfo = new ImgInfo();
            dtoImgInfo.setUri(uri);
            imgInfos.add(dtoImgInfo);
        }
        return imgInfos;
    }

    public void setCheckedAll(boolean checked) {
        for (ImgInfo imgInfo : list) {
            imgInfo.setChecked(checked);
        }
        notifyDataSetChanged();
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

    public boolean isLastItem(int position) {
        return position == getCount() - 1;
    }

    public class ViewHolder {
        public final View root;
        public ImageView iv_img;
        public ImageView iv_img_add;
        public CheckBox cb_check;

        public ViewHolder(View root) {
            this.root = root;
            iv_img = root.findViewById(R.id.iv_img);
            iv_img_add = root.findViewById(R.id.iv_img_add);
            cb_check = root.findViewById(R.id.cb_check);
        }
    }
}
