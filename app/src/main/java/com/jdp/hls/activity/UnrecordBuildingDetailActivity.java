package com.jdp.hls.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddUnrecordBuildingEvent;
import com.jdp.hls.event.ModifyUnrecordBuildingEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.UnRecordBuilding;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/10/11 0011 上午 9:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UnrecordBuildingDetailActivity extends BaseTitleActivity {
    @BindView(R.id.et_unrecording_position)
    EnableEditText etUnrecordingPosition;
    @BindView(R.id.et_unrecording_layer)
    EnableEditText etUnrecordingLayer;
    @BindView(R.id.et_unrecording_area)
    EnableEditText etUnrecordingArea;
    @BindView(R.id.et_unrecording_pic84)
    EnableEditText etUnrecordingPic84;
    @BindView(R.id.et_unrecording_pic94)
    EnableEditText etUnrecordingPic94;
    @BindView(R.id.et_unrecording_pic2000)
    EnableEditText etUnrecordingPic2000;
    private UnRecordBuilding unRecordBuilding;
    private boolean editable;

    @Override
    public void initVariable() {
        editable = getIntent().getBooleanExtra(Constants.Extra.EDITABLE,true);
        unRecordBuilding = (UnRecordBuilding) getIntent().getSerializableExtra(Constants.Extra.UNRECORD_BUILDING);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_unrecordbuilding_add;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "增加未登记建筑信息";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


        //修改
        if (unRecordBuilding != null) {
            etUnrecordingPosition.setString(unRecordBuilding.getPosition());
            etUnrecordingLayer.setString(unRecordBuilding.getLayer());
            etUnrecordingArea.setString(unRecordBuilding.getArea());
            etUnrecordingPic84.setString(unRecordBuilding.getPic84());
            etUnrecordingPic94.setString(unRecordBuilding.getPic94());
            etUnrecordingPic2000.setString(unRecordBuilding.getPic2000());
            if (editable) {
                setRightClick("保存", new NoDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {
                        saveUnrecordBuilding();
                    }
                });

            }
            etUnrecordingPosition.setEnabled(editable);
            etUnrecordingLayer.setEnabled(editable);
            etUnrecordingArea.setEnabled(editable);
            etUnrecordingPic84.setEnabled(editable);
            etUnrecordingPic94.setEnabled(editable);
            etUnrecordingPic2000.setEnabled(editable);
        }
    }

    private void saveUnrecordBuilding() {
        String position = etUnrecordingPosition.getText().toString().trim();
        String laye = etUnrecordingLayer.getText().toString().trim();
        String area = etUnrecordingArea.getText().toString().trim();
        String pic84 = etUnrecordingPic84.getText().toString().trim();
        String pic94 = etUnrecordingPic94.getText().toString().trim();
        String pic2000 = etUnrecordingPic2000.getText().toString().trim();
        if (CheckUtil.checkEmpty(position, "请输入部位") && CheckUtil.checkEmpty(laye, "请输入层次") && CheckUtil
                .checkEmpty(area, "请输入面积")) {
            UnRecordBuilding building = new UnRecordBuilding();
            building.setEdited(true);
            building.setPosition(position);
            building.setLayer(Integer.valueOf(laye));
            building.setArea(Double.valueOf(area));
            building.setPic84(pic84);
            building.setPic94(pic94);
            building.setPic2000(pic2000);
            if (unRecordBuilding != null) {
                building.setAirCheckId(unRecordBuilding.getAirCheckId());
                building.setId(unRecordBuilding.getId());
                EventBus.getDefault().post(new ModifyUnrecordBuildingEvent(building));
            } else {
                EventBus.getDefault().post(new AddUnrecordBuildingEvent(building));
            }
            finish();
        }

    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Context context, UnRecordBuilding unRecordBuilding, boolean editable) {
        Intent intent = new Intent(context, UnrecordBuildingDetailActivity.class);
        intent.putExtra(Constants.Extra.UNRECORD_BUILDING, unRecordBuilding);
        intent.putExtra(Constants.Extra.EDITABLE, editable);
        context.startActivity(intent);
    }

}
