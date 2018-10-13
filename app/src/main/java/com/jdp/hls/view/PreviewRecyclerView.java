package com.jdp.hls.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.activity.PhotoPreviewActivity;
import com.jdp.hls.adapter.BaseRvPositionAdapter;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.model.entiy.ImgInfo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/10 0010 下午 4:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class PreviewRecyclerView extends RecyclerView {
    private List<ImgInfo> photos = new ArrayList<>();
    private ArrayList<String> deleteIds = new ArrayList<>();
    private PreviewImgAdapter previewImgAdapter;

    public PreviewRecyclerView(Context context) {
        this(context, null);
    }

    public PreviewRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PreviewRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void create() {
//        initView(imgInfos);
    }

    public void create(List<ImgInfo> imgInfos) {
//        initView(getDTOFromImgInfo(imgInfos));
    }


    public void initPhotos(List<ImgInfo> photos) {
        this.photos = photos;
        previewImgAdapter = new PreviewImgAdapter(getContext(), photos == null ? new ArrayList<>() : photos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        setLayoutManager(layoutManager);
        setAdapter(previewImgAdapter);
        setItemAnimator(new DefaultItemAnimator());
        addItemDecoration(new RvItemDecoration(getContext(), RvItemDecoration.LayoutStyle.HORIZONTAL_LIST,
                12, 0x00ffffff));
        previewImgAdapter.setOnItemClickListener((list, position) -> {
        });
    }


    public void setData(List<ImgInfo> imgInfos) {
        previewImgAdapter.setData(imgInfos);
    }


    public String getDeleteIds() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deleteIds.size(); i++) {
            if (i != deleteIds.size() - 1) {
                sb.append(deleteIds.get(i));
                sb.append("#");
            } else {
                sb.append(deleteIds.get(i));
            }
        }
        return sb.toString();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.RequestCode.PHOTO_PREVIEW && data != null) {
            photos = (List<ImgInfo>) data.getSerializableExtra(Constants.Extra.DTO_IMGS);
            previewImgAdapter.setData(photos);
        }
    }

    public void goPhotoPreviewActivity(Activity context) {
        PhotoPreviewActivity.goActivity(context, photos);
    }

    public void onActivityResult(Intent data) {
        onActivityResult(Constants.RequestCode.PHOTO_PREVIEW, Activity.RESULT_OK, data);
    }


    public class PreviewImgAdapter extends BaseRvPositionAdapter<ImgInfo> {
        protected final String TAG = getClass().getSimpleName();

        PreviewImgAdapter(Context context, List<ImgInfo> list) {
            super(context, list);
        }

        @Override
        protected ViewHolder createViewHolder(View v) {
            return new ImgInfoViewHolder(v);
        }

        @Override
        protected int getItemView() {
            return R.layout.item_photo_preview;
        }

        @Override
        protected void bindHolder(ViewHolder baseHolder, List<ImgInfo> list, int position) {
            ImgInfoViewHolder holder = (ImgInfoViewHolder) baseHolder;
            if (list.size() == 0) {
                holder.iv_img.setImageResource(R.mipmap.pic_stay_uploaded);
            } else {
                ImageLoader.getInstance().loadImage(context, list.get(position).getSmallImgUrl(), holder.iv_img);
            }
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

        public List<ImgInfo> getDate() {
            return list;
        }


        @Override
        public int getItemCount() {
            if (list.size() == 0) {
                return 1;
            } else {
                return list.size();
            }
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
}
