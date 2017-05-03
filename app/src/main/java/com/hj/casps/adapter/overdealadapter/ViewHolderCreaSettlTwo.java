package com.hj.casps.adapter.overdealadapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.payadapter.TypeAbstractViewHolder;
import com.hj.casps.entity.CommodityDetailsEntity;

/**
 * Created by Admin on 2017/4/19.
 * 订单详情
 */

public class ViewHolderCreaSettlTwo extends TypeAbstractViewHolder<CommodityDetailsEntity> {

    private TextView vholder_two_tv_bills_id_content,
            vholder_two_tv_commodity_name_content,
            vholder_two_tv_bills_money_content,
            vholder_two_tv_await_money_content,
            vholder_two_tv_bills_reality_money_content;
    private CheckBox vholder_two_payment_ck_bills;

    public static onCheckedkType onCheckedkType;

    public static void setOnCheckedkType(ViewHolderCreaSettlTwo.onCheckedkType onCheckedkType) {
        ViewHolderCreaSettlTwo.onCheckedkType = onCheckedkType;
    }



    public ViewHolderCreaSettlTwo(View itemView) {
        super(itemView);
        vholder_two_payment_ck_bills = (CheckBox) itemView.findViewById(R.id.vholder_two_payment_ck_bills);
        vholder_two_tv_bills_id_content = (TextView) itemView.findViewById(R.id.vholder_two_tv_bills_id_content);
        vholder_two_tv_commodity_name_content = (TextView) itemView.findViewById(R.id.vholder_two_tv_commodity_name_content);
        vholder_two_tv_bills_money_content = (TextView) itemView.findViewById(R.id.vholder_two_tv_bills_money_content);
        vholder_two_tv_await_money_content = (TextView) itemView.findViewById(R.id.vholder_two_tv_await_money_content);
        vholder_two_tv_bills_reality_money_content = (TextView) itemView.findViewById(R.id.vholder_two_tv_bills_reality_money_content);

    }

    @Override
    public void bindViewHolder(final CommodityDetailsEntity commEntity, final int postion) {
        vholder_two_tv_bills_id_content.setText(commEntity.getOredertitleNumber());
        vholder_two_tv_commodity_name_content.setText(commEntity.getGoodsName());
        vholder_two_tv_bills_money_content.setText(commEntity.getOrdermoney() + "");
        vholder_two_tv_await_money_content.setText(commEntity.getAwaitmoney() + "");
        vholder_two_tv_bills_reality_money_content.setText(commEntity.getMoney() + "");

        commEntity.setCheck(vholder_two_payment_ck_bills.isChecked());
        vholder_two_payment_ck_bills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commEntity.setCheck(vholder_two_payment_ck_bills.isChecked());
                if (vholder_two_payment_ck_bills.isChecked()) {
                    if (onCheckedkType != null)
                        onCheckedkType.onCheckedY(postion);
                } else {
                    if (onCheckedkType != null)
                        onCheckedkType.onCheckedN(postion);
                }
            }
        });
    }


    public interface onCheckedkType {
        void onCheckedY(int pos);

        void onCheckedN(int pos);
    }

}
