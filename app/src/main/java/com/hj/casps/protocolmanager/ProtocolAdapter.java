package com.hj.casps.protocolmanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.WZYBaseAdapter;
import com.hj.casps.cooperate.CooperateCreate;
import com.hj.casps.ui.MyToast;

import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by zy on 2017/4/25.
 */

public class ProtocolAdapter extends WZYBaseAdapter<ProtocolModel> {
    private Context context;

    public ProtocolAdapter(List<ProtocolModel> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
        this.context = context;
    }

    @Override
    public void bindData(ViewHolder holder, ProtocolModel protocolModel) {
        TextView protocol_title = (TextView) holder.getView(R.id.protocol_title);
        protocol_title.setText(protocolModel.getName());
        protocol_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProtocolDetail.class);
                context.startActivity(intent);
            }
        });
        TextView protocol_object = (TextView) holder.getView(R.id.protocol_object);
        protocol_object.setText(protocolModel.getObject());
        TextView protocol_transport_type = (TextView) holder.getView(R.id.protocol_transport_type);
        protocol_transport_type.setText(protocolModel.getTransport());
        TextView protocol_buy_time = (TextView) holder.getView(R.id.protocol_buy_time);
        protocol_buy_time.setText(protocolModel.getPay_term());
        TextView protocol_begin_time = (TextView) holder.getView(R.id.protocol_begin_time);
        protocol_begin_time.setText(protocolModel.getBegin());
        TextView protocol_end_time = (TextView) holder.getView(R.id.protocol_end_time);
        protocol_end_time.setText(protocolModel.getEnd());
        TextView protocol_waiting_sure = (TextView) holder.getView(R.id.protocol_waiting_sure);
        if (protocolModel.isWaiting()) {
            protocol_waiting_sure.setVisibility(View.VISIBLE);
        } else {
            protocol_waiting_sure.setVisibility(View.GONE);
        }
        FancyButton fancyButton_1 = (FancyButton) holder.getView(R.id.fancyButton_1);
        FancyButton fancyButton_2 = (FancyButton) holder.getView(R.id.fancyButton_2);
        FancyButton fancyButton_3 = (FancyButton) holder.getView(R.id.fancyButton_3);
        RelativeLayout protocol_item_relative = (RelativeLayout) holder.getView(R.id.protocol_item_relative);
        View protocol_view = holder.getView(R.id.protocol_view);
        switch (protocolModel.getState()) {
            case 0:
                protocol_item_relative.setVisibility(View.GONE);
                protocol_view.setVisibility(View.GONE);
                break;
            case 1:
                fancyButton_1.setVisibility(View.VISIBLE);
                fancyButton_2.setVisibility(View.VISIBLE);
                fancyButton_3.setVisibility(View.VISIBLE);
                fancyButton_1.setBackgroundColor(context.getResources().getColor(R.color.white));
                fancyButton_1.setBorderColor(context.getResources().getColor(R.color.lineColor));
                fancyButton_1.setTextColor(context.getResources().getColor(R.color.grey));
                fancyButton_1.setText(context.getString(R.string.protocol_refuse));
                fancyButton_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new MyToast(context, context.getString(R.string.cooperate_deny_done));
                    }
                });
                fancyButton_2.setBackgroundColor(context.getResources().getColor(R.color.title_bg));
                fancyButton_2.setBorderColor(context.getResources().getColor(R.color.title_bg));
                fancyButton_2.setTextColor(context.getResources().getColor(R.color.white));
                fancyButton_2.setText(context.getString(R.string.protocol_sure));
                fancyButton_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ProtocolAgree.class);
                        context.startActivity(intent);
                    }
                });
                fancyButton_3.setBackgroundColor(context.getResources().getColor(R.color.white));
                fancyButton_3.setBorderColor(context.getResources().getColor(R.color.title_bg));
                fancyButton_3.setTextColor(context.getResources().getColor(R.color.title_bg));
                fancyButton_3.setText(context.getString(R.string.protocol_edit));
                fancyButton_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CooperateCreate.class);
                        intent.putExtra("type", 1);
                        context.startActivity(intent);
                    }
                });

                break;
            case 2:
                fancyButton_1.setVisibility(View.VISIBLE);
                fancyButton_2.setVisibility(View.VISIBLE);
                fancyButton_3.setVisibility(View.GONE);
                fancyButton_1.setBackgroundColor(context.getResources().getColor(R.color.title_bg));
                fancyButton_1.setBorderColor(context.getResources().getColor(R.color.title_bg));
                fancyButton_1.setTextColor(context.getResources().getColor(R.color.white));
                fancyButton_1.setText(context.getString(R.string.protocol_get_order));
                fancyButton_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CooperateCreate.class);
                        intent.putExtra("type", 2);
                        context.startActivity(intent);
                    }
                });
                fancyButton_2.setBackgroundColor(context.getResources().getColor(R.color.white));
                fancyButton_2.setBorderColor(context.getResources().getColor(R.color.lineColor));
                fancyButton_2.setTextColor(context.getResources().getColor(R.color.grey));
                fancyButton_2.setText(context.getString(R.string.protocol_request_reject));
                fancyButton_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new MyToast(context, context.getString(R.string.cooperate_request_done));
                    }
                });
                break;
            case 3:
                fancyButton_3.setVisibility(View.GONE);
                fancyButton_2.setVisibility(View.GONE);
                fancyButton_1.setVisibility(View.VISIBLE);
                fancyButton_1.setBackgroundColor(context.getResources().getColor(R.color.white));
                fancyButton_1.setBorderColor(context.getResources().getColor(R.color.red));
                fancyButton_1.setTextColor(context.getResources().getColor(R.color.red));
                fancyButton_1.setText(context.getString(R.string.protocol_agree_reject));
                fancyButton_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new MyToast(context, context.getString(R.string.cooperate_agree_done));

                    }
                });
                break;
            case 4:
                fancyButton_3.setVisibility(View.GONE);
                fancyButton_2.setVisibility(View.GONE);
                fancyButton_1.setVisibility(View.VISIBLE);
                fancyButton_1.setBackgroundColor(context.getResources().getColor(R.color.white));
                fancyButton_1.setBorderColor(context.getResources().getColor(R.color.title_bg));
                fancyButton_1.setTextColor(context.getResources().getColor(R.color.title_bg));
                fancyButton_1.setText(context.getString(R.string.protocol_refuse_reject));
                fancyButton_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new MyToast(context, context.getString(R.string.cooperate_reject_done));

                    }
                });
                break;
        }
    }
}
