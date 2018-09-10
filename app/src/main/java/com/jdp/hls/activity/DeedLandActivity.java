package com.jdp.hls.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.PermissionsUtil;
import com.jdp.hls.view.ImgRecyclerView;
import com.zhihu.matisse.Matisse;

import java.util.List;

import butterknife.BindView;

/**
 * Description:土地证
 * Create Time:2018/9/10 0010 上午 10:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedLandActivity extends BaseTitleActivity {
    @BindView(R.id.rv_img)
    ImgRecyclerView rvImg;

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_land;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "土地证";
    }

    @Override
    protected void initView() {
        rvImg.initView();
    }

    @Override
    protected void initData() {
        rvImg.setOnImgOperateListener(new ImgRecyclerView.OnImgOperateListener() {
            @Override
            public void onSelectPhoto() {
                PermissionsUtil.checkOpenPhoto(DeedLandActivity.this);
            }

            @Override
            public void onOpenBigImg(List<DTOImgInfo> dtoImgInfos, int position) {
                BigImgActivity.goActivity(DeedLandActivity.this,dtoImgInfos, position);
            }
        });
    }

    @Override
    protected void initNet() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    rvImg.addUris(Matisse.obtainResult(data));
                    break;
                default:
                    break;

            }
        }
    }
}
