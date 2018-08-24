package com.jdp.hls.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.rx.RxRe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created with Android Studio.
 * Login: ryan.hoo.j@gmail.com
 * Date: 3/16/16
 * Time: 12:14 AM
 * Desc: BaseFragment
 */
public abstract class BaseFragment extends Fragment {
    protected String TAG = getClass().getSimpleName();
    private ProgressDialog mDialogProgress;
    protected Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariable();
        initCommon();
        initComponent(App.getContext().getAppComponent());
        initView();
        initDate();
        initNet();
    }


    protected abstract void initVariable();

    private void initCommon() {
        mDialogProgress = new ProgressDialog(getActivity());
        mDialogProgress.setCancelable(false);
        mDialogProgress.setCanceledOnTouchOutside(false);
        mDialogProgress.setMessage("加载中");
    }

    /*设置圆形进度条*/
    protected void setProgressShow(boolean ifShow) {
        if (ifShow) {
            mDialogProgress.show();
        } else {
            mDialogProgress.dismiss();
        }
    }

    protected abstract void initComponent(AppComponent appComponent);

    protected abstract void initView();

    protected abstract void initDate();

    protected abstract void initNet();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View mRootView = inflater.inflate(getContentId(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    protected abstract int getContentId();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxRe.getInstance().cancle(this);
        if (mDialogProgress != null && mDialogProgress.isShowing()) {
            mDialogProgress.dismiss();
            mDialogProgress = null;
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public void showEmpty(){}

    public void showError(){}

    public void showNetError(){}

    public void showSuccess(){}
}
