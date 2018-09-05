package com.jdp.hls.imgaeloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jdp.hls.R;
import com.jdp.hls.util.SpSir;

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
                .load(SpSir.getInstance().getServerName()+ url)
                .centerCrop()
                .placeholder(resourceId == -1 ? R.mipmap.ic_img_placeholder : resourceId)
                .error(R.mipmap.ic_img_fail)
                .crossFade()
                .skipMemoryCache(true)
                .into(view);
    }

    @Override
    public void loadImage(Context context, Uri uri, ImageView view) {
        Glide.with(context)
                .load(uri)
                .centerCrop()
                .placeholder(R.mipmap.ic_img_placeholder)
                .error(R.mipmap.ic_img_fail)
                .crossFade()
                .skipMemoryCache(true)
                .into(view);
    }
}
