package com.hj.casps.cooperate;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.WZYBaseAdapter;

import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by zy on 2017/4/25.
 */

public class CooperateContentsAdapter extends WZYBaseAdapter<CooperateModel> {
    private Context context;

    public CooperateContentsAdapter(List<CooperateModel> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
        this.context = context;

    }

    @Override
    public void bindData(ViewHolder holder, final CooperateModel cooperateModel) {

        final int[] grade_num = {cooperateModel.getGrade()};

        TextView name = (TextView) holder.getView(R.id.cooperate_name_contents);
        name.setText(cooperateModel.getName());
        TextView address = (TextView) holder.getView(R.id.cooperate_address_contents);
        address.setText(cooperateModel.getAddress());
        final TextView grade = (TextView) holder.getView(R.id.cooperate_grade_tv);
        grade.setText(String.valueOf(grade_num[0]));

        Button reduce = (Button) holder.getView(R.id.cooperate_grade_reduce);
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grade_num[0]--;
                grade.setText(String.valueOf(grade_num[0]));
                cooperateModel.setGrade(grade_num[0]);
            }
        });
        Button plus = (Button) holder.getView(R.id.cooperate_grade_plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grade_num[0]++;
                grade.setText(String.valueOf(grade_num[0]));
                cooperateModel.setGrade(grade_num[0]);
            }
        });
        FancyButton cancel_cooperate = (FancyButton) holder.getView(R.id.cancel_cooperate);
        cancel_cooperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        FancyButton upgrade_cooperate = (FancyButton) holder.getView(R.id.upgrade_cooperate);
        upgrade_cooperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
