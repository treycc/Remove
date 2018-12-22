package com.jdp.hls.page.supervise.statistics.total.taotype.person;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.TaoTypePersonAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.TaoTypePerson;
import com.jdp.hls.util.SimpleTextWatcher;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/12/20 0020 下午 6:26
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TaoTypePersonListActivity extends BaseTitleActivity implements TaoTypePersonListContract.View {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.lv)
    ListView lv;
    @Inject
    TaoTypePersonListPresenter taoTypePersonListPresenter;
    private TaoTypePersonAdapter taoTypePersonAdapter;
    private int patternId;
    private String title;

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etKeyword.setText("");
                break;
        }
    }

    @Override
    public void initVariable() {
        patternId = getIntent().getIntExtra(Constants.Extra.ID, 0);
        title = getIntent().getStringExtra(Constants.Extra.TITLE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_taotype_person_list;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        taoTypePersonListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return title;
    }

    @Override
    protected void initView() {
        taoTypePersonAdapter = new TaoTypePersonAdapter(this, null);
        lv.setAdapter(taoTypePersonAdapter);
    }

    @Override
    protected void initData() {
        etKeyword.setHint("请输入户主姓名/地址");
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                taoTypePersonAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void initNet() {
        taoTypePersonListPresenter.getTaoTypePersonList(patternId);
    }


    @Override
    public void onGetTaoTypePersonListSuccess(List<TaoTypePerson> taoTypeList) {
        String keyword = etKeyword.getText().toString().trim();
        setSearchListView(taoTypeList, taoTypePersonAdapter, keyword);
    }

    public static void goActivity(Context context, int id, String patternName) {
        Intent intent = new Intent(context, TaoTypePersonListActivity.class);
        intent.putExtra(Constants.Extra.ID, id);
        intent.putExtra(Constants.Extra.TITLE, patternName);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
