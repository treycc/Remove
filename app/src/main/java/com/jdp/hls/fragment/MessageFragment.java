package com.jdp.hls.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.activity.MessageDetailActivity;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Message;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MessageFragment extends BaseFragment {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private List<Message> messages = new ArrayList<>();
    private CommonAdapter adapter;

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString("param", null);
        fragment.setArguments(args);
        return fragment;
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        GoUtil.goActivity(getActivity(), MessageDetailActivity.class);
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            String param = getArguments().getString("param");
        }
        for (int i = 0; i < 10; i++) {
            messages.add(new Message("曹雪晴" + i, "2018-08-22 10:22:2" + i, "您好，请发送的丈量信息审核不通过，审核人为朱自清，不通过原因为：图片清晰度不够" +
                    i));
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView() {
        plv.setAdapter(adapter = new CommonAdapter<Message>(getActivity(), messages, R.layout.item_message) {
            @Override
            public void convert(ViewHolder helper, Message item) {
                helper.setText(R.id.tv_message_sender, item.getSender());
                helper.setText(R.id.tv_message_date, item.getDate());
                helper.setText(R.id.tv_message_content, item.getContent());
//                helper.setText(R.id.v_message_isReaded, item.getPerson());
            }
        });
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.common_lv_title;
    }

}
