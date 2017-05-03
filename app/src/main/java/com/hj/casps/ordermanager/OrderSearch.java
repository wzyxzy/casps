package com.hj.casps.ordermanager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.common.Constant;

import mehdi.sakout.fancybuttons.FancyButton;

public class OrderSearch extends ActivityBaseHeader2 implements View.OnClickListener {

    private EditText order_buy_id;
    private Spinner order_buy_type;
    private Spinner order_buy_state;
    private EditText order_doing_object_text;
    private EditText order_time_from;
    private EditText order_time_to;
    private FancyButton order_search;
    private int type;
    private LinearLayout order_state_layout;
    private LinearLayout order_time_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_search);
        initData();
        initView();
    }

    private void initData() {
        type = getIntent().getIntExtra(Constant.PROTOCOL_TYPE, Constant.protocol_0);
    }

    private void initView() {
        order_buy_id = (EditText) findViewById(R.id.order_buy_id);
        order_buy_type = (Spinner) findViewById(R.id.order_buy_type);
        order_buy_state = (Spinner) findViewById(R.id.order_buy_state);
        order_doing_object_text = (EditText) findViewById(R.id.order_doing_object_text);
        order_time_from = (EditText) findViewById(R.id.order_time_from);
        order_time_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendar(order_time_from);
            }
        });
        order_time_to = (EditText) findViewById(R.id.order_time_to);
        order_search = (FancyButton) findViewById(R.id.order_search);

        order_search.setOnClickListener(this);
        order_state_layout = (LinearLayout) findViewById(R.id.order_state_layout);
        order_time_layout = (LinearLayout) findViewById(R.id.order_time_layout);
        if (type != 2) {
            order_state_layout.setVisibility(View.GONE);
            order_time_layout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_search:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String id = order_buy_id.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "id不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String text = order_doing_object_text.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "text不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String from = order_time_from.getText().toString().trim();
        if (TextUtils.isEmpty(from)) {
            Toast.makeText(this, "from不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String to = order_time_to.getText().toString().trim();
        if (TextUtils.isEmpty(to)) {
            Toast.makeText(this, "to不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
