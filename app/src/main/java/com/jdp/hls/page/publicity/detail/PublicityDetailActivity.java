package com.jdp.hls.page.publicity.detail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.ModifyPublicityEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.model.entiy.PublicityDetail;
import com.jdp.hls.model.entiy.PublicityItem;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.FileUtil;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.AddableRecyclerView;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:公示详情
 * Create Time:2018/9/18 0018 下午 1:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityDetailActivity extends BaseTitleActivity implements PublicityDetailContract.View {

    @BindView(R.id.et_publicity_number)
    EnableEditText etPublicityNumber;
    @BindView(R.id.tv_publicity_count)
    StringTextView tvPublicityCount;
    @BindView(R.id.ll_publicity_select)
    LinearLayout llPublicitySelect;
    @BindView(R.id.tv_publicity_realName)
    StringTextView tvPublicityRealName;
    @BindView(R.id.tv_publicity_startDate)
    StringTextView tvPublicityStartDate;
    @BindView(R.id.ll_publicity_startDate)
    LinearLayout llPublicityStartDate;
    @BindView(R.id.tv_publicity_endDate)
    StringTextView tvPublicityEndDate;
    @BindView(R.id.ll_publicity_endDate)
    LinearLayout llPublicityEndDate;
    @BindView(R.id.et_des)
    EnableEditText etDes;
    @BindView(R.id.tv_publicityType)
    StringTextView tvPublicityType;
    @BindView(R.id.tv_buildingType)
    StringTextView tvBuildingType;
    @BindView(R.id.rv_airphotoPhoto)
    AddableRecyclerView rvPhoto;
    private int pubId;
    @Inject
    PublicityDetailPresenter publicityDetailPresenter;
    private int pubStatus;
    private int position;
    private String batchName;
    private String des;


    @Override
    public void initVariable() {
        pubId = getIntent().getIntExtra(Constants.Extra.PUBID, 0);
        pubStatus = getIntent().getIntExtra(Constants.Extra.PUB_STATUS, 0);
        position = getIntent().getIntExtra(Constants.Extra.POSITION, 0);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_publicity_detail;
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
        return "公示信息";
    }

    @Override
    protected void initView() {
        publicityDetailPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        tvPublicityRealName.setString(SpSir.getInstance().getRealName());
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                saveData();
            }
        });

    }

    private void saveData() {
        batchName = etPublicityNumber.getText().toString().trim();
        des = etDes.getText().toString().trim();
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("PubId", String.valueOf(pubId))
                .addFormDataPart("BatchName", batchName)
                .addFormDataPart("Descriptiton", des)
                .addFormDataPart("PubStatus", String.valueOf(pubStatus))
                .addFormDataPart("DeleteFileIDs", rvPhoto.getDeleteImgIds());
        List<ImgInfo> imgInfos = rvPhoto.getDate();
        for (int i = 0; i < imgInfos.size(); i++) {
            Uri uri = imgInfos.get(i).getUri();
            if (uri != null) {
                File photoFile = FileUtil.getFileByUri(uri, this);
                bodyBuilder.addFormDataPart("Files" + i, photoFile.getName(), RequestBody.create(MediaType
                        .parse("image/*"), photoFile));
            }
        }
        publicityDetailPresenter.modifyPublicity(bodyBuilder.build());
    }

    @Override
    public void initNet() {
        publicityDetailPresenter.getPublicityDetail(pubId);
    }

    public static void goActivity(Fragment context, int pubId, int pubStatus, int position) {
        Intent intent = new Intent(context.getActivity(), PublicityDetailActivity.class);
        intent.putExtra(Constants.Extra.PUBID, pubId);
        intent.putExtra(Constants.Extra.PUB_STATUS, pubStatus);
        intent.putExtra(Constants.Extra.POSITION, position);
        context.startActivityForResult(intent, Constants.RequestCode.PUBLICITY_DETAIL);
    }
    public static void goActivity(Activity context, int pubId, int pubStatus, int position) {
        Intent intent = new Intent(context, PublicityDetailActivity.class);
        intent.putExtra(Constants.Extra.PUBID, pubId);
        intent.putExtra(Constants.Extra.PUB_STATUS, pubStatus);
        intent.putExtra(Constants.Extra.POSITION, position);
        context.startActivity(intent);
    }

    @Override
    public void onGetPublicityDetailSuccess(PublicityDetail publicityDetail) {
        etPublicityNumber.setString(publicityDetail.getBatchName());
        tvPublicityCount.setString(publicityDetail.getTotalQuantity());
        tvPublicityStartDate.setString(DateUtil.getShortDate(publicityDetail.getStartDate()));
        tvPublicityEndDate.setString(DateUtil.getShortDate(publicityDetail.getEndDate()));
        tvBuildingType.setString(publicityDetail.getBuildingType() == Status.BuildingType.PERSONAL ? "住宅" : "企业");
        tvPublicityType.setString(publicityDetail.getPubType() == Status.PublicityType.SURVEY ? "调查公示" : "认定公示");
        etDes.setString(publicityDetail.getDescriptiton());
        rvPhoto.setDate(publicityDetail.getFiles(), publicityDetail.isAllowEdit());
    }

    @Override
    public void onModifyPublicitySuccess(PublicityItem publicityItem) {
        EventBus.getDefault().post(new ModifyPublicityEvent(publicityItem));
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
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    rvPhoto.onPhotoCallback(requestCode, resultCode, data);
                    break;
                default:
                    break;
            }
        }
    }
}
