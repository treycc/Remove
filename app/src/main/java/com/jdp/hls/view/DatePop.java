package com.jdp.hls.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.kingja.popwindowsir.BasePop;

/**
 * Description:TODO
 * Create Time:2018/10/13 0013 下午 1:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DatePop extends BasePop {

    private final AppCompatActivity activity;
    private long startMillseconds = -1;
    private long endMillseconds = -1;
    private OnDateSelectedListener onDateSelectedListener;

    public DatePop(Context context) {
        super(context);
        activity = (AppCompatActivity) context;
    }


    @Override
    protected void initPop() {

    }

    @Override
    protected void initView(View contentView) {
        TextView tv_pop_startDate = contentView.findViewById(R.id.tv_pop_startDate);
        TextView tv_pop_endDate = contentView.findViewById(R.id.tv_pop_endDate);
        ImageView iv_pop_startDate = contentView.findViewById(R.id.iv_pop_startDate);
        ImageView iv_pop_endDate = contentView.findViewById(R.id.iv_pop_endDate);
        ImageView iv_pop_startDateLimit = contentView.findViewById(R.id.iv_pop_startDateLimit);
        ImageView iv_pop_endDateLimit = contentView.findViewById(R.id.iv_pop_endDateLimit);
        iv_pop_startDateLimit.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                tv_pop_startDate.setText("无限制");
                startMillseconds = -1;
                nodifyDate();
            }
        });
        iv_pop_endDateLimit.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                tv_pop_endDate.setText("无限制");
                endMillseconds = -1;
                nodifyDate();
            }
        });
        iv_pop_startDate.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH_DAY)
                        .setThemeColor(ContextCompat.getColor(context, R.color.main))
                        .setWheelItemTextSize(15)
                        .setTitleStringId("请选择开始时间")
                        .setCallBack((timePickerView, millseconds) -> {
                            startMillseconds = millseconds;
                            tv_pop_startDate.setText(DateUtil.getDateString(millseconds));
                            nodifyDate();
                        })
                        .build().show(activity.getSupportFragmentManager(), String.valueOf(iv_pop_startDate.hashCode
                        ()));
            }
        });
        iv_pop_endDate.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH_DAY)
                        .setThemeColor(ContextCompat.getColor(context, R.color.main))
                        .setWheelItemTextSize(15)
                        .setTitleStringId("请选择结束时间")
                        .setCallBack((timePickerView, millseconds) -> {
                            endMillseconds = millseconds;
                            tv_pop_endDate.setText(DateUtil.getDateString(millseconds));
                            nodifyDate();
                        })
                        .build().show(activity.getSupportFragmentManager(), String.valueOf(iv_pop_endDate.hashCode()));
            }
        });
    }

    private void nodifyDate() {
        if (onDateSelectedListener != null) {
            onDateSelectedListener.onDateSelected(startMillseconds, endMillseconds);
        }
    }

    @Override
    protected View getLayoutView() {
        return View.inflate(context, R.layout.pop_date, null);
    }

    public interface OnDateSelectedListener {
        void onDateSelected(long startDate, long endDate);
    }

    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        this.onDateSelectedListener = onDateSelectedListener;
    }
}
