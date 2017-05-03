package com.hj.casps.ordermanager;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import java.util.ArrayList;
import java.util.List;

public class BuyShell extends ActivityBaseHeader2 implements View.OnClickListener {

    private TextView buy_shell_info;
    private TextView text_name_buy_shell;
    private TextView order_buy_name_shell;
    private TextView order_buy_num_shell;
    private ListView buy_cart_list_shell;
    private CheckBox select_all_shell;
    private TextView offline_shell_reset;
    private TextView order_buy_btn;
    private String buy_type;
    private String buy_name;
    private int buy_num;
    private List<OrderShellModel> orderShellModels;
    private OrderShellAdapter adapter;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_shell);
        initData();
        initView();
    }

    private void initData() {
        buy_type = getIntent().getStringExtra("buy_type");
        buy_name = getIntent().getStringExtra("buy_name");
        buy_num = getIntent().getIntExtra("buy_num", 0);
        switch (buy_type) {
            case "销售方：":
                setTitle(getString(R.string.order_sell_cart));
                state = 0;
                break;
            case "采购方：":
                setTitle(getString(R.string.order_buy_cart));
                state = 1;
                break;
        }
        orderShellModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OrderShellModel orderShellModel = new OrderShellModel();
            orderShellModel.setNum(0);
            orderShellModel.setChecked(false);
            orderShellModel.setStatus(false);
            orderShellModel.setName("冷冻大肉" + String.valueOf(i));
            orderShellModel.setPrice("￥25-￥30");
            orderShellModels.add(orderShellModel);

        }
    }

    private void initView() {
        buy_shell_info = (TextView) findViewById(R.id.buy_shell_info);
        text_name_buy_shell = (TextView) findViewById(R.id.text_name_buy_shell);
        order_buy_name_shell = (TextView) findViewById(R.id.order_buy_name_shell);
        order_buy_num_shell = (TextView) findViewById(R.id.order_buy_num_shell);
        buy_cart_list_shell = (ListView) findViewById(R.id.buy_cart_list_shell);
        select_all_shell = (CheckBox) findViewById(R.id.select_all_shell);
        offline_shell_reset = (TextView) findViewById(R.id.offline_shell_reset);
        order_buy_btn = (TextView) findViewById(R.id.order_buy_btn);
        text_name_buy_shell.setText(buy_type);
        order_buy_name_shell.setText(buy_name);
        order_buy_num_shell.setText(String.valueOf(buy_num));
        buy_shell_info.setOnClickListener(this);
        offline_shell_reset.setOnClickListener(this);
        order_buy_btn.setOnClickListener(this);
        adapter = new OrderShellAdapter(orderShellModels, this, R.layout.item_buy_shell, state);
        buy_cart_list_shell.setAdapter(adapter);
        select_all_shell.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (int i = 0; i < orderShellModels.size(); i++) {
                    orderShellModels.get(i).setChecked(b);
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_shell_info:

                break;
            case R.id.offline_shell_reset:
                for (int i = 0; i < orderShellModels.size(); i++) {
                    orderShellModels.get(i).setChecked(false);
                    orderShellModels.get(i).setNum(0);

                }
                adapter.notifyDataSetChanged();
                select_all_shell.setChecked(false);
                break;
            case R.id.order_buy_btn:
                bundle.putString("title", getString(R.string.order_detail_product_grid));
                intentActivity(OrderDetail.class, bundle);

                break;
        }
    }
}
