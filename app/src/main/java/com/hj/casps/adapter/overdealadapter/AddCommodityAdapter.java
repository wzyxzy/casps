package com.hj.casps.adapter.overdealadapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.hj.casps.R;
import com.hj.casps.adapter.payadapter.CommonAdapter;
import com.hj.casps.adapter.payadapter.ViewHolder;
import com.hj.casps.entity.CardInfoEntity;
import com.hj.casps.entity.CommodityDetailsEntity;

import java.util.List;

/**
 * Created by Admin on 2017/4/18.
 * 收款列表adapter
 */

public class AddCommodityAdapter extends CommonAdapter<CommodityDetailsEntity> {

    public static onCheckedkType onCheckedkType;

    public static void setOnCheckedkType(AddCommodityAdapter.onCheckedkType onCheckedkType) {
        AddCommodityAdapter.onCheckedkType = onCheckedkType;
    }

    public AddCommodityAdapter(Context context, List<CommodityDetailsEntity> datas) {
        super(context, datas, R.layout.create_settl_vholder_two);
        this.mContext = context;
    }

    @Override
    public void concert(final ViewHolder hooder, final CommodityDetailsEntity commodityDetailsEntity, final int indexPos) {
        hooder.setText(R.id.vholder_two_tv_bills_id_content, commodityDetailsEntity.getOredertitleNumber());
        hooder.setText(R.id.vholder_two_tv_commodity_name_content, commodityDetailsEntity.getGoodsName());
        hooder.setText(R.id.vholder_two_tv_bills_money_content, commodityDetailsEntity.getOrdermoney() + "");
        hooder.setText(R.id.vholder_two_tv_await_money_content, commodityDetailsEntity.getAwaitmoney() + "");
        hooder.setText(R.id.vholder_two_tv_bills_reality_money_content, commodityDetailsEntity.getMoney() + "");

        final CheckBox tCheck = hooder.getView(R.id.vholder_two_payment_ck_bills);
        tCheck.setChecked(commodityDetailsEntity.isCheck());
        tCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commodityDetailsEntity.setCheck(tCheck.isChecked());
                if (tCheck.isChecked()) {
                    if (onCheckedkType != null)
                        onCheckedkType.onCheckedY(indexPos);
                } else {
                    if (onCheckedkType != null)
                        onCheckedkType.onCheckedN(indexPos);
                }
            }
        });
    }

    public interface onCheckedkType {
        void onCheckedY(int pos);

        void onCheckedN(int pos);
    }
}
