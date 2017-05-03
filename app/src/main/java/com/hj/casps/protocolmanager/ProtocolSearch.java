package com.hj.casps.protocolmanager;

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

public class ProtocolSearch extends ActivityBaseHeader2 implements View.OnClickListener {

    private Spinner protocol_type;
    private EditText protocol_object_text;
    private Spinner protocol_state;
    private LinearLayout protocol_state_ll;
    private FancyButton protocol_search;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_search);
        initData();
        initView();
    }

    private void initData() {
        type = getIntent().getIntExtra(Constant.PROTOCOL_TYPE, Constant.protocol_0);
    }

    private void initView() {
        setTitle(getString(R.string.search));
        protocol_type = (Spinner) findViewById(R.id.protocol_type);
        protocol_object_text = (EditText) findViewById(R.id.protocol_object_text);
        protocol_state = (Spinner) findViewById(R.id.protocol_state);
        protocol_state_ll = (LinearLayout) findViewById(R.id.protocol_state_ll);
        protocol_search = (FancyButton) findViewById(R.id.protocol_search);

        protocol_search.setOnClickListener(this);
        if (type != 2) {
            protocol_state_ll.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.protocol_search:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String text = protocol_object_text.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "text不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
