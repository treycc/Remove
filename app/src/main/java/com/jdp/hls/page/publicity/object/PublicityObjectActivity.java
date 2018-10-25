package com.jdp.hls.page.publicity.object;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.PublicityObjectAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.PublicityObject;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 8:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityObjectActivity extends BaseTitleActivity implements PublicityObjectContract.View {
    @BindView(R.id.iv_business_search)
    ImageView ivBusinessSearch;
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.cb_selectAll)
    CheckBox cbSelectAll;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;

    private List<PublicityObject> publicityObjectList = new ArrayList<>();
    private PublicityObjectAdapter publicityObjectAdapter;
    public static String SELECTED_OBJECTS = "selected_objects";

    private int publicityType;
    private int buildingType;
    @Inject
    PublicityObjectPresenter publicityObjectPresenter;

    @Override
    public void initVariable() {
        publicityType = getIntent().getIntExtra(Constants.Extra.PUBLICITY_TYPE, 0);
        buildingType = getIntent().getIntExtra(Constants.Extra.BUILDING_TYPE, 0);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_publicity_object;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "选择公示对象";
    }

    @Override
    protected void initView() {
        publicityObjectPresenter.attachView(this);
        publicityObjectAdapter = new PublicityObjectAdapter(this, publicityObjectList);
        plv.setAdapter(publicityObjectAdapter);

    }

    @OnCheckedChanged({R.id.cb_selectAll})
    public void checkedChanged(CompoundButton buttonView, boolean isChecked) {
        publicityObjectAdapter.setAllCheckedStatus(isChecked);

    }

    @Override
    protected void initData() {
        setRightClick("确定", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                String buildingIds = publicityObjectAdapter.getSelectedBuildingIds();
                if (!TextUtils.isEmpty(buildingIds)) {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.Extra.BUILDINGIDS, buildingIds);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    ToastUtil.showText("请选择公示对象");
                }
            }
        });
    }

    @Override
    protected void initNet() {
        publicityObjectPresenter.getPublicityObject(buildingType, publicityType);
    }

    public static void goActivity(Activity context, int publicityType, int buildingType) {
        Intent intent = new Intent(context, PublicityObjectActivity.class);
        intent.putExtra(Constants.Extra.PUBLICITY_TYPE, publicityType);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        context.startActivityForResult(intent, Constants.RequestCode.PUBLICITY_OBJECT);
    }

    @Override
    public void onGetPublicityObjectSuccess(List<PublicityObject> publicityObjects) {
        if (publicityObjects != null && publicityObjects.size() > 0) {
            publicityObjectAdapter.setData(publicityObjects);
        } else {
            showEmptyCallback();
        }
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
