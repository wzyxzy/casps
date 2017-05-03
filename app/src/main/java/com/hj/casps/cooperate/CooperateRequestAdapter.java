package com.hj.casps.cooperate;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.WZYBaseAdapter;
import com.hj.casps.ui.MyDialog;
import com.hj.casps.ui.MyToast;

import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by zy on 2017/4/25.
 */

public class CooperateRequestAdapter extends WZYBaseAdapter<CooperateRequstModel> {
    private Context context;
    private MyDialog myDialog;

    public CooperateRequestAdapter(List<CooperateRequstModel> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
        this.context = context;
    }

    @Override
    public void bindData(ViewHolder holder, CooperateRequstModel cooperateRequstModel) {
        TextView name = (TextView) holder.getView(R.id.cooperate_name_request);
        name.setText(cooperateRequstModel.getName());
        TextView relation = (TextView) holder.getView(R.id.cooperate_relation_request);
        relation.setText(cooperateRequstModel.getRelation());
        TextView request_num = (TextView) holder.getView(R.id.cooperate_request_num);
        request_num.setText(String.valueOf(cooperateRequstModel.getNum()));
        FancyButton agree = (FancyButton) holder.getView(R.id.cooperate_request_agree);
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(true);
            }
        });
        FancyButton deny = (FancyButton) holder.getView(R.id.cooperate_request_deny);
        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(false);
            }
        });

    }

    private void showDialog(boolean agree) {
        myDialog = new MyDialog(context);
        if (agree){
            myDialog.setMessage(context.getString(R.string.cooperate_dialog_agree));
            myDialog.setYesOnclickListener(context.getString(R.string.True), new MyDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    myDialog.dismiss();
                    new MyToast(context,context.getString(R.string.cooperate_dialog_ok));

//                    showOKDialog();
                }
            });
        }else {
            myDialog.setMessage(context.getString(R.string.cooperate_dialog_deny));
            myDialog.setYesOnclickListener(context.getString(R.string.True), new MyDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    myDialog.dismiss();
                    new MyToast(context,context.getString(R.string.cooperate_dialog_ok));
//                    showOKDialog();
                }
            });
        }

        myDialog.setNoOnclickListener(context.getString(R.string.cancel), new MyDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}
