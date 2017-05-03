package com.hj.casps.adapter.overdealadapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.payadapter.CommonAdapter;
import com.hj.casps.adapter.payadapter.ViewHolder;
import com.hj.casps.common.Constant;
import com.hj.casps.entity.execcte.ExecuteGain;

import java.util.List;

/**
 * Created by Admin on 2017/5/2.
 * 执行中结款单列表详情
 */

public class ExexuteAdapter extends CommonAdapter<ExecuteGain> {

    public static onClickBillsListener onClickBillsListener;

    public static void setOnClickBillsListener(ExexuteAdapter.onClickBillsListener onClickBillsListener) {
        ExexuteAdapter.onClickBillsListener = onClickBillsListener;
    }

    public ExexuteAdapter(Context context, List<ExecuteGain> datas) {
        super(context, datas, R.layout.section_item);
        this.mContext = context;
    }

    @Override
    public void concert(ViewHolder hooder, final ExecuteGain executeGain, final int indexPos) {
        ExecuteGain.ExecuteBills executeBills = executeGain.getmList().get(indexPos);
        hooder.setText(R.id.tv_section_bills_id_content, executeBills.getSettleCode());
        hooder.setText(R.id.layout_1_tv_content, executeBills.getMmbpayName());
        hooder.setText(R.id.layout_2_tv_content, executeBills.getCtrTime());
        hooder.setText(R.id.layout_3_tv_content, executeBills.getSettleMoney() + "");
        hooder.setText(R.id.layout_4_tv_content, executeBills.getCtrMoney() + "");
        hooder.setText(R.id.layout_5_tv_content, executeBills.getGotMoney() + "");

        hooder.getView(R.id.relative_layout_1).setVisibility(View.VISIBLE);
        TextView tv_request_stop_title = hooder.getView(R.id.tv_request_stop_title);
        tv_request_stop_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBillsListener.onBtnClickListener(indexPos);
            }
        });
        hooder.getView(R.id.tv_section_bills_id_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBillsListener.onBillsIDItemCilckListener(indexPos);
            }
        });
        setBtnTitle(tv_request_stop_title, Integer.valueOf(executeBills.getStatus()));
    }

    private void setBtnTitle(TextView tv, int btnType) {
        switch (btnType) {
            case Constant.STATUS_TYPE_4:
                tv.setText(R.string.hint_tv_request_stop_title);
                break;
            case Constant.STATUS_TYPE_5:
                tv.setText(R.string.hint_status_type_title_8);
                break;
            case Constant.STATUS_TYPE_6:
                tv.setText(R.string.hint_status_type_title_9);
                break;

        }
    }

    public interface onClickBillsListener {
        void onBillsIDItemCilckListener(int pos);
        void onBtnClickListener(int pos);
    }

}