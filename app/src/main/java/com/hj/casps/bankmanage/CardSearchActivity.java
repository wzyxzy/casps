package com.hj.casps.bankmanage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.common.Constant;

/**
 * 银行账户查询
 */
public class CardSearchActivity extends ActivityBaseHeader2 {
    private EditText search_bank_name;
    private TextView search_bank_name_title;
    private int activityType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_search);
        initView();
        getBundleDatas();
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_card__search));
        titleRight.setVisibility(View.GONE);
        search_bank_name_title = (TextView) findViewById(R.id.search_bank_name_title);
        search_bank_name = (EditText) findViewById(R.id.search_bank_name);
        findViewById(R.id.search_card_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCardDatas();
            }

        });
    }

    /**
     * 请求数据查询
     */
    private void searchCardDatas() {

    }

    public void getBundleDatas() {
        activityType = getIntent().getIntExtra(Constant.BUNDLE_TYPE, 0);
        switch (activityType) {
            case Constant.BANK_BILLS_ACTIVITY_TYPE:
                search_bank_name_title.setText(getText(R.string.item_bank_name2));
                search_bank_name.setHint(getText(R.string.edhint_bank_name));
                break;
            case Constant.ECPRESS_ADDRESS_ACTIVITY_TYPE:
                search_bank_name_title.setText(getText(R.string.hint_tv_express_address_title));
                search_bank_name.setHint(getText(R.string.hint_ed_express_address_title));
                break;
        }
    }
}
