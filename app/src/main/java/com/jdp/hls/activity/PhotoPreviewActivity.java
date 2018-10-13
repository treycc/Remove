package com.jdp.hls.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DTOImgInfo;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.page.photo.PhotoContract;
import com.jdp.hls.page.photo.PhotoPresenter;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.PermissionsUtil;
import com.zhihu.matisse.Matisse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.MultipartBody;

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

    @OnCheckedChanged({R.id.cb_photo_select})
    public void onChecked(CompoundButton buttonView, boolean isChecked) {
        cbPhotoSelect.setText(isChecked ? "取消全选" : "全选");
        photoPreviewAdapter.setCheckedAll(isChecked);
    }

    @OnClick({R.id.tv_photo_delete, R.id.tv_photo_save})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_photo_delete:
                photoPreviewAdapter.deleteImgs();
                break;
            case R.id.tv_photo_save:
                modifyPhotos();
                break;
        }
    }

    private void modifyPhotos() {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("FileType", "")
                .addFormDataPart("BuildingId", String.valueOf(""))
                .addFormDataPart("BuildingType", String.valueOf(""))
                .addFormDataPart("MasterId", "")
                .addFormDataPart("DeleteFileIDs", "");
        photoPresenter.modifyPhotos(null);
    }

    @OnItemClick({R.id.gv_photo_preview})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (photoPreviewAdapter.isLastItem(position)) {
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
        }
    }

    @Override
    public void initVariable() {
        photos = (List<ImgInfo>) getIntent().getSerializableExtra(Constants.Extra.DTO_IMGS);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_photo_preview;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

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
        photoPreviewAdapter = new PhotoPreviewAdapter(this, photos == null ? new ArrayList<>() : photos);
        gvPhotoPreview.setAdapter(photoPreviewAdapter);
        setEditStatus(false);
    }

    @Override
    protected void initNet() {

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

    public static void goActivity(Activity activity, List<ImgInfo> photos) {
        Intent intent = new Intent(activity, PhotoPreviewActivity.class);
        intent.putExtra(Constants.Extra.DTO_IMGS, (Serializable) photos);
        activity.startActivityForResult(intent, Constants.RequestCode.PHOTO_PREVIEW);
    }

    @Override
    public void onBackPressed() {
        onBack();
    }

    @Override
    protected void onBack() {
        setOperateData();
        super.onBack();
    }

    private void setOperateData() {
        ArrayList<String> deleteIds = photoPreviewAdapter.getDeleteIds();
        List<ImgInfo> photos = photoPreviewAdapter.getDate();
        Intent intent = new Intent();
        intent.putStringArrayListExtra(Constants.Extra.DELETE_IDS, deleteIds);
        intent.putExtra(Constants.Extra.DTO_IMGS, (Serializable) photos);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onModifyPhotosSuccess(List<ImgInfo> imgInfoList) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
