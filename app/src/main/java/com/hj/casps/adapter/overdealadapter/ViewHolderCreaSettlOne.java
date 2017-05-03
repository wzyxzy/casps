package com.hj.casps.adapter.overdealadapter;

import android.view.View;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.payadapter.TypeAbstractViewHolder;
import com.hj.casps.entity.OverBillsDtailsEntity;

/**
 * Created by Admin on 2017/4/19.
 * 订单详情
 */

public class ViewHolderCreaSettlOne extends TypeAbstractViewHolder<OverBillsDtailsEntity> {
    private TextView vholder_buyersName_tv,
            vholder_sellersName_tv,
            vholder_buyersId_tv,
            vholder_sellersId_tv,
            vholder_buyerTime_tv;

    public ViewHolderCreaSettlOne(View itemView) {
        super(itemView);

        vholder_buyersName_tv = (TextView) itemView.findViewById(R.id.vholder_buyersName_tv);
        vholder_sellersName_tv = (TextView) itemView.findViewById(R.id.vholder_sellersName_tv);
        vholder_buyersId_tv = (TextView) itemView.findViewById(R.id.vholder_buyersId_tv);
        vholder_sellersId_tv = (TextView) itemView.findViewById(R.id.vholder_sellersId_tv);
        vholder_buyerTime_tv = (TextView) itemView.findViewById(R.id.vholder_buyerTime_tv);
    }

    @Override
    public void bindViewHolder(OverBillsDtailsEntity oEntity, final int postion) {
        vholder_buyersName_tv.setText(oEntity.getBuyersName());
        vholder_sellersName_tv.setText(oEntity.getSellersName());
        vholder_buyersId_tv.setText(oEntity.getBuyersId());
        vholder_sellersId_tv.setText(oEntity.getSellersId());
        vholder_buyerTime_tv.setText(oEntity.getCtrTime());
    }

}
