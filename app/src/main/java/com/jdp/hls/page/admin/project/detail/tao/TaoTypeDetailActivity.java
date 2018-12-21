package com.jdp.hls.page.admin.project.detail.tao;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.TaoTypeAddEvent;
import com.jdp.hls.event.TaoTypeModifyEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.TaoType;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/12/17 0017 下午 3:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TaoTypeDetailActivity extends BaseTitleActivity {

    @BindView(R.id.et_name)
    EnableEditText etName;
    @BindView(R.id.et_area)
    EnableEditText etArea;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    private TaoType taoType;

    @Override
    public void initVariable() {
        taoType = (TaoType) getIntent().getSerializableExtra(Constants.Extra.TaoType);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_taotype_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "套型";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if (taoType != null) {
            etName.setString(taoType.getPatternName());
            etArea.setString(taoType.getArea());
            etRemark.setString(taoType.getRemark());
        }

        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                String name = etName.getText().toString().trim();
                String area = etArea.getText().toString().trim();
                String remark = etRemark.getText().toString().trim();
                if (CheckUtil.checkEmpty(name, "请输入名称") && CheckUtil.checkEmpty(name, "请输入面积")) {
                    TaoType eventItem = new TaoType();
                    eventItem.setPatternName(name);
                    eventItem.setArea(area);
                    eventItem.setRemark(remark);
                    if (taoType != null) {
                        //修改
                        eventItem.setId(taoType.getId());
                        EventBus.getDefault().post(new TaoTypeModifyEvent(eventItem));
                    } else {
                        //新增
                        EventBus.getDefault().post(new TaoTypeAddEvent(eventItem));
                    }
                    showSuccessDialogAndFinish();
                }
            }
        });

    }

    @Override
    public void initNet() {

    }

    public static void goActivity(Context context, TaoType taoType) {
        Intent intent = new Intent(context, TaoTypeDetailActivity.class);
        intent.putExtra(Constants.Extra.TaoType, taoType);
        context.startActivity(intent);
    }
}
