package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.TaoType;
import com.jdp.hls.model.entiy.UnRecordBuilding;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TaoTypeAdapter extends BaseLvAdapter<TaoType> {
    private OnItemOperListener onItemOperListener;
    private List<String> deletePatternIDs = new ArrayList<>();

    public TaoTypeAdapter(Context context, List<TaoType> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_taotype_add, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.drawHelperLayout.close(false);
        viewHolder.tv_name.setString(String.format("%s(%sm²)", String.valueOf(list.get(position).getPatternName()),
                String.valueOf(list.get(position).getArea())));
        viewHolder.tv_delete.setOnClickListener(v -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemDelete(String.valueOf(list.get(position).getId()), position);
            }
        });
        viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemClick(list.get(position));
            }
        });
        return convertView;
    }

    public String getDeleteIds() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deletePatternIDs.size(); i++) {
            if (i != deletePatternIDs.size() - 1) {
                sb.append(String.valueOf(deletePatternIDs.get(i)));
                sb.append("#");
            } else {
                sb.append(String.valueOf(deletePatternIDs.get(i)));
            }
        }
        return sb.toString();
    }

    public class ViewHolder {
        public final View root;
        StringTextView tv_name;
        DrawHelperLayout drawHelperLayout;
        TextView tv_delete;

        public ViewHolder(View root) {
            this.root = root;
            tv_name = root.findViewById(R.id.tv_name);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
        }
    }

    @Override
    public void removeItem(int position) {
        TaoType taoType = list.get(position);
        int id = taoType.getId();
        if (id != 0) {
            deletePatternIDs.add(String.valueOf(id));
        }
        super.removeItem(position);
    }

    public interface OnItemOperListener {
        void onItemDelete(String id, int position);

        void onItemClick(TaoType taoType);
    }

    public void setOnItemOperListener(OnItemOperListener onItemOperListener) {
        this.onItemOperListener = onItemOperListener;
    }
}
