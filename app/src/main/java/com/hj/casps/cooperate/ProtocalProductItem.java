package com.hj.casps.cooperate;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

public class ProtocalProductItem extends ActivityBaseHeader2 implements View.OnClickListener {

    private TextView cooperate_protocol_info;
    private GridView cooperate_protocol_gv;
    private RecyclerView cooperate_protocol_recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocal_product_item);
        initView();

    }

    private void initView() {
        setTitle(getString(R.string.cooperate_products_protocol_type));
        setTitleRight(getString(R.string.save), null);
        cooperate_protocol_info = (TextView) findViewById(R.id.cooperate_protocol_info);
        cooperate_protocol_gv = (GridView) findViewById(R.id.cooperate_protocol_gv);
        cooperate_protocol_recycle = (RecyclerView) findViewById(R.id.cooperate_protocol_recycle);

        cooperate_protocol_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cooperate_protocol_info:

                break;
        }
    }
}
