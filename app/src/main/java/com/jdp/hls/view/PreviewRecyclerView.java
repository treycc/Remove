package com.jdp.hls.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BaseRvPositionAdapter;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.util.FileUtil;
import com.jdp.hls.util.MatisseUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/10 0010 下午 4:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class PreviewRecyclerView extends RecyclerView {
    private List<DTOImgInfo> imgInfos = new ArrayList<>();
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
        initView(imgInfos);
    }

    public void create(List<ImgInfo> imgInfos) {
        initView(getDTOFromImgInfo(imgInfos));
    }

    public static List<DTOImgInfo> getDTOFromImgInfo(List<ImgInfo> imgInfos) {
        List<DTOImgInfo> dtoImgInfos = new ArrayList<>();
        for (ImgInfo imgInfo : imgInfos) {
            DTOImgInfo dtoImgInfo = new DTOImgInfo();
            dtoImgInfo.setId(imgInfo.getId());
            if (imgInfo.getUri() == null) {
                dtoImgInfo.setUrl(imgInfo.getFileUrl());
                dtoImgInfo.setSmallUrl(imgInfo.getSmallImgUrl());
            } else {
                dtoImgInfo.setUriStr(imgInfo.getUri().toString());
            }
            dtoImgInfos.add(dtoImgInfo);
        }
        return dtoImgInfos;
    }

    public void initView(List<DTOImgInfo> imgInfos) {
        previewImgAdapter = new PreviewImgAdapter(getContext(), imgInfos);
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
        previewImgAdapter.setData(getDTOFromImgInfo(imgInfos));
    }

    public void addDeleteIds(ArrayList<String> deleteIds) {
        deleteIds.addAll(deleteIds);
    }

    public List<DTOImgInfo> getDTOImgInfo() {
        return previewImgAdapter.getDate();
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

    public List<File> getAddedFiles() {
        List<DTOImgInfo> dates = previewImgAdapter.getDate();
        List<File> addedFiles =new ArrayList<>();
        for (DTOImgInfo dtoImgInfo : dates) {
            String uriStr = dtoImgInfo.getUriStr();
            if (!TextUtils.isEmpty(uriStr)) {
                File photoFile = FileUtil.getFileByUri(Uri.parse(uriStr), getContext());
                addedFiles.add(photoFile);
            }
        }
        return addedFiles;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.RequestCode.REQUEST_CODE_PHOTO_PREVIEW && data != null) {
            ArrayList<String> deleteIds = data.getStringArrayListExtra(Constants.Extra.DELETE_IDS);
            List<DTOImgInfo> dtoImgInfos = (List<DTOImgInfo>) data.getSerializableExtra(Constants.Extra.DTO_IMGS);
            Iterator<DTOImgInfo> it = previewImgAdapter.getDate().iterator();
            while (it.hasNext()) {
                DTOImgInfo next = it.next();
                for (String deleteId : deleteIds) {
                    if (next.getId().equals(deleteId)) {
                        it.remove();
                    }
                }
            }
            previewImgAdapter.addData(dtoImgInfos);
            this.deleteIds.addAll(deleteIds);
        }
    }

    public void onActivityResult(Intent data) {
        onActivityResult(Constants.RequestCode.REQUEST_CODE_PHOTO_PREVIEW, Activity.RESULT_OK, data);
    }


    public class PreviewImgAdapter extends BaseRvPositionAdapter<DTOImgInfo> {
        protected final String TAG = getClass().getSimpleName();

        PreviewImgAdapter(Context context, List<DTOImgInfo> list) {
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
        protected void bindHolder(ViewHolder baseHolder, List<DTOImgInfo> list, int position) {
            ImgInfoViewHolder holder = (ImgInfoViewHolder) baseHolder;
            if (list.size() == 0) {
                holder.iv_img.setImageResource(R.mipmap.pic_stay_uploaded);
            } else {
                String url = list.get(position).getSmallUrl();
                if (TextUtils.isEmpty(url)) {
                    ImageLoader.getInstance().loadImage(context, Uri.parse(list.get(position).getUriStr()), holder
                            .iv_img);
                } else {
                    ImageLoader.getInstance().loadImage(context, url, holder.iv_img);
                }
            }
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

        public List<DTOImgInfo> getDate() {
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
