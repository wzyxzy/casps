package com.hj.casps.protocolmanager;

import android.os.Bundle;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

public class ProtocolDetail extends ActivityBaseHeader2 {

    private TextView protocol_contract_title;
    private TextView protocol_contract_type;
    private TextView protocol_buy_membername;
    private TextView protocol_sell_membername;
    private TextView protocol_operate_user;
    private TextView protocol_operate_time;
    private TextView protocol_user_time;
    private TextView protocol_pay_type;
    private TextView protocol_flow_type;
    private TextView protocol_sendgoods_type;
    private TextView protocol_payer_code;
    private TextView protocol_getgoods_address;
    private TextView protocol_goods;
    private TextView protocol_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_detail);
        initView();
    }

    private void initView() {
        setTitle(getString(R.string.protocol_detail));
        protocol_contract_title = (TextView) findViewById(R.id.protocol_contract_title);
        protocol_contract_type = (TextView) findViewById(R.id.protocol_contract_type);
        protocol_buy_membername = (TextView) findViewById(R.id.protocol_buy_membername);
        protocol_sell_membername = (TextView) findViewById(R.id.protocol_sell_membername);
        protocol_operate_user = (TextView) findViewById(R.id.protocol_operate_user);
        protocol_operate_time = (TextView) findViewById(R.id.protocol_operate_time);
        protocol_user_time = (TextView) findViewById(R.id.protocol_user_time);
        protocol_pay_type = (TextView) findViewById(R.id.protocol_pay_type);
        protocol_flow_type = (TextView) findViewById(R.id.protocol_flow_type);
        protocol_sendgoods_type = (TextView) findViewById(R.id.protocol_sendgoods_type);
        protocol_payer_code = (TextView) findViewById(R.id.protocol_payer_code);
        protocol_getgoods_address = (TextView) findViewById(R.id.protocol_getgoods_address);
        protocol_goods = (TextView) findViewById(R.id.protocol_goods);
        protocol_note = (TextView) findViewById(R.id.protocol_note);
    }
}
