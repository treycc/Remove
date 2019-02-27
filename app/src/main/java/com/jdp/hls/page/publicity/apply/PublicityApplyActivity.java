package com.jdp.hls.page.publicity.apply;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.event.AddPublicityEvent;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.model.entiy.PublicityItem;
import com.jdp.hls.page.publicity.object.PublicityObjectActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.FileUtil;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.AddableRecyclerView;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:公示申请
 * Create Time:2018/9/13 0013 下午 8:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityApplyActivity extends BaseTitleActivity implements PublicityApplyContract.View {
    @BindView(R.id.et_publicityNumber)
    EnableEditText etPublicityNumber;
    @BindView(R.id.tv_publicityCount)
    TextView tvPublicityCount;
    @BindView(R.id.ll_publicity_select)
    LinearLayout llPublicitySelect;
    @BindView(R.id.tv_publicity_realName)
    TextView tvPublicityRealName;
    @BindView(R.id.spinner_publicityType)
    KSpinner spinnerPublicityType;
    @BindView(R.id.spinner_buildingType)
    KSpinner spinnerBuildingType;
    @BindView(R.id.tv_publicity_startDate)
    TextView tvPublicityStartDate;
    @BindView(R.id.ll_publicity_startDate)
    LinearLayout llPublicityStartDate;
    @BindView(R.id.tv_publicity_endDate)
    TextView tvPublicityEndDate;
    @BindView(R.id.ll_publicity_endDate)
    LinearLayout llPublicityEndDate;
    @BindView(R.id.et_des)
    EnableEditText etDes;
    @BindView(R.id.rv_photo)
    AddableRecyclerView rvPhoto;
    private TimePickerDialog startDateSelector;
    private TimePickerDialog endDateSelector;
    private long longStartDate;
    private int publicityType;
    private int buildingType;
    @Inject
    PublicityApplyPresenter publicityApplyPresenter;
    private String buildingIds;
    private List<TDict> publicityTypeList;

    @OnClick({R.id.ll_publicity_select, R.id.ll_publicity_startDate, R.id.ll_publicity_endDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_publicity_select:
                PublicityObjectActivity.goActivity(this, publicityType, buildingType);
                break;
            case R.id.ll_publicity_startDate:
                startDateSelector.show(getSupportFragmentManager(), "startDate");
                break;
            case R.id.ll_publicity_endDate:
                endDateSelector.show(getSupportFragmentManager(), "endDate");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.PUBLICITY_OBJECT:
                    buildingIds = data.getStringExtra(Constants.Extra.BUILDINGIDS);
                    tvPublicityCount.setText(buildingIds.split("#").length + "户");
                    break;
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    rvPhoto.onPhotoCallback(requestCode, resultCode, data);
                    break;
            }
        }
    }

    @Override
    public void initVariable() {
        publicityTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PUBLICITYTYPE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_publicity_apply;
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
        return "申请公示";
    }

    @Override
    protected void initView() {
        publicityApplyPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        tvPublicityRealName.setText(SpSir.getInstance().getRealName());
        setRightClick("保存", new NoDoubleClickListener() {

            @Override
            public void onNoDoubleClick(View v) {
                saveData();
            }
        });
        startDateSelector = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setThemeColor(ContextCompat.getColor(this, R.color.main))
                .setMinMillseconds(System.currentTimeMillis())
                .setWheelItemTextSize(15)
                .setTitleStringId("开始时间")
                .setCallBack((timePickerView, millseconds) -> {
                    longStartDate = millseconds;
                    tvPublicityStartDate.setText(DateUtil.getDateString(millseconds));
                })
                .build();
        endDateSelector = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setMinMillseconds(longStartDate)
                .setWheelItemTextSize(15)
                .setTitleStringId("结束时间")
                .setThemeColor(ContextCompat.getColor(this, R.color.main))
                .setCallBack((timePickerView, millseconds) -> {
                    tvPublicityEndDate.setText(DateUtil.getDateString(millseconds));
                })
                .build();
        spinnerPublicityType.setDicts(publicityTypeList, typeId -> {
            publicityType = typeId;
        });
        publicityType=spinnerPublicityType.getDefaultTypeId();

        spinnerBuildingType.attachDataSource(Arrays.asList("住宅", "企业"));
        spinnerBuildingType.addOnItemClickListener((parent, view, position, id) -> {
            buildingType = position;
        });
    }

    private void saveData() {
        String batchName = etPublicityNumber.getText().toString().trim();
        String descriptiton = etDes.getText().toString().trim();
        String startDate = tvPublicityStartDate.getText().toString().trim();
        String endDate = tvPublicityEndDate.getText().toString().trim();
        if (!CheckUtil.checkEmpty(buildingIds, "请选择公示对象")) {
            return;
        }
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("ProjectId", SpSir.getInstance().getProjectId())
                .addFormDataPart("BatchName", batchName)
                .addFormDataPart("PubType", String.valueOf(publicityType))
                .addFormDataPart("BuildingType", String.valueOf(buildingType))
                .addFormDataPart("BuildingIDs", buildingIds)
                .addFormDataPart("StartDate", startDate)
                .addFormDataPart("EndDate", endDate)
                .addFormDataPart("Descriptiton", descriptiton);
        List<ImgInfo> imgInfos = rvPhoto.getDate();
        for (int i = 0; i < imgInfos.size(); i++) {
            Uri uri = imgInfos.get(i).getUri();
            if (uri != null) {
                File photoFile = FileUtil.getFileByUri(uri, this);
                bodyBuilder.addFormDataPart("Files" + i, photoFile.getName(), RequestBody.create(MediaType
                        .parse("image/*"), photoFile));
            }
        }
        publicityApplyPresenter.applyPublicity(bodyBuilder.build());
    }

    @Override
    public void initNet() {

    }

    @Override
    public void onApplyPublicitySuccess(PublicityItem publicityItem) {
        EventBus.getDefault().post(new AddPublicityEvent(publicityItem));
        showSuccessDialogAndFinish();
    }

}
