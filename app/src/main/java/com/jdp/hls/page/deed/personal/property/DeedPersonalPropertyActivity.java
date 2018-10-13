package com.jdp.hls.page.deed.personal.property;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.activity.PhotoPreviewActivity;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedPersonalProperty;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.PreviewRecyclerView;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:产权证-个人
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedPersonalPropertyActivity extends BaseTitleActivity implements DeedPersonalPropertyContract.View {

    @BindView(R.id.et_property_num)
    EnableEditText etPropertyNum;
    @BindView(R.id.et_property_totalArea)
    EnableEditText etPropertyTotalArea;
    @BindView(R.id.et_property_shareArea)
    EnableEditText etPropertyShareArea;
    @BindView(R.id.spinner_property_structure)
    KSpinner spinnerPropertyStructure;
    @BindView(R.id.spinner_property_use)
    KSpinner spinnerPropertyUse;
    @BindView(R.id.et_property_address)
    EnableEditText etPropertyAddress;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvImg;
    @BindView(R.id.ll_photo_preview)
    LinearLayout llImgPreview;
    private String houdeId;
    private boolean isAdd;
    private List<TDict> propertyUseList;
    private List<TDict> propertyStructureList;
    private int propertyUse;
    private int propertyStructure;
    private List<ImgInfo> imgInfos;
    @Inject
    DeedPersonalPropertyPresenter deedPersonalPropertyPresenter;
    private boolean allowEdit;

    @OnClick({R.id.ll_photo_preview})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_photo_preview:
//                PhotoPreviewActivity.goActivityWithDTO(this, rvImg.getDTOImgInfo());
                break;
        }
    }

    @Override
    public void initVariable() {
        isAdd = getIntent().getBooleanExtra("isAdd", false);
        houdeId = getIntent().getStringExtra("houdeId");

        propertyUseList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_USE);
        propertyStructureList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_STRUCTURE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_personal_property;
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
        return "产权证";
    }

    @Override
    protected void initView() {
        deedPersonalPropertyPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        rvImg.create();
        spinnerPropertyUse.setDicts(propertyUseList, typeId -> {
            propertyUse = typeId;
        });
        propertyUse=spinnerPropertyUse.getDefaultTypeId();
        spinnerPropertyStructure.setDicts(propertyStructureList, typeId -> {
            propertyStructure = typeId;
        });
        propertyStructure=spinnerPropertyStructure.getDefaultTypeId();

    }



    @Override
    protected void initNet() {
        //1.增加 不做任何操作，点保存增加
        //2.修改 先获取接口，然后可编辑，点保存修改
        //3.查看 先获取接口，然后不可编辑，不出现按钮
        if (isAdd) {
            setRightClick("保存", addListener);
        } else {
            //获取接口
            deedPersonalPropertyPresenter.getDeedPersonalProperty(houdeId);
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedPersonalPropertyPresenter.modifyDeedPersonalProperty(requestBody);
        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedPersonalPropertyPresenter.addDeedPersonalProperty(requestBody);
        }
    };

    @NonNull
    private RequestBody getRequestBody() {
        String certNum = etPropertyNum.getText().toString().trim();
        String totalArea = etPropertyTotalArea.getText().toString().trim();
        String shareArea = etPropertyShareArea.getText().toString().trim();
        String address = etPropertyAddress.getText().toString().trim();
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", houdeId)
                .addFormDataPart("PropertyUseTypeId", String.valueOf(propertyUse))
                .addFormDataPart("StructureTypeId", String.valueOf(propertyStructure))
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("TotalArea", totalArea)
                .addFormDataPart("ShareArea", shareArea)
                .addFormDataPart("Address", address);
        return bodyBuilder.build();
    }



    public static void goActivity(Context context, String houdeId, boolean isAdd) {
        Intent intent = new Intent(context, DeedPersonalPropertyActivity.class);
        intent.putExtra("houdeId", houdeId);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }

    @Override
    public void onGetDeedPersonalPropertySuccess(DeedPersonalProperty deedPersonalProperty) {
        etPropertyNum.setText(deedPersonalProperty.getCertNum());
        etPropertyTotalArea.setText(String.valueOf(deedPersonalProperty.getTotalArea()));
        etPropertyShareArea.setText(String.valueOf(deedPersonalProperty.getShareArea()));
        etPropertyAddress.setText(deedPersonalProperty.getAddress());
        propertyUse = deedPersonalProperty.getPropertyUseTypeId();
        propertyStructure = deedPersonalProperty.getStructureTypeId();
        spinnerPropertyUse.setSelectItem(propertyUse);
        spinnerPropertyStructure.setSelectItem(propertyStructure);
        imgInfos = deedPersonalProperty.getFiles();
        if (imgInfos != null) {
            rvImg.setData(imgInfos);
        }
        allowEdit = deedPersonalProperty.isAllowEdit();
        setEditable(allowEdit);
    }

    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        } else {
            //全部禁用
        }
    }

    @Override
    public void onAddDeedPersonalPropertySuccess() {
        ToastUtil.showText("增加成功");

    }

    @Override
    public void onModifyDeedPersonalPropertySuccess() {
        ToastUtil.showText("修改成功");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        rvImg.onActivityResult(requestCode, resultCode, data);
    }
}
