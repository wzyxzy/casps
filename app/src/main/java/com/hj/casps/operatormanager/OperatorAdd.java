package com.hj.casps.operatormanager;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.casps.R;
import com.hj.casps.adapter.operatoradapter.RelationAdapter;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.entity.OperatorEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;

public class OperatorAdd extends ActivityBaseHeader2 implements View.OnClickListener,RelationAdapter.OnItemClickLitener  {
    private EditText operator_ed_account, operator_ed_account_name,
            operator_ed_account_mobile, operator_ed_account_emial;
    private FancyButton operator_btn_commit;
    private TextView tl_shopping_title;
    private RecyclerView relation_recycler;
    private RelationAdapter relationAdapter;

    private List<OperatorEntity> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_add);
        getDatas();
        initView();
    }

    private void initView() {
        setTitle(getString(R.string.activity_add_operator_title));
        titleRight.setVisibility(View.GONE);
        RelationAdapter.setmOnItemClickLitener(this);
        layout_head_left_btn = (FancyButton) findViewById(R.id.layout_head_left_btn);
        layout_head_left_btn.setText(getString(R.string.hint_block_up_title));
        layout_head_right_btn = (FancyButton) findViewById(R.id.layout_head_right_btn);
        operator_btn_commit = (FancyButton) findViewById(R.id.operator_btn_commit);
        layout_head_right_btn.setVisibility(View.VISIBLE);
        operator_btn_commit.setOnClickListener(this);
        findViewById(R.id.layout_head_right_tv).setVisibility(View.GONE);
        layout_head_left_btn.setOnClickListener(this);
        layout_head_right_btn.setOnClickListener(this);
        tl_shopping_title = (TextView) findViewById(R.id.tl_shopping_title);
        tl_shopping_title.setText(getString(R.string.hint_about_role_title));

        operator_ed_account = (EditText) findViewById(R.id.operator_ed_account);
        operator_ed_account_name = (EditText) findViewById(R.id.operator_ed_account_name);
        operator_ed_account_mobile = (EditText) findViewById(R.id.operator_ed_account_mobile);
        operator_ed_account_emial = (EditText) findViewById(R.id.operator_ed_account_emial);

        relation_recycler = (RecyclerView) findViewById(R.id.relation_recycler);

        relation_recycler.setLayoutManager(new GridLayoutManager(this, 3));
        relationAdapter = new RelationAdapter(this, mList);
        relation_recycler.setAdapter(relationAdapter);
        relationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_head_left_btn:

                break;
            case R.id.layout_head_right_btn:
                intentActivity(this, OperatorPassWord.class);
                break;
            case R.id.operator_btn_commit:
                break;
        }
    }

    public void getDatas() {
        mList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            OperatorEntity op = new OperatorEntity("天美业务主管" + i, "tm" + i);
            mList.add(op);
        }
    }

    @Override
    public void onItemClick(View view, int position, Map<Integer, String> ckMap) {
        for (Map.Entry<Integer, String> entry : ckMap.entrySet()) {
            Toast.makeText(OperatorAdd.this, entry.getValue(), Toast.LENGTH_SHORT).show();
        }
    }
}
