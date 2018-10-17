package com.jdp.hls.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.model.entiy.ImgInfo;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/8/17 0017 上午 9:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MatisseUtil {
    public static final int REQUEST_CODE_CHOOSE = 888;

    public static void openCamera(Activity context, int maxCount) {
        openCamera(context, maxCount, REQUEST_CODE_CHOOSE);
    }

    public static void openCamera(Activity context, int maxCount, int requestCode) {
        Matisse.from(context)
                .choose(MimeType.ofAll(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.jdp.hls.fileProvider"))
                .maxSelectable(maxCount)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .originalEnable(true)
                .maxOriginalSize(10)
                .forResult(requestCode);
    }

    public static void openCameraInFragment(Fragment context, int maxCount) {
        Matisse.from(context)
                .choose(MimeType.ofAll(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.jdp.hls.fileProvider"))
                .maxSelectable(maxCount)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .originalEnable(true)
                .maxOriginalSize(10)
                .forResult(REQUEST_CODE_CHOOSE);
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
            } else {
                dtoImgInfo.setUriStr(imgInfo.getUri().toString());
            }
            dtoImgInfos.add(dtoImgInfo);
        }
        return dtoImgInfos;
    }

}
