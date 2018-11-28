package com.jdp.hls.imgaeloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jdp.hls.R;
import com.jdp.hls.constant.Constants;

/**
 * Description：TODO
 * Create Time：2017/3/9 11:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GlideLoader implements IImageLoader {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void loadImage(Context context, String url, int resourceId, ImageView view) {
        Glide.with(context)
                .load(Constants.BASE_URL + url)
                .centerCrop()
                .placeholder(resourceId == -1 ? R.drawable.ic_placeholder : resourceId)
                .error(R.drawable.ic_img_fail)
                .crossFade()
                .into(view);
    }

    @Override
    public void loadImage(Context context, Uri uri, ImageView view) {
        Glide.with(context)
                .load(uri)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_img_fail)
                .crossFade()
                .skipMemoryCache(true)
                .into(view);
    }

    @Override
    public void loadRoundImage(Context context, String url, int resourceId, ImageView view, int connerWidth) {
        Glide.with(context)
                .load(Constants.BASE_URL + url)
                .placeholder(resourceId == -1 ? R.drawable.ic_placeholder : resourceId)
                .error(R.drawable.ic_img_fail)
                .crossFade()
                .transform(new CenterCrop(context), new GlideRoundTransform(context, connerWidth))
                .into(view);
    }

    @Override
    public void loadCircleImage(Context context, String url, int resourceId, ImageView view) {
        Glide.with(context)
                .load(Constants.BASE_URL + url)
                .centerCrop()
                .placeholder(resourceId == -1 ? R.drawable.ic_placeholder : resourceId)
                .error(R.drawable.ic_img_fail)
                .crossFade()
                .transform(new CenterCrop(context), new GlideCircleTransform(context))
                .into(view);
    }
}
