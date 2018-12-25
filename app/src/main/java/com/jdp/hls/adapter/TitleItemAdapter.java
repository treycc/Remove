package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.constant.Status;
import com.jdp.hls.model.entiy.KeyValue;
import com.jdp.hls.model.entiy.TaoType;
import com.jdp.hls.model.entiy.TitleItem;
import com.jdp.hls.page.supervise.statistics.total.pay.list.SupervisePayOwnerListActivity;
import com.jdp.hls.page.supervise.statistics.total.taotype.StatisticsTaotypeListActivity;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.FixedListView;
import com.jdp.hls.view.StringTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TitleItemAdapter extends BaseLvAdapter<TitleItem> {

    public TitleItemAdapter(Context context, List<TitleItem> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_statistics_total_title, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        TitleItem titleItem = (TitleItem) getItem(position);
        viewHolder.tv_title.setString(titleItem.getTitle());
        viewHolder.flv.setAdapter(new CommonAdapter<KeyValue>(context, titleItem.getItems(), R.layout
                .item_statistics_total) {
            @Override
            public void convert(com.jdp.hls.adapter.ViewHolder helper, KeyValue item) {
                helper.setText(R.id.tv_key, item.getName());
                helper.setText(R.id.tv_value, item.getValueDesc());
                helper.setVisibility(R.id.iv_detail, item.isHasDetail());
            }
        });
        viewHolder.flv.setOnItemClickListener((adapterView, view, itemPosition, id) -> {
            KeyValue keyValue = (KeyValue) adapterView.getItemAtPosition(itemPosition);
            LogUtil.e(TAG, "OnItem" + keyValue.getInterfaceId());
            if (!keyValue.isHasDetail()) {
                return;
            }
            switch (keyValue.getInterfaceId()) {
                case Status.InterfaceId.TAOTYPE_DETAIL:
                    StatisticsTaotypeListActivity.GoActivity(context, keyValue.getName(), keyValue.getValue(),
                            getBuildingArea(titleItem.getItems()));
                    break;
                case Status.InterfaceId.PAY_DETAIL:
                    SupervisePayOwnerListActivity.GoActivity(context, keyValue.getType(), keyValue.getUseItemId(),
                            keyValue.getName());
                    break;
            }
        });
        return convertView;
    }

    private String getBuildingArea(List<KeyValue> keyValueList) {
        if (keyValueList != null && keyValueList.size() > 0) {
            for (KeyValue keyValue : keyValueList) {
                if (Status.AreaType.BUILDING_AREA.equals(keyValue.getName())) {
                    return keyValue.getValue();
                }

            }
        }
        return "0";

    }


    public class ViewHolder {
        public final View root;
        StringTextView tv_title;
        FixedListView flv;

        public ViewHolder(View root) {
            this.root = root;
            tv_title = root.findViewById(R.id.tv_title);
            flv = root.findViewById(R.id.flv);
        }
    }
}
