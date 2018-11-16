package com.jdp.hls.page.module;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Module;
import com.jdp.hls.model.entiy.ModuleDetail;
import com.jdp.hls.page.admin.project.list.ProjectListAdminActivity;
import com.jdp.hls.page.employee.list.EmployeeListActivity;
import com.jdp.hls.page.levy.LevyActivity;
import com.jdp.hls.page.message.notification.NotificationActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:业务征收平台
 * Create Time:2018/9/5 0005 上午 9:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class HomeFragment extends BaseFragment implements ModuleContract.View {
    @BindView(R.id.tv_title)
    StringTextView tvTitle;
    @BindView(R.id.iv_imageUrl)
    ImageView ivImageUrl;
    @BindView(R.id.flv)
    ListView flv;
    private String routeId;

    @Inject
    ModulePresenter modulePresenter;


    @OnItemClick({R.id.flv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Module module = (Module) adapterView.getItemAtPosition(position);
        switch (module.getModuleId()) {
            case Status.ModuleId.SYSTEM_ACCOUNT:
                GoUtil.goActivity(getActivity(), EmployeeListActivity.class);
                break;
            case Status.ModuleId.SYSTEM_PROJECT_MANAGER:
                GoUtil.goActivity(getActivity(), ProjectListAdminActivity.class);
                break;
            case Status.ModuleId.SYSTEM_MESSAGE:
                GoUtil.goActivity(getActivity(), NotificationActivity.class);

                break;
            case Status.ModuleId.SYSTEM_QUERY:
                ToastUtil.showText("功能开发中");
                break;
            case Status.ModuleId.SYSTEM_LEVY:
                GoUtil.goActivity(getActivity(), LevyActivity.class);
                break;
            case Status.ModuleId.SYSTEM_PROJECT_SUPERVISE:
                ToastUtil.showText("功能开发中");
                break;
            case Status.ModuleId.SYSTEM_LOCATION:
                ToastUtil.showText("功能开发中");
                break;
        }
    }

    public static HomeFragment newInstance(String routeId) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(Constants.Extra.RouteId, routeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            routeId = getArguments().getString(Constants.Extra.RouteId);
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        modulePresenter.attachView(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initNet() {
        modulePresenter.getModuleDetail(routeId);
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onGetModuleDetailSuccess(ModuleDetail moduleDetail) {
        if (moduleDetail != null) {
            tvTitle.setString(moduleDetail.getTitle());
            ImageLoader.getInstance().loadImage(getActivity(), moduleDetail.getImageUrl(), ivImageUrl);
            List<Module> modules = moduleDetail.getModules();
            if (modules != null && modules.size() > 0) {
                flv.setAdapter(new CommonAdapter<Module>(getActivity(), modules, R.layout.item_module) {
                    @Override
                    public void convert(ViewHolder helper, Module module) {
                        helper.setText(R.id.tv_moduleName, module.getModuleName());
                        helper.setText(R.id.tv_moduleDesc, module.getModuleDesc());
                        helper.setImageByUrl(R.id.iv_iconUrl, module.getIconUrl());
                    }
                });
            }

        }

    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
