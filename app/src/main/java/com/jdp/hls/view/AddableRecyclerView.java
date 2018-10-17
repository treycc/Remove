package com.jdp.hls.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.jdp.hls.activity.BigImgActivity;
import com.jdp.hls.adapter.BaseRvPositionAdapter;
import com.jdp.hls.adapter.ImgAdapter;
import com.jdp.hls.adapter.ImgUriAdapter;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.page.rosteradd.RosterAddActivity;
import com.jdp.hls.page.rosterdetail.RosterDetailActivity;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.PermissionsUtil;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/10/17 0017 下午 1:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddableRecyclerView extends RecyclerView {
    private List<ImgInfo> imgInfos = new ArrayList<>();
    private Context context;
    private ImgAdapter imgAdapter;
    private boolean editable=true;

    public AddableRecyclerView(Context context) {
        this(context, null);
    }

    public AddableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initAddableRecyclerView();
    }

    private void initAddableRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        setLayoutManager(layoutManager);
        imgAdapter = new ImgAdapter(context, imgInfos);
        setAdapter(imgAdapter);
        setItemAnimator(new DefaultItemAnimator());
        addItemDecoration(new RvItemDecoration(context, RvItemDecoration.LayoutStyle.HORIZONTAL_LIST,
                0, 0x00ffffff));
        imgAdapter.setOnItemClickListener(new BaseRvPositionAdapter.OnItemClickListener<ImgInfo>() {
            @Override
            public void onItemClick(List<ImgInfo> list, int position) {
                if (editable&&imgAdapter.isLastItem(position)) {
                    PermissionsUtil.checkOpenPhoto((AppCompatActivity) context);
                } else {
                    BigImgActivity.goActivity((AppCompatActivity) context, MatisseUtil.getDTOImgInfoFromImgInfo
                            (imgAdapter.getDate()), position);
                }
            }
        });

    }
    public String getDeleteImgIds() {
        return imgAdapter.getDeleteImgIds();
    }
    public void onPhotoCallback(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    imgAdapter.addData(MatisseUtil.getImgInfoFromUri(Matisse.obtainResult(data)));
                    break;
                default:
                    break;
            }
        }
    }

    public void setDate(List<ImgInfo> photos, boolean editable) {
        this.editable = editable;
        imgAdapter.setData(photos == null ? new ArrayList<>() : photos);
        imgAdapter.setEditable(editable);
    }

    public void setDate(List<ImgInfo> photos) {
        setDate(photos, true);
    }

    public List<ImgInfo> getDate() {
        return imgAdapter.getDate();
    }
}
