package com.jdp.hls.page.rosterdetail.contacts.list;

import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.BaseListFactory;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.dialog.BaseListDialog;

/**
 * Description:TODO
 * Create Time:2019/1/28 0028 下午 2:15
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsListActivity extends BaseTitleActivity {
    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.common_plv;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "户主列表";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        BaseListDialog createTypeDialog = new BaseListDialog(this, BaseListFactory.getCreateTypeList(), "添加方式");
        createTypeDialog.setOnDisPlayItemClickListener(displayItem -> {
            switch (displayItem.getCode()) {
                case Status.BuildingType.PERSONAL:
                    ToastUtil.showText("导入");
                    break;
                case Status.BuildingType.COMPANY:
                    ToastUtil.showText("新增");
                    break;
            }
        });
        setRightClick("添加", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                createTypeDialog.show();
            }
        });

    }

    @Override
    public void initNet() {

    }
}
