package com.hj.casps.adapter.overdealadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.payadapter.CommonAdapter;
import com.hj.casps.adapter.payadapter.ViewHolder;
import com.hj.casps.entity.registerassure.RegisterAssureGain;

import java.util.List;

/**
 * Created by Admin on 2017/5/2.
 * 执行中结款单列表详情
 */

public class RegisterAssuerAdapter extends CommonAdapter<RegisterAssureGain> {

    public static onClickBillsListener onClickBillsListener;

    public static void setOnClickBillsListener(RegisterAssuerAdapter.onClickBillsListener onClickBillsListener) {
        RegisterAssuerAdapter.onClickBillsListener = onClickBillsListener;
    }

    public RegisterAssuerAdapter(Context context, List<RegisterAssureGain> datas) {
        super(context, datas, R.layout.section_item);
        this.mContext = context;
    }

    @Override
    public void concert(ViewHolder hooder, final RegisterAssureGain assureGain, final int indexPos) {
        RegisterAssureGain.AssureBills assureBills = assureGain.getmList().get(indexPos);
        hooder.setText(R.id.tv_section_bills_id_content, assureBills.getSettleCode());
        hooder.setTextInt(R.id.layout_1_tv_title, R.string.hint_status_regist_assure_title_1);
        hooder.setText(R.id.layout_1_tv_content, assureBills.getMmbpayName());
        hooder.setTextInt(R.id.layout_2_tv_title, R.string.hint_status_regist_assure_title_2);
        hooder.setText(R.id.layout_2_tv_content, assureBills.getCtrTime());
        hooder.setTextInt(R.id.layout_3_tv_title, R.string.hint_status_regist_assure_title_3);
        hooder.setText(R.id.layout_3_tv_content, assureBills.getSettleMoney() + "");
        hooder.setTextInt(R.id.layout_4_tv_title, R.string.hint_status_regist_assure_title_4);
        hooder.setText(R.id.layout_4_tv_content, assureBills.getCtrMoney() + "");
        hooder.setTextInt(R.id.layout_5_tv_title, R.string.hint_status_regist_assure_title_5);
        hooder.setText(R.id.layout_5_tv_content, assureBills.getGotMoney() + "");

        if (Integer.valueOf(assureBills.getStatusRegist()) == 1) {
            hooder.getView(R.id.relative_layout_1).setVisibility(View.VISIBLE);
            TextView tv_request_stop_title = hooder.getView(R.id.tv_request_stop_title);
            tv_request_stop_title.setBackgroundResource(R.drawable.btn_style);
            tv_request_stop_title.setText(R.string.hint_status_regist_assure_title_6);
            tv_request_stop_title.setTextColor(Color.WHITE);
            tv_request_stop_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickBillsListener.onBtnClickListener(indexPos);
                }
            });
        } else {
            hooder.getView(R.id.relative_layout_1).setVisibility(View.GONE);
            hooder.getView(R.id.tv_commit_apply_title).setVisibility(View.VISIBLE);
        }


        hooder.getView(R.id.tv_section_bills_id_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBillsListener.onBillsIDItemCilckListener(indexPos);
            }
        });

    }

    public interface onClickBillsListener {
        void onBillsIDItemCilckListener(int pos);

        void onBtnClickListener(int pos);
    }

}