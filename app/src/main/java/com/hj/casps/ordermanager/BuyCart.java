package com.hj.casps.ordermanager;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader;
import com.hj.casps.common.Constant;

import java.util.ArrayList;
import java.util.List;

public class BuyCart extends ActivityBaseHeader implements View.OnClickListener {

    private TextView buy_cart_info;
    private ListView buy_cart_list;
    private int type;
    private String type_name;
    private List<OrderBuyModel> orderBuyModels;
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_cart);
        initData();
        initView();
    }

    private void initData() {
        type = getIntent().getIntExtra(Constant.ORDER_TYPE, Constant.order_type_buy);
        switch (type) {
            case Constant.order_type_buy:
                setTitle(getString(R.string.order_buy_cart));
                type_name = getString(R.string.cooperate_buy_role);
                break;
            case Constant.order_type_sell:
                setTitle(getString(R.string.order_sell_cart));
                type_name = getString(R.string.cooperate_buy_part);
                break;

        }
        orderBuyModels = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            OrderBuyModel orderBuyModel = new OrderBuyModel();
            orderBuyModel.setName("长城商行" + String.valueOf(i + 1));
            orderBuyModel.setType(type_name);
            orderBuyModel.setContents("散装农场青菜        冷冻大肉        葵花油       五常大米       中国");
            orderBuyModel.setNum(i + 20);
            orderBuyModels.add(orderBuyModel);

        }

    }

    private void initView() {
        setTitleRight(null, null);
        buy_cart_info = (TextView) findViewById(R.id.buy_cart_info);
        buy_cart_list = (ListView) findViewById(R.id.buy_cart_list);
        adapter = new OrderAdapter(orderBuyModels, this, R.layout.item_buy_cart);
        buy_cart_list.setAdapter(adapter);
        buy_cart_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_cart_info:

                break;
        }
    }
}
