package com.jdp.hls.page.node.protocol.personal.lastst.taotype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.TaoTypeSelectAdapter;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.TaoType;
import com.jdp.hls.util.NoDoubleClickListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:TODO
 * Create Time:2018/12/19 0019 下午 3:26
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TaoTypeSelectActivity extends BaseTitleActivity {

    @BindView(R.id.lv)
    ListView lv;
    private List<TaoType> taoTypeList = new ArrayList<>();
    private boolean editable;
    private TaoTypeSelectAdapter taoTypeSelectAdapter;

    @Override
    public void initVariable() {
        taoTypeList = (List<TaoType>) getIntent().getSerializableExtra(Constants.Extra.LIST);
        editable = getIntent().getBooleanExtra(Constants.Extra.EDITABLE, false);

    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "选择套型";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        taoTypeSelectAdapter = new TaoTypeSelectAdapter(this, taoTypeList, editable);
        lv.setAdapter(taoTypeSelectAdapter);
        if (editable) {
            setRightClick("保存", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    Intent intent = new Intent();
                    List<TaoType> taoTypeList = taoTypeSelectAdapter.getData();
                    intent.putExtra(Constants.Extra.LIST, (Serializable) taoTypeList);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void initNet() {

    }

    public static void goActivity(BaseFragment fragment, List<TaoType> taoTypeList, boolean editable) {
        Intent intent = new Intent(fragment.getActivity(), TaoTypeSelectActivity.class);
        intent.putExtra(Constants.Extra.LIST, (Serializable) taoTypeList);
        intent.putExtra(Constants.Extra.EDITABLE, editable);
        fragment.startActivityForResult(intent, Constants.RequestCode.TaoTypeSelect);
    }

}
