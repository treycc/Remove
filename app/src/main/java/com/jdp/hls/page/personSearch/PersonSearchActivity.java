package com.jdp.hls.page.personSearch;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.PersonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Person;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/8/17 0017 下午 4:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PersonSearchActivity extends BaseTitleActivity implements PersonsContract.View, SwipeRefreshLayout
        .OnRefreshListener {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    @Inject
    PersonsPresenter personsPresenter;
    List<Person> persons = new ArrayList<>();

    private PersonAdapter adapter;

    @OnClick({R.id.iv_search})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                break;
        }
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Person person = (Person) adapterView.getItemAtPosition(position);
        Intent intent = new Intent();
        intent.putExtra("person", person);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_person_list;
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
        return "选择联系人";
    }

    @Override
    protected void initView() {
        personsPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        plv.setAdapter(adapter = new PersonAdapter(this, persons, R.layout.item_person));
        srl.setOnRefreshListener(this);
        etKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.searchPerson(s.toString());
            }
        });
    }

    @Override
    protected void initNet() {
        personsPresenter.getPersons(SpSir.getInstance().getProjectId());
    }

    @Override
    public void onGetPersonsSuccess(List<Person> persons) {
        if (persons != null && persons.size() > 0) {
            adapter.setData(persons);
        }
    }

    @Override
    public void showLoading() {
        srl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srl.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        initNet();
    }
}
