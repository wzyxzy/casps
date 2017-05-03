package com.hj.casps.adapter.operatoradapter;

import android.content.Context;

import com.hj.casps.R;
import com.hj.casps.adapter.payadapter.CommonAdapter;
import com.hj.casps.adapter.payadapter.ViewHolder;
import com.hj.casps.entity.OperatorEntity;

import java.util.List;

/**
 * Created by Admin on 2017/4/18.
 * 操作员管理adapter
 */

public class OperatorAdapter extends CommonAdapter<OperatorEntity> {

    public OperatorAdapter(Context context, List<OperatorEntity> datas) {
        super(context, datas, R.layout.operator_item);
        this.mContext = context;
    }

    @Override
    public void concert(ViewHolder hooder, final OperatorEntity operatorEntity, final int indexPos) {
        hooder.setText(R.id.operator_tv_tlile, operatorEntity.getAccountName());
        hooder.setText(R.id.operator_account_tlile, operatorEntity.getAccountID());
    }
}
