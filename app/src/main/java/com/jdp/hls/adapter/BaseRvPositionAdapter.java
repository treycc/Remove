package com.jdp.hls.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/8/3 0003 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseRvPositionAdapter<T> extends RecyclerView.Adapter<BaseRvPositionAdapter.ViewHolder>  {
    protected OnItemClickListener onItemClickListener;
    protected OnItemLongClickListener onItemLongClickListener;
    protected Context context;
    protected List<T> list;

    public BaseRvPositionAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BaseRvPositionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getItemView(), parent, false);
        return createViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        bindHolder(holder, list, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(list, position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(list, position);
                }
                return true;
            }
        });
    }

    protected abstract BaseRvPositionAdapter.ViewHolder createViewHolder(View v);

    protected abstract int getItemView();

    protected abstract void bindHolder(BaseRvPositionAdapter.ViewHolder baseHolder, List<T> list, int position);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setData(List<T> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public List<T> getData() {
        return list;
    }


    public interface OnItemClickListener<T> {
        void onItemClick(List<T> list, int position);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(List<T> list, int position);
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {

        this.onItemLongClickListener = onItemLongClickListener;
    }

}
