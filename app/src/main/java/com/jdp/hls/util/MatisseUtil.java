package com.jdp.hls.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.model.entiy.ImgInfo;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/8/17 0017 上午 9:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MatisseUtil {
    public static final int REQUEST_CODE_CHOOSE=888;

    public static void openCamera(Activity context) {
        Matisse.from(context)
                .choose(MimeType.allOf())
                .countable(true)
//                .capture(true)
                .theme(R.style.PhotoTheme)//主题  暗色主题 R.style.Matisse_Dracula
                .maxSelectable(9) // 图片选择的最多数量
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
    }
    public static void openCamera(Activity context,int maxCount) {
        Matisse.from(context)
                .choose(MimeType.allOf())
                .countable(true)
//                .capture(true)
                .theme(R.style.PhotoTheme)//主题  暗色主题 R.style.Matisse_Dracula
                .maxSelectable(maxCount) // 图片选择的最多数量
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
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

    public static List<DTOImgInfo> getDTOImgInfoFromUri(List<Uri> uris) {
        List<DTOImgInfo> dtoImgInfos = new ArrayList<>();
        for (Uri uri : uris) {
            DTOImgInfo dtoImgInfo = new DTOImgInfo();
            dtoImgInfo.setUriStr(uri.toString());
            dtoImgInfos.add(dtoImgInfo);
        }
        return dtoImgInfos;
    }

    public static List<DTOImgInfo> getDTOImgInfoFromImgInfo(List<ImgInfo> imgInfos) {
        List<DTOImgInfo> dtoImgInfos = new ArrayList<>();
        for (ImgInfo imgInfo : imgInfos) {
            DTOImgInfo dtoImgInfo = new DTOImgInfo();
            if (imgInfo.getUri() == null) {
                dtoImgInfo.setUrl(imgInfo.getFileUrl());
            }else{
                dtoImgInfo.setUriStr(imgInfo.getUri().toString());
            }
            dtoImgInfos.add(dtoImgInfo);
        }
        return dtoImgInfos;
    }

}
