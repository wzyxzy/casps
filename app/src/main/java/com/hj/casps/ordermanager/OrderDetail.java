package com.hj.casps.ordermanager;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.ui.MyListView;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail extends ActivityBaseHeader2 {

    private EditText order_detail_time_pay;
    private Spinner order_detail_process;
    private EditText order_detail_time_start;
    private EditText order_detail_time_end;
    private Spinner order_detail_pay_account;
    private Spinner order_detail_pay_address;
    private Spinner order_detail_get_account;
    private Spinner order_detail_get_address;
    private MyListView order_detail_add_layout;
    private TextView order_detail_num;
    private TextView order_detail_product_pay;
    private List<OrderShellModel> orders;
    private OrderShellDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initData();
        initView();
    }

    private void initData() {
        orders = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            OrderShellModel orderms = new OrderShellModel();
            orderms.setNum(233);
            orderms.setName("冷冻大肉" + String.valueOf(i));
            orderms.setPrice("100");
            orderms.setSize("400千克");
            orders.add(orderms);
        }
    }

    private void initView() {
        setTitle(getIntent().getStringExtra("title"));
        order_detail_time_pay = (EditText) findViewById(R.id.order_detail_time_pay);
        order_detail_process = (Spinner) findViewById(R.id.order_detail_process);
        order_detail_time_start = (EditText) findViewById(R.id.order_detail_time_start);
        order_detail_time_end = (EditText) findViewById(R.id.order_detail_time_end);
        order_detail_pay_account = (Spinner) findViewById(R.id.order_detail_pay_account);
        order_detail_pay_address = (Spinner) findViewById(R.id.order_detail_pay_address);
        order_detail_get_account = (Spinner) findViewById(R.id.order_detail_get_account);
        order_detail_get_address = (Spinner) findViewById(R.id.order_detail_get_address);
        order_detail_add_layout = (MyListView) findViewById(R.id.order_detail_add_layout);
        order_detail_num = (TextView) findViewById(R.id.order_detail_num);
        order_detail_product_pay = (TextView) findViewById(R.id.order_detail_product_pay);
        adapter = new OrderShellDetailAdapter(orders, this, R.layout.item_order_detail);
        order_detail_add_layout.setAdapter(adapter);
    }

    private void submit() {
        // validate
        String pay = order_detail_time_pay.getText().toString().trim();
        if (TextUtils.isEmpty(pay)) {
            Toast.makeText(this, "pay不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String start = order_detail_time_start.getText().toString().trim();
        if (TextUtils.isEmpty(start)) {
            Toast.makeText(this, "start不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String end = order_detail_time_end.getText().toString().trim();
        if (TextUtils.isEmpty(end)) {
            Toast.makeText(this, "end不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
