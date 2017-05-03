package com.hj.casps.ordermanager;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.WZYBaseAdapter;

import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by zy on 2017/4/27.
 */

public class OrderShellAdapter extends WZYBaseAdapter<OrderShellModel> {
    private int state;

    public OrderShellAdapter(List<OrderShellModel> data, Context context, int layoutRes, int state) {
        super(data, context, layoutRes);
        this.state = state;
    }

    @Override
    public void bindData(ViewHolder holder, final OrderShellModel orderShellModel) {

        CheckBox shell_ck_buy = (CheckBox) holder.getView(R.id.shell_ck_buy);
        shell_ck_buy.setChecked(orderShellModel.isChecked());
        orderShellModel.setChecked(shell_ck_buy.isChecked());
        TextView buy_shell_name = (TextView) holder.getView(R.id.buy_shell_name);
        buy_shell_name.setText(orderShellModel.getName());
        TextView buy_shell_price = (TextView) holder.getView(R.id.buy_shell_price);
        buy_shell_price.setText(orderShellModel.getPrice());
        EditText buy_shell_num = (EditText) holder.getView(R.id.buy_shell_num);
        buy_shell_num.setText(String.valueOf(orderShellModel.getNum()));
        orderShellModel.setNum(Integer.parseInt(buy_shell_num.getText().toString().trim()));
        TextView delete_buy_shell = (TextView) holder.getView(R.id.delete_buy_shell);
        RelativeLayout buy_shell_item_relative1 = (RelativeLayout) holder.getView(R.id.buy_shell_item_relative1);
        if (state == 0) {
            delete_buy_shell.setVisibility(View.GONE);
            buy_shell_item_relative1.setVisibility(View.VISIBLE);
        } else {
            delete_buy_shell.setVisibility(View.VISIBLE);
            buy_shell_item_relative1.setVisibility(View.GONE);


        }
        delete_buy_shell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //网络请求去删除
            }
        });
        final TextView view_status_product = (TextView) holder.getView(R.id.view_status_product);
        if (orderShellModel.isStatus()) {
            view_status_product.setVisibility(View.VISIBLE);
        } else {
            view_status_product.setVisibility(View.GONE);

        }
        FancyButton status_buy_shell_product = (FancyButton) holder.getView(R.id.status_buy_shell_product);
        status_buy_shell_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                orderShellModel.setStatus(true);
                view_status_product.setVisibility(View.VISIBLE);

            }
        });
        FancyButton buy_shell_delete = (FancyButton) holder.getView(R.id.buy_shell_delete);
        buy_shell_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                orderShellModel.setStatus(false);
                view_status_product.setVisibility(View.GONE);

            }
        });

    }
}
