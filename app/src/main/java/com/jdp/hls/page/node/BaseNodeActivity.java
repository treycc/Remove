package com.jdp.hls.page.node;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/27 0027 上午 11:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseNodeActivity extends BaseTitleActivity {
    protected String mBuildingId;
    protected boolean allowEdit;

    @Override
    public void initVariable() {
        mBuildingId = getIntent().getStringExtra(Constants.Extra.BUILDINGID);
    }

    @Override
    protected abstract int getContentView();

    @Override
    protected abstract void initComponent(AppComponent appComponent);

    @Override
    protected abstract String getContentTitle();

    @Override
    protected abstract void initView();

    @Override
    protected abstract void initData();

    @Override
    protected abstract void initNet();

    public static void goActivity(Context context, Class<? extends BaseNodeActivity> clazz, String buildingId) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(Constants.Extra.BUILDINGID, buildingId);
        context.startActivity(intent);
    }

    protected void setEditable(boolean allowEdit) {
        this.allowEdit = allowEdit;
        onUiEditable(allowEdit);
        if (allowEdit) {
            setRightClick("保存", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    onSaveDate();
                }
            });
        }

    }


    protected void setDateSelector(ImageView ivDate, TextView tvDate, boolean allowEdit) {
        ivDate.setVisibility(allowEdit ? View.VISIBLE : View.GONE);
        if (!allowEdit) {
            return;
        }
        ivDate.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH_DAY)
                        .setThemeColor(ContextCompat.getColor(BaseNodeActivity.this, R.color.main))
                        .setWheelItemTextSize(15)
                        .setTitleStringId("请选择时间")
                        .setCallBack((timePickerView, millseconds) -> {
                            tvDate.setText(DateUtil.getDateString(millseconds));
                        })
                        .build().show(getSupportFragmentManager(), String.valueOf(ivDate.hashCode()));
            }
        });
    }

    public void setPhotoPreview(List<ImgInfo> photos, PreviewRecyclerView previewRecyclerView) {
        if (photos != null && photos.size() > 0) {
            previewRecyclerView.setData(photos);
        }
    }



    protected abstract void onUiEditable(boolean allowEdit);

    protected abstract void onSaveDate();

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }
}
