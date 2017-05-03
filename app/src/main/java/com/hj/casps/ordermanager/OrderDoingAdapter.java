package com.hj.casps.ordermanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.WZYBaseAdapter;
import com.hj.casps.bankmanage.BillsDetailsActivity;
import com.hj.casps.common.Constant;
import com.hj.casps.entity.PayMnetInfo;
import com.hj.casps.ui.MyDialog;

import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by zy on 2017/5/2.
 */

public class OrderDoingAdapter extends WZYBaseAdapter<OrderDoingModel> {
    private Context context;
    private PayMnetInfo pInfo;

    public OrderDoingAdapter(List<OrderDoingModel> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
        this.context = context;
    }

    @Override
    public void bindData(ViewHolder holder, final OrderDoingModel orderDoingModel) {
        TextView oderId = (TextView) holder.getView(R.id.layout_tv_order_line_no);
        oderId.setText(orderDoingModel.getOrderId());
        oderId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoDetail(orderDoingModel);
            }
        });
        TextView object = (TextView) holder.getView(R.id.order_doing_object);
        object.setText(orderDoingModel.getObject());
        TextView time = (TextView) holder.getView(R.id.order_doing_create_time);
        time.setText(orderDoingModel.getCreate_time());
        TextView price = (TextView) holder.getView(R.id.order_doing_all_price);
        price.setText(orderDoingModel.getPrice());
        CheckBox checkBox = (CheckBox) holder.getView(R.id.layout_check_order_going);
        TextView order_item_state = (TextView) holder.getView(R.id.order_item_state);
        FancyButton order_going_btn = (FancyButton) holder.getView(R.id.order_going_btn);

        switch (orderDoingModel.getStatus()) {
            case 0:
                checkBox.setVisibility(View.GONE);
                order_item_state.setVisibility(View.GONE);
                order_going_btn.setVisibility(View.GONE);
                break;
            case 1:
                checkBox.setVisibility(View.VISIBLE);
                order_item_state.setVisibility(View.GONE);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        orderDoingModel.setChoice(b);
                    }
                });
                checkBox.setChecked(orderDoingModel.isChoice());
                order_going_btn.setVisibility(View.VISIBLE);
                order_going_btn.setText(context.getString(R.string.quotes_edit));
                order_going_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(context, OrderDetail.class);
                        intent.putExtra("title", context.getString(R.string.edit));
                        context.startActivity(intent);
                    }
                });
                break;
            case 2:
                switch (orderDoingModel.getStatus2()) {
                    case 0:
                        checkBox.setVisibility(View.GONE);
                        order_item_state.setVisibility(View.VISIBLE);
                        order_going_btn.setVisibility(View.VISIBLE);
                        order_going_btn.setText(context.getString(R.string.order_doing_request_stop));
                        order_going_btn.setPadding(20, 0, 20, 0);
                        order_going_btn.setBorderColor(context.getResources().getColor(R.color.title_bg));
                        order_going_btn.setTextColor(context.getResources().getColor(R.color.title_bg));
                        order_item_state.setText(context.getString(R.string.order_doing_going));
                        order_item_state.setBackgroundColor(context.getResources().getColor(R.color.light_green));


                        break;
                    case 1:
                        checkBox.setVisibility(View.GONE);
                        order_item_state.setVisibility(View.VISIBLE);
                        order_going_btn.setVisibility(View.VISIBLE);
                        order_going_btn.setText(context.getString(R.string.order_doing_reject_request));
                        order_going_btn.setPadding(5, 0, 5, 0);
                        order_going_btn.setBorderColor(context.getResources().getColor(R.color.title_bg));
                        order_going_btn.setTextColor(context.getResources().getColor(R.color.title_bg));
                        order_item_state.setText(context.getString(R.string.order_doing_going));
                        order_item_state.setBackgroundColor(context.getResources().getColor(R.color.light_green));

                        break;
                    case 2:
                        checkBox.setVisibility(View.GONE);
                        order_item_state.setVisibility(View.VISIBLE);
                        order_going_btn.setVisibility(View.VISIBLE);
                        order_going_btn.setText(context.getString(R.string.order_doing_sure_stop));
                        order_going_btn.setPadding(20, 0, 20, 0);
                        order_going_btn.setBorderColor(context.getResources().getColor(R.color.red));
                        order_going_btn.setTextColor(context.getResources().getColor(R.color.red));
                        order_item_state.setText(context.getString(R.string.order_doing_going));
                        order_item_state.setBackgroundColor(context.getResources().getColor(R.color.light_green));

                        break;
                    case 3:
                        checkBox.setVisibility(View.GONE);
                        order_item_state.setVisibility(View.VISIBLE);
                        order_item_state.setText(context.getString(R.string.order_doing_finished));
                        order_item_state.setBackgroundColor(context.getResources().getColor(R.color.text_color_blue2));
                        order_going_btn.setVisibility(View.GONE);
                        break;

                }

//                order_going_btn.setPadding(10, 0, 10, 0);

                break;

        }
        order_going_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(orderDoingModel.getStatus2());
            }
        });


    }

    private void gotoDetail(OrderDoingModel orderDoingModel) {
        getDatas(orderDoingModel.getOrderId());
        Intent intent = new Intent(context, BillsDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.BUNDLE_TYPE, pInfo);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 加数据
     */
    public void getDatas(String id) {

        pInfo = new PayMnetInfo();
        pInfo.setBillsId(id);
        pInfo.setBillsType("销售");
        pInfo.setFlowId("先货后款");
        pInfo.setBuyer("奥森学院");
        pInfo.setContractTime("2017-04-26");
        pInfo.setPaymentTime("2017-04-26");
        pInfo.setDeliveryStarTime("2017-04-26");
        pInfo.setDeliveryEndTime("2017-04-26");
        pInfo.setTotalMoney("20000");
        pInfo.setPayId("暂无");
        pInfo.setPayId("中信银行");
        pInfo.setHarvestAddress("北京海淀区");
        pInfo.setAlready_money("3000");
        pInfo.setAwait_money("17000");
        pInfo.setNow_money("1700");
        pInfo.setPayment_remark("没有备注");


    }

    private void showDialog(int i) {
        final MyDialog myDialog = new MyDialog(context);
        switch (i) {
            case 0:
                myDialog.setMessage(context.getString(R.string.order_stop_ask));
                break;
            case 1:
                myDialog.setMessage(context.getString(R.string.order_stop_reject_ask));

                break;
            case 2:
                myDialog.setMessage(context.getString(R.string.order_stop_agree_ask));

                break;
        }
        myDialog.setYesOnclickListener(context.getString(R.string.True), new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                myDialog.dismiss();
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
}
