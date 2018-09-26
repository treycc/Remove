package com.jdp.hls.page.otherarea;

import com.jdp.hls.R;
import com.jdp.hls.adapter.OtherAreaAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AirPhotoPerson;
import com.jdp.hls.model.entiy.OtherArea;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherAreaListActivity extends BaseTitleActivity {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private List<OtherArea> otherAreas = new ArrayList<>();
    private OtherAreaAdapter otherAreaAdapter;

    @Override
    public void initVariable() {
        for (int i = 0; i < 10; i++) {
            otherAreas.add(new OtherArea());
        }
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
        return "其它面积";
    }

    @Override
    protected void initView() {
        otherAreaAdapter = new OtherAreaAdapter(this, otherAreas, R.layout.item_otherarea);
        plv.setAdapter(otherAreaAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

    }

}
