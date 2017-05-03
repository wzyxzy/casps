package com.hj.casps.quotes;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class QuoteQuery extends ActivityBaseHeader implements View.OnClickListener {

    private FancyButton quote_create_Btn;
    private TextView quote_do_desc_tv;
    private ListView quotes_list;
    private List<QuoteModel> quoteModels;
    private QuotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.quotes_manage));
        setContentView(R.layout.activity_quote_query);
        initData();
        initView();
    }

    private void initData() {
        //假数据
        quoteModels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            QuoteModel quoteModel = new QuoteModel();
            quoteModel.setName("绿色西兰花" + String.valueOf(i));
            quoteModel.setSerial_no("3016200568972" + String.valueOf(i));
            quoteModel.setNumber("100" + String.valueOf(i));
            quoteModel.setPeriod("2017-05-01至2018-05-01");
            quoteModel.setPerson("天美商行操作员" + String.valueOf(i));
            quoteModel.setPrice("3000-4000");
            quoteModel.setQuote_status("指定报价");
            quoteModels.add(quoteModel);
        }
    }

    private void initView() {

        quote_create_Btn = (FancyButton) findViewById(R.id.quote_create_Btn);
        quote_do_desc_tv = (TextView) findViewById(R.id.quote_do_desc_tv);
        quotes_list = (ListView) findViewById(R.id.quotes_list);
        adapter = new QuotesAdapter(quoteModels, QuoteQuery.this, R.layout.quotes_items);
        quotes_list.setAdapter(adapter);
        quote_create_Btn.setOnClickListener(this);
        quote_do_desc_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quote_create_Btn:
                intentActivity(this,CreateQuotes.class);
                break;
            case R.id.quote_do_desc_tv:

                //操作说明

                break;
        }
    }
    @Override
    protected void onNavSearchClick() {
        super.onNavSearchClick();
        intentActivity(this,QuotesSearch.class);
    }
}
