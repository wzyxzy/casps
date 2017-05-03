package com.hj.casps.adapter.payadapter;

import android.view.View;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.payadapter.TypeAbstractViewHolder;
import com.hj.casps.entity.PayMnetInfo;

/**
 * Created by Admin on 2017/4/19.
 * 商品清单
 */

public class ViewHolderCommodity extends TypeAbstractViewHolder<PayMnetInfo.CommodityIndo> {
    private TextView rl_city_commodity_name, rl_city_commodity_money,
            rl_city_commodity_one_money, rl_city_commodity_count,
            rl_city_await_count, rl_city_reality_count,
            rl_city_await_money,rl_reality_money;


    public ViewHolderCommodity(View itemView) {
        super(itemView);

        rl_city_commodity_name = (TextView) itemView.findViewById(R.id.rl_city_commodity_name);
        rl_city_commodity_money = (TextView) itemView.findViewById(R.id.rl_city_commodity_money);
        rl_city_commodity_one_money = (TextView) itemView.findViewById(R.id.rl_city_commodity_one_money);
        rl_city_commodity_count = (TextView) itemView.findViewById(R.id.rl_city_commodity_count);
        rl_city_await_count = (TextView) itemView.findViewById(R.id.rl_city_await_count);
        rl_city_reality_count = (TextView) itemView.findViewById(R.id.rl_city_reality_count);
        rl_city_await_money = (TextView) itemView.findViewById(R.id.rl_city_await_money);
        rl_reality_money = (TextView) itemView.findViewById(R.id.rl_reality_money);

    }

    @Override
    public void bindViewHolder(PayMnetInfo.CommodityIndo cargoMessage,int postion) {
        rl_city_commodity_name.setText(cargoMessage.getGoodsName());
        rl_city_commodity_money.setText(cargoMessage.getGoodsSumMoney());
        rl_city_commodity_one_money.setText(cargoMessage.getGoodsOneMoney());
        rl_city_commodity_count.setText(cargoMessage.getGoodsSumCount());

        rl_city_await_count.setText(cargoMessage.getGoodsWaitCount());
        rl_city_reality_count.setText(cargoMessage.getGoodsHarvestCount());
        rl_city_await_money.setText(cargoMessage.getGoodsWaitMoney());
        rl_reality_money.setText(cargoMessage.getGoodsHarvestMoney());
    }


}
