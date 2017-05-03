package com.hj.casps.ordermanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.WZYBaseAdapter;

import java.util.List;

/**
 * Created by zy on 2017/4/27.
 */

public class OrderAdapter extends WZYBaseAdapter<OrderBuyModel> {
    private Context context;

    public OrderAdapter(List<OrderBuyModel> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
        this.context = context;
    }

    @Override
    public void bindData(ViewHolder holder, final OrderBuyModel orderBuyModel) {
        TextView type = (TextView) holder.getView(R.id.text_name_buy_cart);
        type.setText(orderBuyModel.getType());
        TextView name = (TextView) holder.getView(R.id.order_buy_name);
        name.setText(orderBuyModel.getName());
        final TextView contents = (TextView) holder.getView(R.id.buy_cart_info_contents);
        contents.setText(orderBuyModel.getContents());
        TextView num = (TextView) holder.getView(R.id.order_buy_num);
        num.setText(String.valueOf(orderBuyModel.getNum()));
        RelativeLayout buy_cart_contents_rl = (RelativeLayout) holder.getView(R.id.buy_cart_contents_rl);
        buy_cart_contents_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BuyShell.class);
                intent.putExtra("buy_type", orderBuyModel.getType());
                intent.putExtra("buy_name", orderBuyModel.getName());
                intent.putExtra("buy_num", orderBuyModel.getNum());
                context.startActivity(intent);
            }
        });


    }
}
