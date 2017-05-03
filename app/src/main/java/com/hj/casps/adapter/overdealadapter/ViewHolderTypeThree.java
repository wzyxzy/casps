package com.hj.casps.adapter.overdealadapter;

import android.view.View;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.payadapter.TypeAbstractViewHolder;
import com.hj.casps.entity.SectionEntity;

/**
 * Created by Admin on 2017/4/19.
 * 商品清单
 */

public class ViewHolderTypeThree extends TypeAbstractViewHolder<SectionEntity.OrderList> {
    private TextView rl_city_commodity_name, rl_city_commodity_money,
            rl_city_commodity_one_money, rl_city_commodity_count;


    public ViewHolderTypeThree(View itemView) {
        super(itemView);

        rl_city_commodity_name = (TextView) itemView.findViewById(R.id.rl_three_city_commodity_name);
        rl_city_commodity_money = (TextView) itemView.findViewById(R.id.rl_city_three_commodity_money);
        rl_city_commodity_one_money = (TextView) itemView.findViewById(R.id.rl_city_three_commodity_one_money);
        rl_city_commodity_count = (TextView) itemView.findViewById(R.id.rl_city_three_commodity_count);

    }

    @Override
    public void bindViewHolder(SectionEntity.OrderList orderList, final int postion) {
        rl_city_commodity_name.setText(orderList.getOredertitleNumber());
        rl_city_commodity_money.setText(orderList.getGoodsName());
        rl_city_commodity_one_money.setText(orderList.getOrdermoney() + "");
        rl_city_commodity_count.setText(orderList.getMoney() + "");
    }
}
