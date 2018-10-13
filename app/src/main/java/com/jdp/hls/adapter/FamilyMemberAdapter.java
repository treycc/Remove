package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.FamilyMember;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FamilyMemberAdapter extends BaseLvAdapter<FamilyMember> {
    private OnDeleteFamilyMemberListener onDeleteFamilyMemberListener;

    public FamilyMemberAdapter(Context context, List<FamilyMember> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_family_relation, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_familyRelation_title.setText(list.get(position).getTypeName());
        viewHolder.tv_familyRelation_name.setText(list.get(position).getRealName());
        viewHolder.tv_familyRelation_idCard.setText(list.get(position).getIdcard());
        viewHolder.tv_delete.setOnClickListener(v -> {
            if (onDeleteFamilyMemberListener != null) {
                onDeleteFamilyMemberListener.onDeleteFamilyMember(String.valueOf(list.get(position).getPersonId()),
                        String.valueOf(list.get(position).getBookletId()), position);
            }

        });
        viewHolder.drawHelperLayout.close(false);
        viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
            if (onDeleteFamilyMemberListener != null) {
                onDeleteFamilyMemberListener.onFamilyMemberClick(list.get(position));
            }
        });

        return convertView;
    }

    public void remove(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    public void addFirst(FamilyMember familyMember) {
        list.add(0, familyMember);
        notifyDataSetChanged();
    }

    public void modify(FamilyMember familyMember) {
        for (FamilyMember item : list) {
            if (item.getPersonId().equals(familyMember.getPersonId())) {
                item.setGender(familyMember.isGender());
                item.setRealName(familyMember.getRealName());
                item.setIdcard(familyMember.getIdcard());
                item.setIsFarming(familyMember.getIsFarming());
                item.setTypeId(familyMember.getTypeId());
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public final View root;
        public TextView tv_familyRelation_title;
        public TextView tv_familyRelation_name;
        public TextView tv_familyRelation_idCard;
        public DrawHelperLayout drawHelperLayout;
        public TextView tv_delete;

        public ViewHolder(View root) {
            this.root = root;
            tv_familyRelation_title = root.findViewById(R.id.tv_familyRelation_title);
            tv_familyRelation_name = root.findViewById(R.id.tv_familyRelation_name);
            tv_familyRelation_idCard = root.findViewById(R.id.tv_familyRelation_idCard);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
        }
    }

    public interface OnDeleteFamilyMemberListener {
        void onDeleteFamilyMember(String personId, String bookletId, int position);

        void onFamilyMemberClick(FamilyMember familyMember);
    }

    public void setOnDeleteFamilyMemberListener(OnDeleteFamilyMemberListener onDeleteFamilyMemberListener) {
        this.onDeleteFamilyMemberListener = onDeleteFamilyMemberListener;
    }
}
