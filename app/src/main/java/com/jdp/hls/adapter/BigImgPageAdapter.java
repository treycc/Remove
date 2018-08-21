package com.jdp.hls.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.model.entiy.DTOImgInfo;

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
        ImageView imageView = new ImageView(context);
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
