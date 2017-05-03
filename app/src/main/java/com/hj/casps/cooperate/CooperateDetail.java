package com.hj.casps.cooperate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.ui.MyDialog;

import mehdi.sakout.fancybuttons.FancyButton;

public class CooperateDetail extends ActivityBaseHeader2 implements View.OnClickListener {
    private String name;
    private TextView cooperate_person_name;
    private TextView cooperate_address;
    private FancyButton cooperate_create_buy;
    private FancyButton cooperate_create_sell;
    private FancyButton cooperate_detail_submit;
    private ToggleButton toggle_coop_buy;
    private ToggleButton toggle_coop_sell;
    private MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_detail);
        initData();
        initView();
    }

    private void initView() {

        setTitle(getString(R.string.cooperate_detail));
//        titleRight.setVisibility(View.GONE);
        cooperate_person_name = (TextView) findViewById(R.id.cooperate_person_name);
        cooperate_person_name.setText(name);
        cooperate_address = (TextView) findViewById(R.id.cooperate_address);
        cooperate_create_buy = (FancyButton) findViewById(R.id.cooperate_create_buy);
        cooperate_create_buy.setOnClickListener(this);
        cooperate_create_sell = (FancyButton) findViewById(R.id.cooperate_create_sell);
        cooperate_create_sell.setOnClickListener(this);
        cooperate_detail_submit = (FancyButton) findViewById(R.id.cooperate_detail_submit);
        cooperate_detail_submit.setOnClickListener(this);
        toggle_coop_buy = (ToggleButton) findViewById(R.id.toggle_coop_buy);
        toggle_coop_buy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cooperate_create_buy.setVisibility(View.VISIBLE);
                } else {
                    cooperate_create_buy.setVisibility(View.INVISIBLE);
                }
            }
        });
        toggle_coop_sell = (ToggleButton) findViewById(R.id.toggle_coop_sell);
        toggle_coop_sell.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cooperate_create_sell.setVisibility(View.VISIBLE);
                } else {
                    cooperate_create_sell.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CooperateCreate.class);
        switch (v.getId()) {
            case R.id.cooperate_create_buy:
                intent.putExtra("buy_type", true);
                startActivity(intent);
                break;
            case R.id.cooperate_create_sell:
                intent.putExtra("buy_type", false);
                startActivity(intent);
                break;
            case R.id.cooperate_detail_submit:
                showDialog();
                break;

        }
    }

    private void showDialog() {
        myDialog = new MyDialog(this);
        myDialog.setMessage(getString(R.string.cooperate_downgrade_dialog));
        myDialog.setYesOnclickListener(getString(R.string.True), new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                myDialog.dismiss();
                showOKDialog();
            }
        });
        myDialog.setNoOnclickListener(getString(R.string.cancel), new MyDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    private void showOKDialog() {
        myDialog = new MyDialog(this);
        myDialog.setMessage(getString(R.string.cooperate_downgrade_OK));
        myDialog.setYesOnclickListener(getString(R.string.True), new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                myDialog.dismiss();
//                showOKDialog();
            }
        });
        myDialog.show();
        myDialog.setButtonVisible(true, false);
        myDialog.setTextLeftImg(R.mipmap.uc_prompt2,this);

    }
}
