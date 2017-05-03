package com.hj.casps.cooperate;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import mehdi.sakout.fancybuttons.FancyButton;

public class CooperateSearch extends ActivityBaseHeader2 implements View.OnClickListener {

    private Spinner cooperate_grade_spinner;
    private FancyButton cooperate_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_search);
        initView();
    }

    private void initView() {
        setTitle(getString(R.string.cooperate_contents));
        cooperate_grade_spinner = (Spinner) findViewById(R.id.cooperate_grade_spinner);
        cooperate_search = (FancyButton) findViewById(R.id.cooperate_search);

        cooperate_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cooperate_search:

                break;
        }
    }
}
