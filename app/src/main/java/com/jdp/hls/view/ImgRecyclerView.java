package com.jdp.hls.view;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.jdp.hls.adapter.BaseRvPositionAdapter;
import com.jdp.hls.adapter.ImgAdapter;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.model.entiy.ImgInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/10 0010 下午 4:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ImgRecyclerView extends RecyclerView {
    private List<ImgInfo> imgInfos = new ArrayList<>();
    private OnImgOperateListener onImgOperateListener;
    private ImgAdapter imgAdapter;

    public ImgRecyclerView(Context context) {
        this(context,null);
    }

    public ImgRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImgRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void initView() {
        imgAdapter = new ImgAdapter(getContext(), imgInfos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        setLayoutManager(layoutManager);
        setAdapter(imgAdapter);
        setItemAnimator(new DefaultItemAnimator());
        addItemDecoration(new RvItemDecoration(getContext(), RvItemDecoration.LayoutStyle.HORIZONTAL_LIST,
                0, 0x00ffffff));
        imgAdapter.setOnItemClickListener(new BaseRvPositionAdapter.OnItemClickListener<ImgInfo>() {
            @Override
            public void onItemClick(List<ImgInfo> list, int position) {
                if (onImgOperateListener != null) {
                    if (imgAdapter.isLastItem(position)) {
                        onImgOperateListener.onSelectPhoto();
                    } else {
                        onImgOperateListener.onOpenBigImg(imgAdapter.getDTOData(), position);
                    }
                }

            }
        });
    }

    public void setData(List<ImgInfo> imgInfos) {
        imgAdapter.setData(imgInfos);
    }

    public void addData(List<ImgInfo> imgInfos) {
        imgAdapter.addData(imgInfos);
    }
    public void addUris(List<Uri> uris) {
        imgAdapter.addUris(uris);
    }

    public List<ImgInfo> getData() {
       return imgAdapter.getData();
    }
    public interface OnImgOperateListener{
        void onSelectPhoto();
        void onOpenBigImg(List<DTOImgInfo> dtoImgInfos, int position);
    }

    public void setOnImgOperateListener(OnImgOperateListener onImgOperateListener) {
        this.onImgOperateListener = onImgOperateListener;
    }
}
