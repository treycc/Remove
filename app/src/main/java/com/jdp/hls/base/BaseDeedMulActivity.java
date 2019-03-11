package com.jdp.hls.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.jdp.hls.R;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddDeedListEvent;
import com.jdp.hls.event.ModifyDeedListEvent;
import com.jdp.hls.event.RefreshCertCountEvent;
import com.jdp.hls.event.RefreshCertNumEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedItem;
import com.jdp.hls.other.file.FileConfig;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.AddableRecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/10/18 0018 下午 2:15
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseDeedMulActivity extends BaseTitleActivity {
    @BindView(R.id.rv_addable_photo_preview)
    protected AddableRecyclerView rvAddablePhotoPreview;
    protected boolean mIsAdd;
    protected String mBuildingId;
    protected String mBuildingType;
    protected String mFileType;
    protected String mEditable;
    protected int mCertId;

    public FileConfig getFileConfig() {
        return mFileConfig;
    }

    public void setFileConfig(FileConfig mFileConfig) {
        this.mFileConfig = mFileConfig;
    }

    private FileConfig mFileConfig;

    @Override
    public void initVariable() {
        mBuildingId = getIntent().getStringExtra(Constants.Extra.BUILDING_ID);
        mBuildingType = getIntent().getStringExtra(Constants.Extra.BUILDING_TYPE);
        mFileType = getIntent().getStringExtra(Constants.Extra.FILETYPE);
        mCertId = getIntent().getIntExtra(Constants.Extra.CERT_ID, 0);
        mIsAdd = mCertId == 0;
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
        setFileConfig(new FileConfig(Integer.valueOf(mFileType), mBuildingId, mBuildingType));
//        rvPhotoPreview.setData(null, getFileConfig(), true);
    }

    @Override
    public abstract void initNet();

    public static void goActivity(Activity context, Class<? extends BaseDeedMulActivity> clazz, String fileType, String
            buildingId, String buildingType, int certId) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(Constants.Extra.FILETYPE, fileType);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        intent.putExtra(Constants.Extra.CERT_ID, certId);
        context.startActivity(intent);
    }

    public static void goActivity(Activity context, Class<? extends BaseDeedMulActivity> clazz, String
            buildingId, String buildingType, int certId) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        intent.putExtra(Constants.Extra.CERT_ID, certId);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    rvAddablePhotoPreview.onPhotoCallback(requestCode, resultCode, data);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return !mIsAdd;
    }

    protected void showSaveDeedSuccess(RefreshCertNumEvent refreshCertNumEvent) {
        EventBus.getDefault().post(refreshCertNumEvent);
        ToastUtil.showText("保存成功");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 100);

    }

    protected void refreshDeedList(DeedItem deedItem) {
        if (mIsAdd) {
            EventBus.getDefault().post(new AddDeedListEvent(deedItem));
            EventBus.getDefault().post(new RefreshCertCountEvent(deedItem.getCertType(), true));
        } else {
            EventBus.getDefault().post(new ModifyDeedListEvent(deedItem));
        }

        showSuccessDialogAndFinish();
    }
}
