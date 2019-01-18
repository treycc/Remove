package com.jdp.hls.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.ToastUtil;
import com.kingja.wheelview.AbstractWheelTextAdapter;
import com.kingja.wheelview.OnWheelScrollListener;
import com.kingja.wheelview.WheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2019/1/17 0017 上午 10:19
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseWheelListDialog<T> extends CommonDialog implements OnWheelScrollListener {
    private List<T> list = new ArrayList<>();
    private int currentIndex;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.wv_area)
    WheelView wvArea;
    private static final int VisibleItems = 5;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private OnConfirmListener<T> onConfirmListener;

    private TextAdapter<T> textAdapter;

    @OnClick({R.id.tv_cancle, R.id.tv_confirm})
    public void click(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.tv_cancle:

                break;
            case R.id.tv_confirm:
                if (onConfirmListener != null) {
                    if (!clickable(list.get(currentIndex))) {
                        return;
                    }
                    onConfirmListener.onConfirm(list.get(currentIndex));
                }
                break;
        }
    }

    protected abstract boolean clickable(T t);

    BaseWheelListDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_area_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    public void initVariable() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        textAdapter = new TextAdapter<T>(getContext(), list) {
            @Override
            protected CharSequence getItemText(int index) {
                return BaseWheelListDialog.this.getItemText(list, index);
            }
        };
        wvArea.setVisibleItems(VisibleItems);
        wvArea.setViewAdapter(textAdapter);
        wvArea.setCurrentItem(currentIndex);
        wvArea.addScrollingListener(this);
    }


    protected abstract CharSequence getItemText(List<T> list, int index);

    @Override
    public void initNet() {

    }

    private static final int TEXT_SIZE = 15;

    @Override
    public void onScrollingStarted(WheelView wheel) {

    }


    @Override
    public void onScrollingFinished(WheelView wheel) {
        currentIndex = wheel.getCurrentItem();
    }

    abstract class TextAdapter<T> extends AbstractWheelTextAdapter {
        private List<T> textList;

        TextAdapter(Context context, List<T> textList) {
            super(context, R.layout.item_birth, NO_RESOURCE, 0, TEXT_SIZE, TEXT_SIZE);
            this.textList = (textList == null ? new ArrayList<>() : textList);
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            return super.getItem(index, cachedView, parent);
        }

        @Override
        public int getItemsCount() {
            return textList.size();
        }

        @Override
        protected abstract CharSequence getItemText(int index);

        public void setData(List<T> textList) {
            this.textList = (textList == null ? new ArrayList<>() : textList);
        }
    }

    protected void setData(List<T> list, int currentIndex) {
        if (list == null || list.size() == 0) {
            ToastUtil.showText("无数据");
            return;
        }
        this.list = list;
        this.currentIndex = currentIndex;
        if (textAdapter != null) {
            textAdapter.setData(list);
            wvArea.invalidateWheel(true);
            wvArea.setCurrentItem(currentIndex, false);
        }

    }


    public interface OnConfirmListener<T> {
        void onConfirm(T t);
    }

    public void setOnConfirmListener(OnConfirmListener<T> onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }
}
