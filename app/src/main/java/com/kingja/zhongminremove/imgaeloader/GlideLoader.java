package com.kingja.zhongminremove.imgaeloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.constant.Constants;

/**
 * Description：TODO
 * Create Time：2017/3/9 11:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GlideLoader implements IImageLoader {
    @Override
    public void loadImage(Context context, String url, int resourceId, ImageView view) {
        Glide.with(context)
                .load(Constants.BASE_URL + url)
                .centerCrop()
                .placeholder(resourceId == -1 ? R.mipmap.ic_logo : resourceId)
                .crossFade()
                .into(view);
    }

    @Override
    public void loadImage(Context context, Uri uri, ImageView view) {
        Glide.with(context)
                .load(uri)
                .centerCrop()
                .crossFade()
                .into(view);
    }
}
