
package com.hj.casps.bankmanage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.common.Constant;
import com.hj.casps.entity.CardInfoEntity;
import com.hj.casps.entity.ExpressInfoEntity;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by zdd on 2017/4/17.
 * 账户修改
 */

public class EditCardActivity extends ActivityBaseHeader2 implements View.OnClickListener {
    private CardInfoEntity cardInfo;
    private ExpressInfoEntity expressInfoEntity;
    private TextView account_name_title, account_number_title, bank_name_title,
            contacts_name_title, mobile_number_title, telephone_number_title;
    private EditText account_name, account_number, bank_name,
            contacts_name, mobile_number, telephone_number;


    private FancyButton add_card_btn;
    private int card_type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_card_info);
        initView();
    }

    private void initView() {
        card_type = getIntent().getIntExtra(Constant.CARD_TYPE, 0);

        titleRight.setVisibility(View.GONE);

        account_name_title = (TextView) findViewById(R.id.ed_account_name_title);
        account_number_title = (TextView) findViewById(R.id.ed_account_number_title);
        bank_name_title = (TextView) findViewById(R.id.ed_bank_name_title);
        contacts_name_title = (TextView) findViewById(R.id.ed_contacts_name_title);
        mobile_number_title = (TextView) findViewById(R.id.ed_mobile_number_title);
        telephone_number_title = (TextView) findViewById(R.id.ed_telephone_number_title);

        account_name = (EditText) findViewById(R.id.ed_account_name);
        account_number = (EditText) findViewById(R.id.ed_account_number);
        bank_name = (EditText) findViewById(R.id.ed_bank_name);
        contacts_name = (EditText) findViewById(R.id.ed_contacts_name);
        mobile_number = (EditText) findViewById(R.id.ed_mobile_number);
        telephone_number = (EditText) findViewById(R.id.ed_telephone_number);
        add_card_btn = (FancyButton) findViewById(R.id.add_card_btn);
        add_card_btn.setOnClickListener(this);

        //判断哪个界面进去的
        setInitView(card_type);
    }

    private void setInitView(int numb) {
        switch (numb) {
            case Constant.CARD_EDIT:
                setTitle(getString(R.string.activity_title_card_edit_tv));
                cardInfo = (CardInfoEntity) getIntent().getSerializableExtra(Constant.BUNDLE_TYPE);
                //修改
                if (cardInfo != null) {
                    setContentValue(cardInfo.getAccountname(), cardInfo.getAccountno(),
                            cardInfo.getBankname(), cardInfo.getContact(),
                            cardInfo.getMobilephone(), cardInfo.getPhone(),
                            Constant.CARD_EDIT);
                }
                break;
            case Constant.CARD_ADD:
                setTitle(getString(R.string.activity_title_card_add_tv));
                setResult(Constant.CARD_ADD);
                break;
            case Constant.ADDRESS_EDIT:
                setTitle(getString(R.string.activity_express_edit_title));
                setInitView();
                expressInfoEntity = (ExpressInfoEntity) getIntent().getSerializableExtra(Constant.BUNDLE_TYPE);
                //修改
                if (expressInfoEntity != null) {
                    setContentValue(expressInfoEntity.getAccountname(), expressInfoEntity.getAccountno(),
                            expressInfoEntity.getBankname(), expressInfoEntity.getContact(),
                            expressInfoEntity.getMobilephone(), expressInfoEntity.getPhone(),
                            Constant.ADDRESS_EDIT);
                }
                break;
            case Constant.ADDRESS_ADD:
                setTitle(getString(R.string.activity_express_add_title));
                setInitView();
                setResult(Constant.ADDRESS_ADD);
                break;
        }
    }


    /**
     * 设置默认显示
     */
    private void setInitView() {
        account_name_title.setText(R.string.hint_tv_express_address_title);
        account_number_title.setText(R.string.hint_tv_express_area_title);
        bank_name_title.setText(R.string.hint_tv_express_postcode_title);
        bank_name_title.setText(R.string.hint_tv_express_contacts_title);
        contacts_name_title.setText(R.string.hint_tv_express_mobile_title);
        mobile_number_title.setText(R.string.hint_tv_express_phone_title);
        telephone_number_title.setText(R.string.hint_tv_express_phone_title);

        account_name.setHint(R.string.hint_ed_express_address_title);
        account_number.setHint(R.string.hint_ed_express_area_title);
        bank_name.setHint(R.string.hint_ed_express_postcode_title);
        contacts_name.setHint(R.string.hint_ed_express_contacts_title);
        mobile_number.setHint(R.string.hint_ed_express_mobile_title);
        telephone_number.setHint(R.string.hint_ed_express_phone_title);
    }

    /**
     * 设置内容
     *
     * @param accountname
     * @param accountno
     * @param bankname
     * @param contact
     * @param mobilephone
     * @param phone
     * @param result_type
     */
    private void setContentValue(String accountname, String accountno, String bankname,
                                 String contact, String mobilephone, String phone, int result_type) {
        account_name.setText(accountname);
        account_number.setText(accountno);
        bank_name.setText(bankname);
        contacts_name.setText(contact);
        mobile_number.setText(mobilephone);
        telephone_number.setText(phone);
        setResult(result_type);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_card_btn:
                saveEditInfo();
                break;
        }
    }

    /**
     * 保存修改或者新增的信息
     */
    private void saveEditInfo() {
        switch (card_type) {
            case Constant.CARD_EDIT:
                saveCardInfo();
                break;
            case Constant.CARD_ADD:
                saveCardInfo();
                break;
            case Constant.ADDRESS_EDIT:
                saveExpressInfo();
                break;
            case Constant.ADDRESS_ADD:
                saveExpressInfo();
                break;
        }
    }

    private void saveCardInfo() {
        Constant.cardInfoEntity = new CardInfoEntity(
                getEdVaule(account_name),
                getEdVaule(account_number),
                getEdVaule(bank_name),
                getEdVaule(contacts_name),
                getEdVaule(mobile_number),
                getEdVaule(telephone_number)
        );
    }

    private void saveExpressInfo() {
        Constant.expressInfoEntity = new ExpressInfoEntity(
                getEdVaule(account_name),
                getEdVaule(account_number),
                getEdVaule(bank_name),
                getEdVaule(contacts_name),
                getEdVaule(mobile_number),
                getEdVaule(telephone_number)
        );
    }


    private String getEdVaule(EditText editText) {
        return editText.getText().toString().trim();
    }


}
