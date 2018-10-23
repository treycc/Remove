package com.jdp.hls.base;

import android.app.Activity;
import android.content.Intent;

import com.jdp.hls.R;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.other.file.FileConfig;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.PreviewRecyclerView;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/10/18 0018 下午 2:15
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseDeedActivity extends BaseTitleActivity {
    @BindView(R.id.rv_photo_preview)
    protected PreviewRecyclerView rvPhotoPreview;
    protected boolean mIsAdd;
    protected String mBuildingId;
    protected String mBuildingType;
    protected String mFileType;

    public FileConfig getFileConfig() {
        return mFileConfig;
    }

    public void setFileConfig(FileConfig mFileConfig) {
        this.mFileConfig = mFileConfig;
    }

    private FileConfig mFileConfig;

    @Override
    public void initVariable() {
        mIsAdd = getIntent().getBooleanExtra(Constants.Extra.ISADD, false);
        mBuildingId = getIntent().getStringExtra(Constants.Extra.BUILDING_ID);
        mBuildingType = getIntent().getStringExtra(Constants.Extra.BUILDING_TYPE);
        mFileType = getIntent().getStringExtra(Constants.Extra.FILETYPE);
    }

    @Override
    protected abstract int getContentView();

    @Override
    protected abstract void initComponent(AppComponent appComponent);

    @Override
    protected abstract String getContentTitle();

    @Override
    protected abstract void initView();

    @Override
    protected void initData() {
        setFileConfig(new FileConfig(mFileType, mBuildingId, mBuildingType));
        rvPhotoPreview.setConfig(getFileConfig());
    }

    @Override
    protected abstract void initNet();

    public static void goActivity(Activity context, Class<? extends BaseDeedActivity> clazz, String fileType, String
            buildingId, String buildingType, boolean isAdd) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(Constants.Extra.FILETYPE, fileType);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        intent.putExtra(Constants.Extra.ISADD, isAdd);
        context.startActivityForResult(intent, Integer.valueOf(fileType));
    }

    public void setResult(String certNum) {
        ToastUtil.showText("保存成功");
        Intent intent = new Intent();
        intent.putExtra(Constants.Extra.CERTNUM, certNum);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        rvPhotoPreview.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return !mIsAdd;
    }
}
