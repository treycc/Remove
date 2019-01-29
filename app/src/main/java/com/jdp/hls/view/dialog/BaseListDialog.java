package com.jdp.hls.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.injector.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2019/1/29 0029 上午 11:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public  class BaseListDialog extends CommonDialog {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    private List<DisplayItem> list;
    private String title;
    @BindView(R.id.lv)
    ListView lv;
    private OnDisPlayItemClickListener onDisPlayItemClickListener;

    public BaseListDialog(@NonNull Context context) {
        super(context);
    }

    public BaseListDialog(@NonNull Context context, List<DisplayItem> list) {
        this(context, list, "请选择");
    }

    public BaseListDialog(@NonNull Context context, List<DisplayItem> list, String title) {
        this(context);
        this.list = (list == null ? new ArrayList<>() : list);
        this.title = title;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_base_list;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    public void initVariable() {


    }

    @Override
    public void initView() {
        lv.setAdapter(new BaseListAdapter(getContext(), list));
        lv.setOnItemClickListener((parent, view, position, id) -> {
            DisplayItem displayItem = (DisplayItem) parent.getItemAtPosition(position);
            dismiss();
            if (onDisPlayItemClickListener != null) {
                onDisPlayItemClickListener.onDisPlayItemClick(displayItem);
            }
        });
    }

    public interface OnDisPlayItemClickListener {
        void onDisPlayItemClick(DisplayItem displayItem);
    }

    public void setOnDisPlayItemClickListener(OnDisPlayItemClickListener onDisPlayItemClickListener) {
        this.onDisPlayItemClickListener = onDisPlayItemClickListener;
    }

    @Override
    public void initData() {
        tvTitle.setText(title);
    }

    @Override
    public void initNet() {

    }

    class BaseListAdapter extends BaseAdapter {
        private Context context;
        private List<DisplayItem> dataList;

        BaseListAdapter(Context context, List<DisplayItem> dataList) {
            this.context = context;
            this.dataList = (dataList == null ? new ArrayList<>() : dataList);
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View
                        .inflate(context, R.layout.item_single_tv, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            DisplayItem displayItem = (DisplayItem) getItem(position);
            viewHolder.tv_single.setText(displayItem.getName());
            return convertView;
        }

        class ViewHolder {
            public final View root;
            TextView tv_single;

            public ViewHolder(View root) {
                this.root = root;
                tv_single = root.findViewById(R.id.tv_single);
            }
        }
    }


    public static class DisplayItem {
        private int code;
        private String name;

        public DisplayItem(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return null == name ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
