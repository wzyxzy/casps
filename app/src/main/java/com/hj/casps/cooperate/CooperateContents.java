package com.hj.casps.cooperate;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader;

import java.util.ArrayList;
import java.util.List;

public class CooperateContents extends ActivityBaseHeader implements View.OnClickListener {

    private TextView cooperate_contents_info;
    private ListView contents_list;
    private CooperateContentsAdapter adapter;
    private List<CooperateModel> cooperateModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_contents);
        initData();
        initView();
    }

    private void initData() {
        cooperateModels = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            CooperateModel cooperateModel = new CooperateModel();
            cooperateModel.setName("北京每日鲜" + i);
            cooperateModel.setAddress("北京小树林" + i);
            cooperateModel.setGrade(i % 6);
            cooperateModels.add(cooperateModel);

        }
    }

    private void initView() {
        setTitle(getString(R.string.cooperate_contents));
        cooperate_contents_info = (TextView) findViewById(R.id.cooperate_contents_info);
        cooperate_contents_info.setOnClickListener(this);
        contents_list = (ListView) findViewById(R.id.contents_list);
        adapter = new CooperateContentsAdapter(cooperateModels, this, R.layout.item_cooperate_contents);
        contents_list.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cooperate_contents_info:

                break;
        }
    }

    @Override
    protected void onNavSearchClick() {
        super.onNavSearchClick();
        intentActivity(this, CooperateSearch.class);
    }
}
