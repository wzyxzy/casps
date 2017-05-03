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

public class CooperateGroupAdapter extends WZYBaseAdapter<CooperateGroupModel> {
    private Context context;

    public CooperateGroupAdapter(List<CooperateGroupModel> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
        this.context = context;

    }

    @Override
    public void bindData(ViewHolder holder, CooperateGroupModel cooperateModel) {

        TextView name = (TextView) holder.getView(R.id.cooperate_group_name);
        name.setText(cooperateModel.getName());
        FancyButton state = (FancyButton) holder.getView(R.id.cooperate_group_state);
        switch (cooperateModel.getState()) {
            case 0:

                break;
            case 1:
                state.setText(context.getString(R.string.cooperate_group_add));
                state.setBackgroundColor(context.getResources().getColor(R.color.title_bg));
                state.setTextColor(context.getResources().getColor(R.color.white));
                state.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new MyToast(context, context.getString(R.string.cooperate_group_grant));

                    }
                });
                break;
            case 2:
                state.setText(context.getString(R.string.cooperate_group_gone));
                state.setBackgroundColor(context.getResources().getColor(R.color.white));
                state.setTextColor(context.getResources().getColor(R.color.grey));
                state.setBorderColor(context.getResources().getColor(R.color.hint_color));
                state.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final MyDialog myDialog = new MyDialog(context);
                        myDialog.setMessage(context.getString(R.string.cooperate_dialog_agree));
                        myDialog.setYesOnclickListener(context.getString(R.string.True), new MyDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                myDialog.dismiss();
                                new MyToast(context, context.getString(R.string.cooperate_dialog_gone_ok));
                            }
                        });
                        myDialog.setNoOnclickListener(context.getString(R.string.cancel), new MyDialog.onNoOnclickListener() {
                            @Override
                            public void onNoClick() {
                                myDialog.dismiss();
                            }
                        });
                        myDialog.show();
                    }
                });

                break;
        }


    }
}
