package com.jdp.hls.page.module;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.activity.TodoActivity;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.SwitchProjectEvent;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Module;
import com.jdp.hls.model.entiy.ModuleDetail;
import com.jdp.hls.page.admin.employee.list.EmployeeListActivity;
import com.jdp.hls.page.admin.message.notification.NotificationActivity;
import com.jdp.hls.page.admin.project.list.ProjectListAdminActivity;
import com.jdp.hls.page.admin.query.list.QueryListActivity;
import com.jdp.hls.page.levy.LevyActivity;
import com.jdp.hls.page.projects.ProjectListActivity;
import com.jdp.hls.page.supervise.project.detail.ProjectDetailSuperviseActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;
import com.jdp.hls.view.StringTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;

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
    @BindView(R.id.rsrl)
    RefreshSwipeRefreshLayout rsrl;
    @BindView(R.id.ll_switch)
    LinearLayout llSwitch;
    Unbinder unbinder;
    private String routeId;

    @Inject
    ModulePresenter modulePresenter;

    @OnClick({R.id.ll_switch})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_switch:
                GoUtil.goActivity(getActivity(), ProjectListActivity.class);
                break;
        }
    }

    @OnItemClick({R.id.flv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Module module = (Module) adapterView.getItemAtPosition(position);
        switch (module.getModuleId()) {
            case Status.ModuleId.SYSTEM_ACCOUNT:
                //账号管理
                GoUtil.goActivity(getActivity(), EmployeeListActivity.class);
                break;
            case Status.ModuleId.SYSTEM_PROJECT_MANAGER:
                //项目管理
                GoUtil.goActivity(getActivity(), ProjectListAdminActivity.class);
                break;
            case Status.ModuleId.SYSTEM_MESSAGE:
                //系统消息
                if (CheckUtil.checkEmpty(SpSir.getInstance().getProjectId(), "请选择项目")) {
                    GoUtil.goActivity(getActivity(), NotificationActivity.class);
                }
                break;
            case Status.ModuleId.SYSTEM_QUERY:
                //数据查询
                if (CheckUtil.checkEmpty(SpSir.getInstance().getProjectId(), "请选择项目")) {
//                    GoUtil.goActivity(getActivity(), ProjectSelectActivity.class);
                    QueryListActivity.GoActivity(getActivity(), SpSir.getInstance().getProjectId(), SpSir.getInstance
                            ().getProjectName());
                }
                break;
            case Status.ModuleId.SYSTEM_LEVY:
                //征收系统
                if (CheckUtil.checkEmpty(SpSir.getInstance().getProjectId(), "请选择项目")) {
                    GoUtil.goActivity(getActivity(), LevyActivity.class);
                }
                break;
            case Status.ModuleId.SYSTEM_PROJECT_SUPERVISE:
                //项目监管
                if (CheckUtil.checkEmpty(SpSir.getInstance().getProjectId(), "请选择项目")) {
                    GoUtil.goActivity(getActivity(), ProjectDetailSuperviseActivity.class);
                }
                break;
            case Status.ModuleId.SYSTEM_LOCATION:
                //地理信息
                TodoActivity.goActivity(getActivity(), "地理信息系统");
                break;
            case Status.ModuleId.SYSTEM_PROJECRT_CHECK:
                //项目审批系统
                TodoActivity.goActivity(getActivity(), "项目审批系统");
                break;
            case Status.ModuleId.SYSTEM_MONEY_MANAGE:
                //财务管理系统
                TodoActivity.goActivity(getActivity(), "财务管理系统");
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
        EventBus.getDefault().register(this);
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
        rsrl.stepRefresh(this);
        tvTitle.setText(TextUtils.isEmpty(SpSir.getInstance().getProjectName()) ? "项目未选择" : SpSir.getInstance()
                .getProjectName());
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
    public void onGetModuleDetailSuccess(ModuleDetail moduleDetail) {
        if (moduleDetail != null) {
//            tvTitle.setString(moduleDetail.getTitle());
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void switchProject(SwitchProjectEvent event) {
        tvTitle.setText(SpSir.getInstance().getProjectName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
