package com.hj.casps.protocolmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import mehdi.sakout.fancybuttons.FancyButton;

public class ProtocolAgree extends ActivityBaseHeader2 implements View.OnClickListener {

    private Spinner protocol_receipt_account;
    private Spinner protocol_delivery_address;
    private FancyButton protocol_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_agree);
        initView();
    }

    private void initView() {
        setTitle(getString(R.string.protocol_agree));
        protocol_receipt_account = (Spinner) findViewById(R.id.protocol_receipt_account);
        protocol_delivery_address = (Spinner) findViewById(R.id.protocol_delivery_address);
        protocol_sure = (FancyButton) findViewById(R.id.protocol_sure);

        protocol_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.protocol_sure:

                break;
        }
    }
}
