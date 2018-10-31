package com.jdp.hls.adapter;

import android.content.Context;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/8/21 0021 上午 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PersonAdapter extends CommonAdapter<Person> {
    private List<Person> selectDatas = new ArrayList<>();

    public PersonAdapter(Context context, List<Person> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
        selectDatas = datas;
}

    @Override
    public void convert(ViewHolder helper, Person person) {
        helper.setText(R.id.tv_person_name, person.getRealName());
        helper.setText(R.id.tv_person_mobile, person.getMobilePhone());
        helper.setText(R.id.tv_person_idcard, person.getIdcard());
    }

    public void searchPerson(String keyword) {
        selectDatas = new ArrayList<>();
        for (Person person : mDatas) {
            if (person.getRealName().contains(keyword) || person.getIdcard().contains(keyword)|| person.getMobilePhone().contains(keyword)) {
                selectDatas.add(person);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Person getItem(int position) {
        return selectDatas.get(position);
    }

    @Override
    public int getCount() {
        return selectDatas.size();
    }

    @Override
    public void setData(List<Person> list) {
        selectDatas = list;
        mDatas = list;
        notifyDataSetChanged();
    }
}
