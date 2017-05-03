package com.hj.casps.cooperate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader;

import java.util.ArrayList;
import java.util.List;

public class CooperateDirectory extends ActivityBaseHeader implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup cooperate_choose;
    private ListView quotes_list;
    private List<CooperateModel> cooperateModelsBuy;
    private List<CooperateModel> cooperateModelsSell;
    private CooperateAdapter adapter;
    private boolean buyMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_directory);
        initData();
        initView();
    }

    private void initData() {
        cooperateModelsBuy = new ArrayList<>();
        cooperateModelsSell = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CooperateModel cooperateModel = new CooperateModel();
            cooperateModel.setName("采购合作会员" + String.valueOf(i + 1));
            CooperateModel cooperateModel2 = new CooperateModel();
            cooperateModel2.setName("销售合作会员" + String.valueOf(i + 1));
            cooperateModelsBuy.add(cooperateModel);
            cooperateModelsSell.add(cooperateModel2);

        }

    }

    private void initView() {
        setTitle(getString(R.string.cooperate_direct));
        setTitleRight(null, null);
        cooperate_choose = (RadioGroup) findViewById(R.id.cooperate_choose);
        quotes_list = (ListView) findViewById(R.id.quotes_list);
        cooperate_choose.setOnCheckedChangeListener(this);
        adapter = new CooperateAdapter(cooperateModelsBuy, this, R.layout.cooperate_item);
        quotes_list.setAdapter(adapter);
        buyMode = true;
        quotes_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (buyMode) {

                    Intent intent = new Intent(getApplicationContext(), CooperateDetail.class);
                    intent.putExtra("name", cooperateModelsBuy.get(i).getName());
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getApplicationContext(), CooperateDetail.class);
                    intent.putExtra("name", cooperateModelsSell.get(i).getName());
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.main_radio_first:
                buyMode = true;
                adapter = new CooperateAdapter(cooperateModelsBuy, this, R.layout.cooperate_item);
                quotes_list.setAdapter(adapter);
                break;
            case R.id.main_radio_second:
                buyMode = false;
                adapter = new CooperateAdapter(cooperateModelsSell, this, R.layout.cooperate_item);
                quotes_list.setAdapter(adapter);

                break;
        }
    }
}
