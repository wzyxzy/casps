package com.hj.casps.operatormanager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * 设置密码
 */
public class OperatorPassWord extends ActivityBaseHeader2 implements View.OnClickListener {
    private FancyButton affirm_pword_btn;
    private EditText operator_ed_pword, operator_ed_affirmpword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_pword);

        initView();
    }

    private void initView() {
        setTitle(getString(R.string.hint_layout_right_btn_title));
        titleRight.setVisibility(View.GONE);
        affirm_pword_btn = (FancyButton) findViewById(R.id.affirm_pword_btn);
        affirm_pword_btn.setOnClickListener(this);
        operator_ed_pword = (EditText) findViewById(R.id.operator_ed_pword);
        operator_ed_affirmpword = (EditText) findViewById(R.id.operator_ed_affirmpword);
    }

    @Override
    public void onClick(View v) {

    }
}
