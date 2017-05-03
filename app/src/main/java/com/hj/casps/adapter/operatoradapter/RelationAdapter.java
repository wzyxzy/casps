package com.hj.casps.adapter.operatoradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.hj.casps.R;
import com.hj.casps.entity.OperatorEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zdd
 */

public class RelationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private LayoutInflater layoutInflater;
    private List<OperatorEntity> mList;
    public static OnItemClickLitener mOnItemClickLitener;
    private Map<Integer, String> ckMap;

    public RelationAdapter(Context context, List<OperatorEntity> mList) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mList = mList;
        ckMap = new HashMap<>();
    }

    public static void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        RelationAdapter.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolderRelation) holder).bindViewHolder(mList.get(position).getAccountName(),position);
        final CheckBox checkBox = (CheckBox) holder.itemView.findViewById(R.id.relation_ck);
//        checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("show", "下标：" + position);
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);
                        ckMap.remove(position);
                    } else {
                        checkBox.setChecked(true);
                        ckMap.put(position, mList.get(position).getAccountName());
                    }
                    mOnItemClickLitener.onItemClick(holder.itemView, position, ckMap);
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderRelation(layoutInflater.inflate(R.layout.relation_item, parent, false));
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position, Map<Integer, String> ckMap);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

}
