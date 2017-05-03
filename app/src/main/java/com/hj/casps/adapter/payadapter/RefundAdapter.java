package com.hj.casps.adapter.payadapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.hj.casps.R;
import com.hj.casps.entity.PayMnetInfo;

import java.util.List;

/**
 * Created by Admin on 2017/4/18.
 * 收款列表adapter
 */

public class RefundAdapter extends CommonAdapter<PayMnetInfo> {
    private int index1 = -1;
    private int index2 = -1;
    public static onCheckedkType onCheckedkType;

    public static void setOnCheckedkType(RefundAdapter.onCheckedkType onCheckedkType) {
        RefundAdapter.onCheckedkType = onCheckedkType;
    }

    public RefundAdapter(Context context, List<PayMnetInfo> datas) {
        super(context, datas, R.layout.refund_item);
        this.mContext = context;
    }

    @Override
    public void concert(final ViewHolder hooder, final PayMnetInfo payMnetInfo, final int indexPos) {

        final EditText remarkEd = hooder.getView(R.id.ed_refund_remark_content);
        if (remarkEd.getTag() instanceof TextWatcher) {
            remarkEd.removeTextChangedListener((TextWatcher) remarkEd.getTag());
            remarkEd.clearFocus();
        }
        TextWatcher remarkWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    payMnetInfo.setPayId("");
                } else {
                    payMnetInfo.setPayId(remarkEd.getText().toString().trim());
                }
            }
        };
        remarkEd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    index2 = indexPos + 1;
                }
                return false;
            }
        });
        remarkEd.addTextChangedListener(remarkWatcher);
        remarkEd.setTag(remarkWatcher);

        final EditText moneyEd = hooder.getView(R.id.ed_refund_money_content);
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

        //订单号
        hooder.getView(R.id.tv_refund_bills_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCheckedkType != null) {
                    onCheckedkType.onBillsIDItemCilckListener(indexPos);
                }
            }
        });

        //多选框
        final CheckBox checkBox = hooder.getView(R.id.ck_refund_bills);
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

        hooder.setText(R.id.tv_refund_bills_id, payMnetInfo.getBillsId());
        hooder.setText(R.id.tv_refund_title_one, payMnetInfo.getBuyer());
        hooder.setText(R.id.tv_refund_payee_content, payMnetInfo.getTotalMoney());
        hooder.setText(R.id.tv_refund_money_content, payMnetInfo.getAwait_money());
        hooder.setEdiTextView(R.id.ed_refund_remark_content, payMnetInfo.getPayId());
        hooder.setEdiTextView(R.id.ed_refund_money_content, payMnetInfo.getNow_money());
        hooder.setText(R.id.ed_payment_card_id_content, payMnetInfo.getNow_money());

        if (index1 != -1 && index1 == indexPos) {
            //强制加上焦点
            moneyEd.requestFocus();
            //设置光标显示到编辑框尾部
            moneyEd.setSelection(moneyEd.getText().length());
            //重置
            index1 = -1;
        }

        if (index2 != -1 && index2 == indexPos + 1) {
            //强制加上焦点
            remarkEd.requestFocus();
            //设置光标显示到编辑框尾部
            remarkEd.setSelection(remarkEd.getText().length());
            //重置
            index2 = -1;
        }
    }


    public interface onCheckedkType {
        void onCheckedY(int pos);

        void onCheckedN(int pos);

        void onBillsIDItemCilckListener(int pos);
    }
}
