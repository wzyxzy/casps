package com.hj.casps.overdealmanager;

import android.os.Bundle;
import android.view.View;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * 创建结款单
 */
public class NewCreateSettlement extends ActivityBaseHeader2 implements View.OnClickListener {
    @BindView(R.id.create_setttlement_btn_next)
    FancyButton create_setttlement_btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_create_settlement);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setTitle(getString(R.string.hint_tv_create_section_bills_title));

        create_setttlement_btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_setttlement_btn_next:
                intentActivity(this, NewCreateSettlDetails.class);
                break;
        }
    }
}
