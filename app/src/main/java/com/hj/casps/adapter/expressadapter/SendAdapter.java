package com.hj.casps.adapter.expressadapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.hj.casps.R;
import com.hj.casps.adapter.payadapter.CommonAdapter;
import com.hj.casps.adapter.payadapter.ViewHolder;
import com.hj.casps.entity.PayMnetInfo;

import java.util.List;

/**
 * Created by Admin on 2017/4/18.
 */

public class SendAdapter extends CommonAdapter<PayMnetInfo> {
    private int index1 = -1;
    public static onCheckedkType onCheckedkType;

    public static void setOnCheckedkType(SendAdapter.onCheckedkType onCheckedkType) {
        SendAdapter.onCheckedkType = onCheckedkType;
    }

    public SendAdapter(Context context, List<PayMnetInfo> datas) {
        super(context, datas, R.layout.commodity_item);
        this.mContext = context;
    }

    @Override
    public void concert(final ViewHolder hooder, final PayMnetInfo payMnetInfo, final int indexPos) {
        hooder.setTextInt(R.id.tv_bank_name_content_title, R.string.hint_tv_deliver_title);
        hooder.setTextInt(R.id.tv_sum_money_title, R.string.hint_tv_sum_deliver_title);
        hooder.setTextInt(R.id.tv_already_money_title, R.string.hint_tv_send_already_deliver_title);
        hooder.setTextInt(R.id.tv_await_money_title, R.string.hint_tv_send_await_deliver_title);
        hooder.setTextInt(R.id.ed_now_money_title, R.string.hint_tv_send_new_express_title);

        final EditText moneyEd = hooder.getView(R.id.ed_now_money);
        if (moneyEd.getTag() instanceof TextWatcher) {
            moneyEd.removeTextChangedListener((TextWatcher) moneyEd.getTag());
            moneyEd.clearFocus();
        }
        TextWatcher moneyWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    payMnetInfo.setNow_money("");
                } else {
                    payMnetInfo.setNow_money(moneyEd.getText().toString().trim());
                }
            }
        };
        moneyEd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    index1 = indexPos;
                }
                return false;
            }
        });

        moneyEd.addTextChangedListener(moneyWatcher);
        moneyEd.setTag(moneyWatcher);

        hooder.getView(R.id.ed_payment_remark).setVisibility(View.GONE);
        hooder.getView(R.id.layout_payment_id_title).setVisibility(View.GONE);
//        hooder.setEdiTextView(R.id.ed_payment_remark, payMnetInfo.getPayment_remark());
        hooder.getView(R.id.tv_bills_id_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCheckedkType != null) {
                    onCheckedkType.onBillsIDItemCilckListener(indexPos);
                }
            }
        });

        final CheckBox checkBox = hooder.getView(R.id.payment_ck_bills);
        checkBox.setChecked(payMnetInfo.isChecked());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payMnetInfo.setChecked(checkBox.isChecked());
                if (checkBox.isChecked()) {
                    if (onCheckedkType != null)
                        onCheckedkType.onCheckedY(indexPos);
                } else {
                    if (onCheckedkType != null)
                        onCheckedkType.onCheckedN(indexPos);
                }
            }
        });

        hooder.getView(R.id.tv_payment_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payMnetInfo.setPayId(mDatas.get(indexPos).getTotalMoney());
                hooder.setEdiTextView(R.id.tv_payment_id, payMnetInfo.getPayId());
            }
        });

        hooder.setText(R.id.tv_bills_id_content, payMnetInfo.getBillsId());
        hooder.setText(R.id.tv_payment_title, payMnetInfo.getBuyer());
        hooder.setText(R.id.tv_bank_name_content, payMnetInfo.getBuyer());
        hooder.setText(R.id.tv_sum_money, payMnetInfo.getTotalMoney());
        hooder.setText(R.id.tv_already_money, payMnetInfo.getAlready_money());
        hooder.setText(R.id.tv_await_money, payMnetInfo.getAwait_money());
        hooder.setText(R.id.tv_payment_id, payMnetInfo.getPayId());
        hooder.setEdiTextView(R.id.ed_now_money, payMnetInfo.getNow_money());

        if (index1 != -1 && index1 == indexPos) {
            //强制加上焦点
            moneyEd.requestFocus();
            //设置光标显示到编辑框尾部
            moneyEd.setSelection(moneyEd.getText().length());
            //重置
            index1 = -1;
        }
    }

    public interface onCheckedkType {
        void onCheckedY(int pos);

        void onCheckedN(int pos);

        void onBillsIDItemCilckListener(int pos);
    }
}
