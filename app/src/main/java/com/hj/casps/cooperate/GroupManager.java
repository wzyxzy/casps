package com.hj.casps.cooperate;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader;
import com.hj.casps.ui.MyListView;

import java.util.ArrayList;
import java.util.List;

public class GroupManager extends ActivityBaseHeader implements View.OnClickListener {

    private TextView cooperate_group_info;
    private MyListView group_list;
    private List<CooperateGroupModel> cooperateGroupModels;
    private CooperateGroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manager);
        initData();
        initView();
    }

    private void initData() {
        cooperateGroupModels = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            CooperateGroupModel cooperateGroupModel = new CooperateGroupModel();
            cooperateGroupModel.setName("北京高校群" + String.valueOf(i));
            cooperateGroupModel.setState(i % 3);
            cooperateGroupModels.add(cooperateGroupModel);
        }

    }

    private void initView() {
        setTitle(getString(R.string.cooperate_group));
        cooperate_group_info = (TextView) findViewById(R.id.cooperate_group_info);
        group_list = (MyListView) findViewById(R.id.group_list);
        adapter = new CooperateGroupAdapter(cooperateGroupModels, this, R.layout.item_group_manager);
        group_list.setAdapter(adapter);
        cooperate_group_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cooperate_group_info:

                break;
        }
    }

    @Override
    protected void onNavSearchClick() {
        super.onNavSearchClick();
        intentActivity(this, CooperateGroupSearch.class);
    }
}
