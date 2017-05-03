package com.hj.casps.cooperate;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.ui.MyGridView;

import mehdi.sakout.fancybuttons.FancyButton;

public class CooperateCreate extends ActivityBaseHeader2 implements View.OnClickListener {
    private boolean buy_type;
    private TextView cooperate_create_info;
    private TextView cooperate_type;
    private TextView cooperate_buy_role;
    private TextView cooperate_sell_role;
    private FancyButton cooperate_products_list;
    private MyGridView cooperate_create_gv;
    private TextView cooperate_protocol_object;
    private TextView cooperate_protocol_time;
    private EditText cooperate_protocol_title;
    private TextView cooperate_protocol_valid_time;
    private Spinner cooperate_protocol_settle_rule;
    private Spinner cooperate_protocol_ship_rule;
    private Spinner cooperate_products_transport_mode;
    private Spinner cooperate_products_buy_account;
    private Spinner cooperate_products_ship_address;
    private Spinner cooperate_products_receipt_account;
    private Spinner cooperate_products_delivery_address;
    private TextView cooperate_products_remarks;
    private FancyButton cooperate_create_submit;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_create);
        initData();
        initView();
    }

    private void initData() {
        buy_type = getIntent().getBooleanExtra("buy_type", true);
        type = getIntent().getIntExtra("type", 0);



    }

    private void initView() {


        cooperate_create_info = (TextView) findViewById(R.id.cooperate_create_info);
        cooperate_create_info.setOnClickListener(this);
        cooperate_type = (TextView) findViewById(R.id.cooperate_type);
        cooperate_type.setOnClickListener(this);
        if (buy_type) {
            cooperate_type.setText(R.string.cooperate_products_buy);
        } else {
            cooperate_type.setText(R.string.cooperate_products_sell);

        }
        cooperate_buy_role = (TextView) findViewById(R.id.cooperate_buy_role);
        cooperate_buy_role.setOnClickListener(this);
        cooperate_sell_role = (TextView) findViewById(R.id.cooperate_sell_role);
        cooperate_sell_role.setOnClickListener(this);
        cooperate_products_list = (FancyButton) findViewById(R.id.cooperate_products_list);
        cooperate_products_list.setOnClickListener(this);
        cooperate_create_gv = (MyGridView) findViewById(R.id.cooperate_create_gv);
        cooperate_protocol_object = (TextView) findViewById(R.id.cooperate_protocol_object);
        cooperate_protocol_object.setOnClickListener(this);
        cooperate_protocol_time = (TextView) findViewById(R.id.cooperate_protocol_time);
        cooperate_protocol_time.setOnClickListener(this);
        cooperate_protocol_title = (EditText) findViewById(R.id.cooperate_protocol_title);
        cooperate_protocol_title.setOnClickListener(this);
        cooperate_protocol_valid_time = (TextView) findViewById(R.id.cooperate_protocol_valid_time);
        cooperate_protocol_valid_time.setOnClickListener(this);
        cooperate_protocol_settle_rule = (Spinner) findViewById(R.id.cooperate_protocol_settle_rule);
        cooperate_protocol_ship_rule = (Spinner) findViewById(R.id.cooperate_protocol_ship_rule);
        cooperate_products_transport_mode = (Spinner) findViewById(R.id.cooperate_products_transport_mode);
        cooperate_products_buy_account = (Spinner) findViewById(R.id.cooperate_products_buy_account);
        cooperate_products_ship_address = (Spinner) findViewById(R.id.cooperate_products_ship_address);
        cooperate_products_receipt_account = (Spinner) findViewById(R.id.cooperate_products_receipt_account);
        cooperate_products_delivery_address = (Spinner) findViewById(R.id.cooperate_products_delivery_address);
        cooperate_products_remarks = (TextView) findViewById(R.id.cooperate_products_remarks);
        cooperate_products_remarks.setOnClickListener(this);
        cooperate_create_submit = (FancyButton) findViewById(R.id.cooperate_create_submit);
        cooperate_create_submit.setOnClickListener(this);
        switch (type){
            case 0:
                setTitle(getString(R.string.cooperate_create_protocol));

                break;
            case 1:
                setTitle(getString(R.string.cooperate_edit_protocol));

                break;
            case 2:
                setTitle(getString(R.string.cooperate_edit_protocol));
                cooperate_create_submit.setText(getString(R.string.protocol_get_order));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cooperate_create_info:

                break;
            case R.id.cooperate_products_list:
                intentActivity(this, ProtocalProductItem.class);
                break;
            case R.id.cooperate_create_submit:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String title = cooperate_protocol_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "title不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
