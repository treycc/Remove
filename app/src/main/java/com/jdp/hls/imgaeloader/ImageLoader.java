package com.jdp.hls.imgaeloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Description：TODO
 * Create Time：2017/3/9 11:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ImageLoader implements IImageLoader {
    private ImageLoader() {
        imageLoader = new GlideLoader();
    }

    private static ImageLoader mGlideLoader;
    private static IImageLoader imageLoader;

    @Override
    public void loadImage(Context context, String url, int resourceId, ImageView view) {
        imageLoader.loadImage(context, url, resourceId, view);
    }

    @Override
    public void loadImage(Context context, Uri uri, ImageView view) {
        imageLoader.loadImage(context, uri, view);
    }

    @Override
    public void loadRoundImage(Context context, String url, int resourceId, ImageView view, int connerWidth) {
        imageLoader.loadRoundImage(context, url, resourceId, view, connerWidth);
    }

    @Override
    public void loadCircleImage(Context context, String url, int resourceId, ImageView view) {
        imageLoader.loadCircleImage(context, url, resourceId, view);
    }

    public void loadCircleImage(Context context, String url, ImageView view) {
        imageLoader.loadCircleImage(context, url, -1, view);
    }

    public void loadImage(Context context, String url, ImageView view) {
        loadImage(context, url, -1, view);
    }

    public static ImageLoader getInstance() {
        if (mGlideLoader == null) {
            synchronized (ImageLoader.class) {
                if (mGlideLoader == null) {
                    mGlideLoader = new ImageLoader();
                }
            }
        }
        return mGlideLoader;
    }
}
