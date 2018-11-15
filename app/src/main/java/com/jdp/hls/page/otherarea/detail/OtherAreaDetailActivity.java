package com.jdp.hls.page.otherarea.detail;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.ModifyOtherEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.OtherArea;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:其它面积-详情
 * Create Time:2018/10/9 0009 上午 10:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherAreaDetailActivity extends BaseTitleActivity implements OtherAreaDetailContract.View {
    @BindView(R.id.et_otherArea_name)
    EnableEditText etOtherAreaName;
    @BindView(R.id.et_otherArea_price)
    EnableEditText etOtherAreaPrice;
    @BindView(R.id.et_otherArea_area)
    EnableEditText etOtherAreaArea;
    @Inject
    OtherAreaDetailPresenter otherAreaDetailPresenter;
    private String buildingType;
    private OtherArea otherArea;
    private String name;
    private String area;
    private String price;
    private boolean editable;

    @Override
    public void initVariable() {
        editable = getIntent().getBooleanExtra(Constants.Extra.EDITABLE,true);
        buildingType = getIntent().getStringExtra(Constants.Extra.BUILDING_TYPE);
        otherArea = (OtherArea) getIntent().getSerializableExtra(Constants.Extra.OTHER_AREA);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_otherarea_add;
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
        return "详情";
    }

    @Override
    protected void initView() {
        otherAreaDetailPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        if (editable) {
            setRightClick("保存", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    modifyOtherArea();
                }
            });
        }

        etOtherAreaName.setString(otherArea.getName());
        etOtherAreaArea.setString(otherArea.getArea());
        etOtherAreaPrice.setString(otherArea.getPrice());

        etOtherAreaName.setEnabled(editable);
        etOtherAreaArea.setEnabled(editable);
        etOtherAreaPrice.setEnabled(editable);
    }

    private void modifyOtherArea() {
        name = etOtherAreaName.getText().toString().trim();
        area = etOtherAreaArea.getText().toString().trim();
        price = etOtherAreaPrice.getText().toString().trim();
        otherAreaDetailPresenter.modifyOtherArea(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("PCId", String.valueOf(otherArea.getPCId()))
                .addFormDataPart("buildingType", buildingType)
                .addFormDataPart("Id", String.valueOf(otherArea.getId()))
                .addFormDataPart("Name", name)
                .addFormDataPart("Price", price)
                .addFormDataPart("Area", area)
                .build());
    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Context context, OtherArea otherArea, String buildingType, boolean editable) {
        Intent intent = new Intent(context, OtherAreaDetailActivity.class);
        intent.putExtra(Constants.Extra.OTHER_AREA, otherArea);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        intent.putExtra(Constants.Extra.EDITABLE, editable);
        context.startActivity(intent);
    }

    @Override
    public void onModifyOtherAreaSuccess() {
        otherArea.setPrice(Double.valueOf(price));
        otherArea.setName(name);
        otherArea.setArea(Double.valueOf(area));
        EventBus.getDefault().post(new ModifyOtherEvent(otherArea));
        showSuccessDialogAndFinish();
    }
}
