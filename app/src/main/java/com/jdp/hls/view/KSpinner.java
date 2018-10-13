package com.jdp.hls.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.adapter.KSpinnerAdapter;
import com.jdp.hls.greendaobean.TDict;

import org.angmarch.views.NiceSpinner;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/14 0014 下午 5:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class KSpinner extends NiceSpinner {
    public static int DEFAULT_PADDING = 12;
    public static int DEFAULT_BACKGROUND_COLOR = 0x00000000;
    private List<TDict> datas;

    public KSpinner(Context context) {
        this(context, null);
    }

    public KSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setPadding(dp2px(DEFAULT_PADDING), dp2px(DEFAULT_PADDING), DEFAULT_PADDING, dp2px(DEFAULT_PADDING));
    }

    private int dp2px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getContext().getResources()
                .getDisplayMetrics());
    }

    public void setDicts(List<TDict> datas, OnSpinnerSelectedListener onSpinnerSelectedListener) {
        this.datas = datas;
        KSpinnerAdapter kSpinnerAdapter = new KSpinnerAdapter(getContext(), datas);
        setAdapter(kSpinnerAdapter);
        addOnItemClickListener((parent, view, position, id) -> {
            setText(datas.get(position).getTypeName());
            if (onSpinnerSelectedListener != null) {
                onSpinnerSelectedListener.onSpinnerSelected(datas.get(position).getTypeId());
            }
        });
        setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setText(datas.get(position).getTypeName());
                if (onSpinnerSelectedListener != null) {
                    onSpinnerSelectedListener.onSpinnerSelected(datas.get(position).getTypeId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setDictsItem(List<TDict> datas, OnSpinnerItemSelectedListener onSpinnerItemSelectedListener) {
        this.datas = datas;
        KSpinnerAdapter kSpinnerAdapter = new KSpinnerAdapter(getContext(), datas);
        setAdapter(kSpinnerAdapter);
        addOnItemClickListener((parent, view, position, id) -> {
            setText(datas.get(position).getTypeName());
            if (onSpinnerItemSelectedListener != null) {
                onSpinnerItemSelectedListener.onSpinnerItemSelected(datas.get(position));
            }
        });
        setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setText(datas.get(position).getTypeName());
                if (onSpinnerItemSelectedListener != null) {
                    onSpinnerItemSelectedListener.onSpinnerItemSelected(datas.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public int getDefaultTypeId() {
        int typeId = 1;
        if (datas != null && datas.size() > 0) {
            typeId = datas.get(0).getTypeId();
        }
        return typeId;
    }

    public void setBooleanDate(List<String> datas, OnSpinnerBooleanSelectedListener onSpinnerBooleanSelectedListener) {
        attachDataSource(datas);
        addOnItemClickListener((parent, view, position, id) -> {
            if (onSpinnerBooleanSelectedListener != null) {
                onSpinnerBooleanSelectedListener.onBooleanSelected(position == 0);
            }
        });
    }

    public void setSelectItem(int typeId) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getTypeId() == typeId) {
                setSelectedIndex(i);
                return;
            }
        }
    }

    public interface OnSpinnerSelectedListener {
        void onSpinnerSelected(int typeId);
    }
    public interface OnSpinnerItemSelectedListener {
        void onSpinnerItemSelected(TDict dict);
    }

    public interface OnSpinnerBooleanSelectedListener {
        void onBooleanSelected(boolean selected);
    }

    public void disable() {
        hideArrow();
        setEnabled(false);
    }
}
