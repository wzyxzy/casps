package com.hj.casps.operatormanager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.operatoradapter.OperatorAdapter;
import com.hj.casps.base.ActivityBaseHeader;
import com.hj.casps.entity.OperatorEntity;

import java.util.ArrayList;
import java.util.List;

import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;
import mehdi.sakout.fancybuttons.FancyButton;

public class OperatorListActivity extends ActivityBaseHeader implements OnPullListener,
        View.OnClickListener {

    private ListView operator_list;
    private  List<OperatorEntity> mList;
    private OperatorAdapter operatorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_list);

        getDatas();

        initView();
    }


    private void initView() {
        setTitle(getString(R.string.activity_operator_title));
        layout_head_left_btn = (FancyButton) findViewById(R.id.layout_head_left_btn);
        layout_head_right_tv = (TextView) findViewById(R.id.layout_head_right_tv);
        layout_head_left_btn.setOnClickListener(this);
        layout_head_right_tv.setOnClickListener(this);

        operator_list = (ListView) findViewById(R.id.operator_list);
        operatorAdapter = new OperatorAdapter(this, mList);
        operatorAdapter.notifyDataSetChanged();
        operator_list.setAdapter(operatorAdapter);

        mLoader = new NestRefreshLayout(operator_list);
        mLoader.setOnLoadingListener(this);
        mLoader.setPullLoadEnable(true);
        mLoader.setPullRefreshEnable(true);


        operator_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intentActivity(OperatorListActivity.this, OperatorAdd.class);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_head_left_btn:
                addView();
                break;
            case R.id.layout_head_right_tv:

                break;
        }
    }

    //添加
    private void addView() {
        toastSHORT("点击");
    }

    @Override
    public void onRefresh(AbsRefreshLayout listLoader) {
        toast("刷新了");
        mLoader.onLoadAll();//加载全部
    }

    @Override
    public void onLoading(AbsRefreshLayout listLoader) {
        toast("加载了");
        mLoader.onLoadFinished();//加载结束
    }


    public void getDatas() {
        mList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            OperatorEntity op = new OperatorEntity("天美业务主管" + i, "tm" + i);
            mList.add(op);
        }
    }
}
