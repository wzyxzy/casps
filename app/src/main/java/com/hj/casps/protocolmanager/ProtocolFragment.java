package com.hj.casps.protocolmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.ordermanager.OrderDoingAdapter;
import com.hj.casps.ordermanager.OrderDoingModel;
import com.hj.casps.ui.MyDialog;

import java.util.ArrayList;
import java.util.List;


public class ProtocolFragment extends Fragment implements View.OnClickListener {
    private int protocol_type;
    private ListView protocol_list;
    private List<ProtocolModel> protocolModels;
    private List<OrderDoingModel> orderDoingModels;
    private ProtocolAdapter adapter;
    private OrderDoingAdapter adapter_order;
    private int protocol_type_j;
    private int type_k;
    private View order_divider;
    private CheckBox select_all_order;
    private TextView order_lock;
    private TextView order_abandon;
    private LinearLayout order_check_function;
    private MyDialog myDialog;

    public ProtocolFragment() {
        // Required empty public constructor
    }

    public ProtocolFragment(int i, int j, int k) {
        protocol_type = i;
        protocol_type_j = j;
        type_k = k;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_blank, container, false);
        initData();
        initView(layout);
        return layout;
    }

    private void initData() {
        switch (type_k) {
            case 1:
                protocolModels = new ArrayList<>();
                for (int i = 0; i < 30; i++) {
                    ProtocolModel protocolModel = new ProtocolModel();
                    switch (protocol_type) {
                        case 0:
                            protocolModel.setWaiting(true);
                            protocolModel.setState(0);
                            break;
                        case 1:
                            protocolModel.setWaiting(false);
                            protocolModel.setState(1);
                            break;
                        case 2:
                            protocolModel.setWaiting(false);
                            protocolModel.setState(i % 3 + 2);
                            break;
                    }

                    protocolModel.setBegin("2017-04-" + String.valueOf(i));
                    protocolModel.setEnd("2017-07-" + String.valueOf(i));
                    protocolModel.setObject("奥森学院" + String.valueOf(i));
                    protocolModel.setTransport("自取");
                    protocolModel.setPay_term("每月");
                    if ((protocol_type_j == 1 || protocol_type_j == 0) && (i % 2 == 0)) {
                        protocolModel.setName("四月份的临时采购任务" + String.valueOf(i / 2));
                        protocolModels.add(protocolModel);

                    } else if ((protocol_type_j == 0 || protocol_type_j == 2) && (i % 2 == 1)) {
                        protocolModel.setName("四月份的临时销售任务" + String.valueOf(i / 2));
                        protocolModels.add(protocolModel);

                    }
                }
                break;
            case 2:
                orderDoingModels = new ArrayList<>();
                for (int i = 0; i < 30; i++) {
                    OrderDoingModel orderDoingModel = new OrderDoingModel();
                    orderDoingModel.setStatus(protocol_type);
                    orderDoingModel.setStatus2(i % 4);
                    orderDoingModel.setChoice(false);
                    orderDoingModel.setObject("奥森学院");
                    orderDoingModel.setCreate_time("2017-01-31");
                    orderDoingModel.setOrderId("301620056897" + String.valueOf(i));
                    orderDoingModel.setPrice("7200");
                    if ((protocol_type_j == 1 || protocol_type_j == 0) && (i % 2 == 0)) {
                        orderDoingModels.add(orderDoingModel);

                    } else if ((protocol_type_j == 0 || protocol_type_j == 2) && (i % 2 == 1)) {
                        orderDoingModels.add(orderDoingModel);
                    }
                }
                break;
        }


    }

    private void initView(View layout) {
        order_divider = (View) layout.findViewById(R.id.order_divider);
        select_all_order = (CheckBox) layout.findViewById(R.id.select_all_order);
        order_lock = (TextView) layout.findViewById(R.id.order_lock);
        order_lock.setOnClickListener(this);
        order_abandon = (TextView) layout.findViewById(R.id.order_abandon);
        order_abandon.setOnClickListener(this);
        order_check_function = (LinearLayout) layout.findViewById(R.id.order_check_function);
        protocol_list = (ListView) layout.findViewById(R.id.protocol_list);
        switch (type_k) {
            case 1:
                adapter = new ProtocolAdapter(protocolModels, getContext(), R.layout.protocol_item);
                protocol_list.setAdapter(adapter);
                break;
            case 2:
                adapter_order = new OrderDoingAdapter(orderDoingModels, getContext(), R.layout.item_order_going);
                protocol_list.setAdapter(adapter_order);
                if (protocol_type == 1) {
                    order_divider.setVisibility(View.VISIBLE);
                    order_check_function.setVisibility(View.VISIBLE);
                }

                break;
        }
        select_all_order.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (int i = 0; i < orderDoingModels.size(); i++) {
                    orderDoingModels.get(i).setChoice(b);
                }
                adapter_order.notifyDataSetChanged();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_lock:
                showDialog(0);

                break;
            case R.id.order_abandon:
                showDialog(1);


                break;
        }
    }

    private void showDialog(int type) {
        myDialog = new MyDialog(getContext());
        switch (type) {
            case 0:
                myDialog.setMessage(getString(R.string.order_locked_ask));

                break;
            case 1:
                myDialog.setMessage(getString(R.string.order_abandon_ask));

                break;
        }
        myDialog.setYesOnclickListener(getString(R.string.True), new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                myDialog.dismiss();
            }
        });
        myDialog.setNoOnclickListener(getString(R.string.cancel), new MyDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}
