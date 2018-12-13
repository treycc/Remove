package com.jdp.hls.page.admin.contrast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ProjectFacade;
import com.jdp.hls.other.file.FileConfig;
import com.jdp.hls.page.supervise.project.contrast.ProjectContrastContract;
import com.jdp.hls.page.supervise.project.contrast.ProjectContrastPresenter;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/12/12 0012 下午 7:48
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectContrastDetailActivity extends BaseTitleActivity implements ProjectContrastContract.View,
        ProjectContrastDetailContract.View {

    @BindView(R.id.rv_photo_preview_old)
    PreviewRecyclerView rvPhotoPreviewOld;
    @BindView(R.id.et_oldVrUrl)
    EnableEditText etOldVrUrl;
    @BindView(R.id.rv_photo_preview_new)
    PreviewRecyclerView rvPhotoPreviewNew;
    @BindView(R.id.et_newVrUrl)
    EnableEditText etNewVrUrl;
    private String projectId;
    @Inject
    ProjectContrastPresenter projectContrastPresenter;

    @Inject
    ProjectContrastDetailPresenter projectContrastDetailPresenter;

    @Override
    public void initVariable() {
        projectId = getIntent().getStringExtra(Constants.Extra.PROJECTID);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_contrast_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        projectContrastPresenter.attachView(this);
        projectContrastDetailPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "新旧对比";
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        setRightClick("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newVrUrl = etNewVrUrl.getText().toString().trim();
                String oldVrUrl = etOldVrUrl.getText().toString().trim();
                if (CheckUtil.checkUrl(oldVrUrl, "旧貌VR地址格式有误") && CheckUtil.checkUrl(newVrUrl, "新貌VR地址格式有误")) {
                    projectContrastDetailPresenter.saveVrInfo(new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("ProjectId", projectId)
                            .addFormDataPart("OldVRUrl", oldVrUrl)
                            .addFormDataPart("NewVRUrl", newVrUrl)
                            .build());
                }

            }
        });

    }

    @Override
    public void initNet() {
        projectContrastPresenter.getProjectPhoto(projectId);
    }

    public static void goActivity(Context context, String projectId) {
        Intent intent = new Intent(context, ProjectContrastDetailActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        context.startActivity(intent);
    }

    @Override
    public void onGetProjectPhotoSuccess(ProjectFacade projectFacade) {
        rvPhotoPreviewOld.setData(projectFacade.getOldFiles(), new FileConfig(Status.FileType
                .PROJECT_OLDLOOK, projectId, String.valueOf(Status.BuildingType.PERSONAL)), true);
        rvPhotoPreviewNew.setData(projectFacade.getNewFiles(), new FileConfig(Status.FileType
                .PROJECT_NEWLOOK, projectId, String.valueOf(Status.BuildingType.PERSONAL)), true);
        etNewVrUrl.setString(projectFacade.getNewVRUrl());
        etOldVrUrl.setString(projectFacade.getOldVRUrl());
    }

    @Override
    public void onSaveVrInfoSuccess() {
        showSuccessDialogAndFinish();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.PHOTO_PREVIEW:
                    rvPhotoPreviewNew.onActivityResult(requestCode, data);
                    rvPhotoPreviewOld.onActivityResult(requestCode, data);
                    break;
                default:
                    break;
            }
        }
    }
}
