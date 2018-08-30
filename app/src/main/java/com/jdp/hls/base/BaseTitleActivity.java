package com.jdp.hls.base;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.callback.EmptyCallback;
import com.jdp.hls.callback.ErrorCallback;
import com.jdp.hls.callback.LoadingCallback;
import com.jdp.hls.injector.component.AppComponent;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description：TODO
 * Create Time：2017/3/20 14:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseTitleActivity extends BaseActivity {

    protected View rootView;
    private TextView tvTitleTitle;
    private Unbinder bind;
    protected LoadService mBaseLoadService;

    @Override
    public View getContentId() {
        rootView = View.inflate(this, R.layout.activity_title, null);
        FrameLayout flContent = rootView.findViewById(R.id.fl_content);
        RelativeLayout rl_title_root = rootView.findViewById(R.id.rl_title_root);
        tvTitleTitle = rootView.findViewById(R.id.tv_title_title);
        LinearLayout llTitleBack = rootView.findViewById(R.id.ll_title_back);
        tvTitleTitle.setText(getContentTitle() == null ? "" : getContentTitle());
        llTitleBack.setOnClickListener(v -> onBack());
        View content = View.inflate(this, getContentView(), null);
        if (content != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            flContent.addView(content, params);
            bind = ButterKnife.bind(this, rootView);
            // register after ButterKnife.bind()
            if (ifRegisterLoadSir()) {
                mBaseLoadService = LoadSir.getDefault().register(content, new Callback.OnReloadListener() {
                    @Override
                    public void onReload(View v) {
                        onNetReload(v);
                    }
                });
            }

        }
        if (ifHideTitle()) {
            rl_title_root.setVisibility(View.GONE);
        }
        if (ifHideBack()) {
            llTitleBack.setVisibility(View.GONE);
        }
        return rootView;
    }

    protected boolean ifRegisterLoadSir() {
        return false;
    }

    @Override
    public void showLoadingCallback() {
        mBaseLoadService.showCallback(LoadingCallback.class);
    }
    @Override
    public void showEmptyCallback(){
        mBaseLoadService.showCallback(EmptyCallback.class);
    }
    @Override
    public void showErrorCallback(){
        mBaseLoadService.showCallback(ErrorCallback.class);
    }
    @Override
    public void showSuccessCallback(){
        mBaseLoadService.showSuccess();
    }
    private void onNetReload(View v) {
        initNet();
    }


    protected void onBack() {
        finish();
    }

    protected boolean ifHideBack() {
        return false;
    }

    protected boolean ifHideTitle() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }

    public void setContentTitle(String title) {
        tvTitleTitle.setText(title);
    }

    public void setMenuRes(int resId, View.OnClickListener onClickListener) {
        LinearLayout llTitleMenu = rootView.findViewById(R.id.ll_title_menu);
        llTitleMenu.setVisibility(View.VISIBLE);
        llTitleMenu.setOnClickListener(onClickListener);
        ImageView ivMenu = rootView.findViewById(R.id.iv_menu);
        ivMenu.setBackgroundResource(resId);
    }

    public void setRightClick(String rightText, View.OnClickListener onClickListener) {
        TextView tv_right_text = rootView.findViewById(R.id.tv_right_text);
        tv_right_text.setText(rightText);
        tv_right_text.setVisibility(View.VISIBLE);
        tv_right_text.setOnClickListener(onClickListener);
    }

    public void hideRightClick() {
        TextView tv_right_text = rootView.findViewById(R.id.tv_right_text);
        tv_right_text.setVisibility(View.GONE);
    }

    public void setRightClickable(boolean clickable) {
        TextView tv_right_text = rootView.findViewById(R.id.tv_right_text);
        tv_right_text.setEnabled(clickable);
        tv_right_text.setTextColor(clickable?ContextCompat.getColor(this,R.color.main):ContextCompat.getColor(this,R.color.c_9));
    }

    @Override
    public abstract void initVariable();

    protected abstract int getContentView();

    @Override
    protected abstract void initComponent(AppComponent appComponent);

    protected abstract String getContentTitle();

    @Override
    protected abstract void initView();

    @Override
    protected abstract void initData();

    @Override
    protected abstract void initNet();


}
