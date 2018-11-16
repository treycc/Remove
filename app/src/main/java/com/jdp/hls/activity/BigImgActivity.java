package com.jdp.hls.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BigImgPageAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.util.AppUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/8/21 0021 下午 4:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BigImgActivity extends BaseTitleActivity {

    @BindView(R.id.vp_big_img)
    ViewPager vpBigImg;
    @BindView(R.id.ll_dot)
    LinearLayout llDot;
    private List<DTOImgInfo> dtoImgInfos;
    private List<View> points = new ArrayList<>();
    private int position;

    @Override
    public void initVariable() {
        dtoImgInfos = (List<DTOImgInfo>) getIntent().getSerializableExtra("dtoImgInfos");
        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_big_img;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "查看大图";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        initDot();
        vpBigImg.setAdapter(new BigImgPageAdapter(this, dtoImgInfos));
        vpBigImg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (dtoImgInfos.size() < 2) {
                    return;
                }
                for (int i = 0; i < points.size(); i++) {
                    if (i == position) {
                        points.get(i).setBackgroundResource(R.mipmap.ic_dot_sel);
                    } else {
                        points.get(i).setBackgroundResource(R.mipmap.ic_dot_nor);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpBigImg.setCurrentItem(position);
    }

    @Override
    public void initNet() {

    }

    public static void goActivity(Activity activity, List<DTOImgInfo> dtoImgInfos, int position) {
        Intent intent = new Intent(activity, BigImgActivity.class);
        intent.putExtra("dtoImgInfos", (Serializable) dtoImgInfos);
        intent.putExtra("position",  position);
        activity.startActivity(intent);
    }

    private void initDot() {
        if (dtoImgInfos.size() < 2) {
            return;
        }
        for (int i = 0; i < dtoImgInfos.size(); i++) {
            View view = new View(this);
            if (i == 0) {
                view.setBackgroundResource(R.mipmap.ic_dot_sel);
            } else {
                view.setBackgroundResource(R.mipmap.ic_dot_nor);
            }
            points.add(view);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AppUtil.dp2px(10), AppUtil.dp2px(10));
        layoutParams.setMargins(0, 0, AppUtil.dp2px(10), 0);
        for (int i = 0; i < dtoImgInfos.size(); i++) {
            llDot.addView(points.get(i), layoutParams);
        }

    }
}
