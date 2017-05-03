package com.hj.casps.adapter.payadapter;

import android.view.View;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.payadapter.TypeAbstractViewHolder;
import com.hj.casps.entity.PayMnetInfo;

/**
 * Created by Admin on 2017/4/19.
 * 订单详情
 */

public class ViewHolderFather extends TypeAbstractViewHolder<PayMnetInfo> {
    private TextView rl_one_tv_bills_id, rl_ft_tv_bills_type, rl_ft_tv_flow_id, rl_ft_tv_buy_name,
            rl_ft_tv_contract_time, rl_ft_tv_payment_time, rl_ft_tv_start_delivery,
            rl_ft_tv_end_delivery, rl_ft_tv_sum_money, rl_ft_tv_payment_card_id,
            rl_ft_tv_gathering_card, rl_ft_tv_payment_address;


    public ViewHolderFather(View itemView) {
        super(itemView);

        rl_one_tv_bills_id = (TextView) itemView.findViewById(R.id.rl_one_tv_bills_id);
        rl_ft_tv_bills_type = (TextView) itemView.findViewById(R.id.rl_ft_tv_bills_type);
        rl_ft_tv_flow_id = (TextView) itemView.findViewById(R.id.rl_ft_tv_flow_id);
        rl_ft_tv_buy_name = (TextView) itemView.findViewById(R.id.rl_ft_tv_buy_name);
        rl_ft_tv_contract_time = (TextView) itemView.findViewById(R.id.rl_ft_tv_contract_time);
        rl_ft_tv_payment_time = (TextView) itemView.findViewById(R.id.rl_ft_tv_payment_time);
        rl_ft_tv_start_delivery = (TextView) itemView.findViewById(R.id.rl_ft_tv_start_delivery);
        rl_ft_tv_end_delivery = (TextView) itemView.findViewById(R.id.rl_ft_tv_end_delivery);
        rl_ft_tv_sum_money = (TextView) itemView.findViewById(R.id.rl_ft_tv_sum_money);
        rl_ft_tv_payment_card_id = (TextView) itemView.findViewById(R.id.rl_ft_tv_payment_card_id);
        rl_ft_tv_gathering_card = (TextView) itemView.findViewById(R.id.rl_ft_tv_gathering_card);
        rl_ft_tv_payment_address = (TextView) itemView.findViewById(R.id.rl_ft_tv_payment_address);
    }

    @Override
    public void bindViewHolder(PayMnetInfo cargoMessage,int postion) {
        rl_one_tv_bills_id.setText(cargoMessage.getBillsId());
        rl_ft_tv_bills_type.setText(cargoMessage.getBillsType());
        rl_ft_tv_flow_id.setText(cargoMessage.getFlowId());
        rl_ft_tv_buy_name.setText(cargoMessage.getBuyer());
        rl_ft_tv_contract_time.setText(cargoMessage.getContractTime());
        rl_ft_tv_payment_time.setText(cargoMessage.getPaymentTime());
        rl_ft_tv_start_delivery.setText(cargoMessage.getDeliveryStarTime());
        rl_ft_tv_end_delivery.setText(cargoMessage.getDeliveryEndTime());
        rl_ft_tv_sum_money.setText(cargoMessage.getTotalMoney());
        rl_ft_tv_payment_card_id.setText(cargoMessage.getPayId());
        rl_ft_tv_gathering_card.setText(cargoMessage.getBuyId());
        rl_ft_tv_payment_address.setText(cargoMessage.getHarvestAddress());
    }
}
