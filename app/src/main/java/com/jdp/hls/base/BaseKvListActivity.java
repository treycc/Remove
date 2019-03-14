package com.jdp.hls.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jdp.hls.R;
import com.jdp.hls.adapter.KeyValueAdapter;
import com.jdp.hls.i.IKeyValue;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.view.FixedListView;
import com.jdp.hls.view.StringTextView;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2019/3/14 0014 上午 9:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseKvListActivity<T> extends BaseTitleActivity {
    @BindView(R.id.flv)
    FixedListView flv;
    protected KeyValueAdapter<T> keyValueAdapter;

    @Override
    public void initVariable() {
        Bundle bundle = getIntent().getExtras();
        initVariable(bundle);

    }

    protected abstract void initVariable(Bundle bundle);

    @Override
    protected int getContentView() {
        return R.layout.common_flv;
    }

    @Override
    protected abstract void initComponent(AppComponent appComponent);

    @Override
    protected abstract String getContentTitle();

    @Override
    protected void initView() {
        keyValueAdapter = new KeyValueAdapter<T>(this, null) {
            @Override
            protected void fillKeyValue(StringTextView tvTitle, StringTextView tvValue, T keyValue) {
                setKeyValue(tvTitle, tvValue, keyValue);
            }
        };
        flv.setAdapter(keyValueAdapter);
    }

    protected abstract void setKeyValue(StringTextView tvTitle, StringTextView tvValue, T keyValue);

    @Override
    protected void initData() {

    }

    @Override
    public abstract void initNet();

    public static void goActivity(Context context, Class clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


}
