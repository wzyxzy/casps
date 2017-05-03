package com.hj.casps.cooperate;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader;

import java.util.ArrayList;
import java.util.List;

public class CooperateRequest extends ActivityBaseHeader implements View.OnClickListener {

    private TextView cooperate_request_info;
    private ListView request_list;
    private List<CooperateRequstModel> cooperateRequstModels;
    private CooperateRequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_request);
        initData();
        initView();
    }

    private void initData() {
        cooperateRequstModels = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            CooperateRequstModel cooperateRequstModel = new CooperateRequstModel();
            cooperateRequstModel.setName("奥森学校" + String.valueOf(i));
            cooperateRequstModel.setRelation("向我销售" + String.valueOf(i));
            cooperateRequstModel.setNum(i);
            cooperateRequstModels.add(cooperateRequstModel);


        }
    }

    private void initView() {
        setTitle(getString(R.string.cooperate_request));
        setTitleRight(null, null);
        cooperate_request_info = (TextView) findViewById(R.id.cooperate_request_info);
        request_list = (ListView) findViewById(R.id.request_list);

        cooperate_request_info.setOnClickListener(this);
        adapter = new CooperateRequestAdapter(cooperateRequstModels, this, R.layout.cooperate_request_item);
        request_list.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cooperate_request_info:

                break;
        }
    }
}
