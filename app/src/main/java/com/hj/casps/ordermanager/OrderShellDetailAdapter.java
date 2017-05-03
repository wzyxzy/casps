package com.hj.casps.ordermanager;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.WZYBaseAdapter;

import java.util.List;

/**
 * Created by zy on 2017/4/27.
 */

public class OrderShellDetailAdapter extends WZYBaseAdapter<OrderShellModel> {

    public OrderShellDetailAdapter(List<OrderShellModel> data, Context context, int layoutRes) {
        super(data, context, layoutRes);

    }

    @Override
    public void bindData(ViewHolder holder, final OrderShellModel orderShellModel) {

        TextView order_item_detail_name = (TextView) holder.getView(R.id.order_item_detail_name);
        order_item_detail_name.setText(orderShellModel.getName());
        final EditText order_detail_item_price = (EditText) holder.getView(R.id.order_detail_item_price);
        order_detail_item_price.setText(orderShellModel.getPrice());
        TextView order_detail_item_size = (TextView) holder.getView(R.id.order_detail_item_size);
        order_detail_item_size.setText(orderShellModel.getSize());
        final TextView item_detail_order_price = (TextView) holder.getView(R.id.item_detail_order_price);
        order_detail_item_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    orderShellModel.setPrice(order_detail_item_price.getText().toString());
                    item_detail_order_price.setText(String.valueOf(Double.parseDouble(orderShellModel.getPrice()) * orderShellModel.getNum()));

                }
            }
        });
        final EditText order_detail_item_number = (EditText) holder.getView(R.id.order_detail_item_number);
        order_detail_item_number.setText(String.valueOf(orderShellModel.getNum()));
        order_detail_item_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    orderShellModel.setNum(Integer.parseInt(order_detail_item_number.getText().toString()));
                    item_detail_order_price.setText(String.valueOf(Double.parseDouble(orderShellModel.getPrice()) * orderShellModel.getNum()));

                }
            }
        });

        item_detail_order_price.setText(String.valueOf(Double.parseDouble(orderShellModel.getPrice()) * orderShellModel.getNum()));


    }
}
