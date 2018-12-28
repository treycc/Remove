package com.jdp.hls.page.supervise.project.contrast;

import android.view.View;
import android.widget.RelativeLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ProjectFacade;
import com.jdp.hls.page.otherarea.list.OtherAreaListActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.PreviewRecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/12/11 0011 下午 4:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectContrastActivity extends BaseTitleActivity implements ProjectContrastContract.View {
    @Inject
    ProjectContrastPresenter projectContrastPresenter;
    @BindView(R.id.rv_photo_preview_old)
    PreviewRecyclerView rvPhotoPreviewOld;
    @BindView(R.id.rv_photo_preview_new)
    PreviewRecyclerView rvPhotoPreviewNew;

    private String oldVrUrl;
    private String newVrUrl;

    @OnClick({R.id.rl_old_vr, R.id.rl_new_vr})
    public void rl_protocol_otherArea(View view) {
        switch (view.getId()) {
            case R.id.rl_old_vr:
                if (CheckUtil.checkEmpty(oldVrUrl, "暂无全景VR")) {
                    VRDetailActivity.goActivity(this, oldVrUrl, "全景VR");
                    break;
                }
            case R.id.rl_new_vr:
                if (CheckUtil.checkEmpty(newVrUrl, "暂无全景VR")) {
                    VRDetailActivity.goActivity(this, newVrUrl, "全景VR");
                    break;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_contrast;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        projectContrastPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "改造前后对比";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initNet() {
        projectContrastPresenter.getProjectPhoto(SpSir.getInstance().getProjectId());
    }

    @Override
    public void onGetProjectPhotoSuccess(ProjectFacade projectFacade) {
        rvPhotoPreviewOld.setData(projectFacade.getOldFiles());
        rvPhotoPreviewNew.setData(projectFacade.getNewFiles());
        oldVrUrl = projectFacade.getOldVRUrl();
        newVrUrl = projectFacade.getNewVRUrl();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
