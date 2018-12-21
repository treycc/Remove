package com.jdp.hls.page.otherarea.add;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddOtherEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.OtherArea;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:其它面积-增加
 * Create Time:2018/10/9 0009 上午 10:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherAreaAddActivity extends BaseTitleActivity implements OhterAreaAddContract.View {
    @BindView(R.id.et_otherArea_name)
    EnableEditText etOtherAreaName;
    @BindView(R.id.et_otherArea_price)
    EnableEditText etOtherAreaPrice;
    @BindView(R.id.et_otherArea_area)
    EnableEditText etOtherAreaArea;
    @Inject
    OtherAreaAddPresenter otherAreaAddPresenter;
    private String pcdId;
    private String buildingType;
    private String name;
    private String area;
    private String price;

    @Override
    public void initVariable() {
        pcdId = getIntent().getStringExtra(Constants.Extra.ID);
        buildingType = getIntent().getStringExtra(Constants.Extra.BUILDING_TYPE);
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
        return "增加";
    }

    @Override
    protected void initView() {
        otherAreaAddPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                addOtherArea();
            }
        });

    }

    private void addOtherArea() {
        name = etOtherAreaName.getText().toString().trim();
        area = etOtherAreaArea.getText().toString().trim();
        price = etOtherAreaPrice.getText().toString().trim();
        otherAreaAddPresenter.addOtherArea(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("PCId", pcdId)
                .addFormDataPart("buildingType", buildingType)
                .addFormDataPart("Name", name)
                .addFormDataPart("Price", price)
                .addFormDataPart("Area", area)
                .build());
    }

    @Override
    public void initNet() {

    }


    @Override
    public void onAddOtherAreaSuccess(Integer id) {
        OtherArea otherArea = new OtherArea();
        otherArea.setPrice(Double.valueOf(price));
        otherArea.setName(name);
        otherArea.setArea(Double.valueOf(area));
        otherArea.setId(id);
        otherArea.setPCId(pcdId);
        EventBus.getDefault().post(new AddOtherEvent(otherArea));
        LogUtil.e(TAG,"新增其它面积");
        showSuccessDialogAndFinish();
    }

    public static void goActivity(Context context, String id, String buildingType) {
        Intent intent = new Intent(context, OtherAreaAddActivity.class);
        intent.putExtra(Constants.Extra.ID, id);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        context.startActivity(intent);
    }
}
