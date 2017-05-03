package com.hj.casps.cooperate;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import mehdi.sakout.fancybuttons.FancyButton;

public class CooperateGroupSearch extends ActivityBaseHeader2 implements View.OnClickListener {

    private EditText cooperate_group_names;
    private FancyButton group_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_group_search);
        initView();
    }

    private void initView() {
        setTitle(getString(R.string.cooperate_group));
        cooperate_group_names = (EditText) findViewById(R.id.cooperate_group_names);
        group_search = (FancyButton) findViewById(R.id.group_search);

        group_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.group_search:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String names = cooperate_group_names.getText().toString().trim();
        if (TextUtils.isEmpty(names)) {
            Toast.makeText(this, "names不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
