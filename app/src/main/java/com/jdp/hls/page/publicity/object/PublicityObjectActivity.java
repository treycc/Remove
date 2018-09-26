package com.jdp.hls.page.publicity.object;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.PublicityObjectAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.PublicityObject;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 8:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityObjectActivity extends BaseTitleActivity {
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
    public static String SELECTED_OBJECTS="selected_objects";

    @Override
    public void initVariable() {
        for (int i = 0; i < 20; i++) {
            publicityObjectList.add(new PublicityObject());
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_publicity_object;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "选择公示对象";
    }

    @Override
    protected void initView() {
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
                List<PublicityObject> selectedObjects = publicityObjectAdapter.getSelectedObjects();
                if (selectedObjects.size() > 0) {
                    Intent intent = new Intent();
                    intent.putExtra(SELECTED_OBJECTS,(Serializable) selectedObjects);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }else{
                    ToastUtil.showText("请选择公示对象");
                }

            }
        });

    }

    @Override
    protected void initNet() {

    }

}
