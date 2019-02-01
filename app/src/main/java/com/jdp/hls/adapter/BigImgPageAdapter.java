package com.jdp.hls.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.chrisbanes.photoview.PhotoView;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.ImageUtil;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/8/21 0021 下午 4:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BigImgPageAdapter extends PagerAdapter {
    private Context context;
    private List<DTOImgInfo> imgInfos;

    public BigImgPageAdapter(Context context, List<DTOImgInfo> imgInfos) {
        this.context = context;
        this.imgInfos = imgInfos;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        return imgInfos.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView imageView = new PhotoView(context);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogUtil.showConfirmDialog(context, "保存图片到本地", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ImageUtil.saveImgView(context,imageView);
                    }
                });
                return true;
            }
        });
        String fileUrl = imgInfos.get(position).getUrl();
        if (TextUtils.isEmpty(fileUrl)) {
            ImageLoader.getInstance().loadImage(context, Uri.parse(imgInfos.get(position).getUriStr()) , imageView);
        } else {
            ImageLoader.getInstance().loadImage(context, fileUrl, imageView);
        }
        container.addView(imageView);
        return imageView;
    }
}
