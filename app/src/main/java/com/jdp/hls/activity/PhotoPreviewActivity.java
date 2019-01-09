package com.jdp.hls.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.PhotoPreviewAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.other.file.FileConfig;
import com.jdp.hls.page.photo.PhotoContract;
import com.jdp.hls.page.photo.PhotoPresenter;
import com.jdp.hls.util.FileUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.PermissionsUtil;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/9/21 0021 下午 3:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PhotoPreviewActivity extends BaseTitleActivity implements PhotoContract.View {
    @BindView(R.id.gv_photo_preview)
    GridView gvPhotoPreview;
    @BindView(R.id.cb_photo_select)
    CheckBox cbPhotoSelect;
    @BindView(R.id.tv_photo_delete)
    TextView tvPhotoDelete;
    @BindView(R.id.tv_photo_save)
    TextView tvPhotoSave;
    @BindView(R.id.ll_photo_operateBar)
    LinearLayout llPhotoOperateBar;
    private List<ImgInfo> photos = new ArrayList<>();
    private PhotoPreviewAdapter photoPreviewAdapter;
    @Inject
    PhotoPresenter photoPresenter;
    private FileConfig fileConfig;
    private boolean editable;

    @OnCheckedChanged({R.id.cb_photo_select})
    public void onChecked(CompoundButton buttonView, boolean isChecked) {
        cbPhotoSelect.setText(isChecked ? "取消全选" : "全选");
        photoPreviewAdapter.setCheckedAll(isChecked);
    }

    @OnClick({R.id.tv_photo_delete, R.id.tv_photo_save})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_photo_delete:
                LogUtil.e(TAG,"删除");
                photoPreviewAdapter.deleteImgs();
                break;
            case R.id.tv_photo_save:
                modifyPhotos();
                break;
        }
    }

    private void modifyPhotos() {
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("FileType",String.valueOf( fileConfig.getFileType()))
                .addFormDataPart("BuildingId", fileConfig.getBuildingId() == null ? "" : fileConfig.getBuildingId())
                .addFormDataPart("BuildingType", fileConfig.getBuildingType() == null ? "" : fileConfig
                        .getBuildingType())
                .addFormDataPart("MasterId", "")
                .addFormDataPart("DeleteFileIDs", photoPreviewAdapter.getDeleteIds());
        List<ImgInfo> imgInfos = photoPreviewAdapter.getAddedPhotos();
        for (int i = 0; i < imgInfos.size(); i++) {
            Uri uri = imgInfos.get(i).getUri();
            if (uri != null) {
                File photoFile = FileUtil.getFileByUri(uri, this);
                bodyBuilder.addFormDataPart("File" + i, photoFile.getName(), RequestBody.create(MediaType
                        .parse("image/*"), photoFile));
            }
        }
        RequestBody requestBody = bodyBuilder.build();
        photoPresenter.modifyPhotos(requestBody);
    }

    @OnItemClick({R.id.gv_photo_preview})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (editable&&photoPreviewAdapter.isLastItem(position)) {
            PermissionsUtil.checkOpenPhoto(this);
        } else {
            BigImgActivity.goActivity(this, photoPreviewAdapter.getDTOData(), position);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            List<Uri> selectedUris = Matisse.obtainResult(data);
            photoPreviewAdapter.addUris(selectedUris);
            if (selectedUris.size() > 0) {
                setEditStatus(true);
            }
        }
    }

    @Override
    public void initVariable() {
        photos = (List<ImgInfo>) getIntent().getSerializableExtra(Constants.Extra.DTO_IMGS);
        fileConfig = (FileConfig) getIntent().getSerializableExtra(Constants.Extra.FILE_CONFIG);
        editable = getIntent().getBooleanExtra(Constants.Extra.EDITABLE, false);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_photo_preview;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "照片";
    }

    @Override
    protected void initView() {
        photoPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        photoPreviewAdapter = new PhotoPreviewAdapter(this, photos == null ? new ArrayList<>() : photos, editable);
        gvPhotoPreview.setAdapter(photoPreviewAdapter);
        if (editable) {
            setEditStatus(false);
        }
    }

    @Override
    public void initNet() {

    }

    private void setEditStatus(boolean isEditStatus) {
        photoPreviewAdapter.showCheckbox(isEditStatus);
        llPhotoOperateBar.setVisibility(isEditStatus ? View.VISIBLE : View.GONE);
        setRightClick(isEditStatus ? "取消" : "编辑", isEditStatus ? cancelListener : editListener);
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            setEditStatus(true);
        }
    };
    private NoDoubleClickListener cancelListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            setEditStatus(false);
            photoPreviewAdapter.setCheckedAll(false);
            cbPhotoSelect.setChecked(false);
        }
    };

    public static void goActivityWithDTO(Activity activity, List<DTOImgInfo> imgInfos) {
        Intent intent = new Intent(activity, PhotoPreviewActivity.class);
        intent.putExtra(Constants.Extra.DTO_IMGS, (Serializable) imgInfos);
        activity.startActivityForResult(intent, Constants.RequestCode.PHOTO_PREVIEW);
    }

    public static void goActivity(Activity activity, List<ImgInfo> photos, FileConfig fileConfig, boolean editable) {
        Intent intent = new Intent(activity, PhotoPreviewActivity.class);
        intent.putExtra(Constants.Extra.DTO_IMGS, (Serializable) photos);
        intent.putExtra(Constants.Extra.FILE_CONFIG, fileConfig);
        intent.putExtra(Constants.Extra.EDITABLE, editable);
        activity.startActivityForResult(intent, Constants.RequestCode.PHOTO_PREVIEW);
    }


    @Override
    public void onModifyPhotosSuccess(List<ImgInfo> photoList) {
        Intent intent = new Intent();
        intent.putExtra(Constants.Extra.PHOTOLIST, (Serializable) photoList);
        intent.putExtra(Constants.Extra.FILETYPE, fileConfig.getFileType());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
