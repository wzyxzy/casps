package com.hj.casps.quotes;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import java.util.Calendar;

public class QuotesSearch extends ActivityBaseHeader2 implements View.OnClickListener {

    private EditText product_name;
    private Spinner product_type;
    private Spinner quotes_search_status;
    private EditText publish_time_from;
    private EditText publish_time_to;
    private EditText period_time_from;
    private EditText period_time_to;
    private Button search_quotes_btn;
    private Calendar c = Calendar.getInstance();
    private ArrayAdapter<String> stringArrayAdapter;
    private String[] typeItems;
    private String[] stateItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.quotes_search));
        setContentView(R.layout.activity_quotes_search);
        titleRight.setVisibility(View.GONE);
        initData();
        initView();
    }

    private void initData() {
        //假数据
        typeItems = new String[]{"报价类型", "报价类型1", "报价类型2", "报价类型3", "报价类型4"};
        stateItems = new String[]{"全部", "状态1", "状态2", "状态3", "状态4"};
    }

    private void initView() {
        product_name = (EditText) findViewById(R.id.product_name);
        product_type = (Spinner) findViewById(R.id.product_type);
        quotes_search_status = (Spinner) findViewById(R.id.quotes_search_status);
        publish_time_from = (EditText) findViewById(R.id.publish_time_from);
        publish_time_from.setOnClickListener(this);
        publish_time_to = (EditText) findViewById(R.id.publish_time_to);
        publish_time_to.setOnClickListener(this);
        period_time_from = (EditText) findViewById(R.id.period_time_from);
        period_time_from.setOnClickListener(this);
        period_time_to = (EditText) findViewById(R.id.period_time_to);
        period_time_to.setOnClickListener(this);
        search_quotes_btn = (Button) findViewById(R.id.search_quotes_btn);
        stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, typeItems);
        product_type.setAdapter(stringArrayAdapter);
        stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stateItems);
        quotes_search_status.setAdapter(stringArrayAdapter);
        search_quotes_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_quotes_btn:
                submit();
                break;
            case R.id.publish_time_from:
                showCalendar(publish_time_from);
                break;
            case R.id.publish_time_to:
                showCalendar(publish_time_to);
                break;
            case R.id.period_time_from:
                showCalendar(period_time_from);
                break;
            case R.id.period_time_to:
                showCalendar(period_time_to);
                break;
        }
    }

    private void submit() {
        // validate
//        String name = product_name.getText().toString().trim();
//        if (TextUtils.isEmpty(name)) {
//            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String from = publish_time_from.getText().toString().trim();
//        if (TextUtils.isEmpty(from)) {
//            Toast.makeText(this, "from不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String to = publish_time_to.getText().toString().trim();
//        if (TextUtils.isEmpty(to)) {
//            Toast.makeText(this, "to不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String period_from = period_time_from.getText().toString().trim();
//        if (TextUtils.isEmpty(from)) {
//            Toast.makeText(this, "from不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String period_to = period_time_to.getText().toString().trim();
//        if (TextUtils.isEmpty(to)) {
//            Toast.makeText(this, "to不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }

        // TODO validate success, do something
        Toast.makeText(this, "查询结果已展示", Toast.LENGTH_SHORT).show();
        intentActivity(this,QuoteQuery.class);


    }

//    private void showCalendar(final EditText editText) {
////        c = Calendar.getInstance();
//        new DatePickerDialog(this,
//                new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year,
//                                          int monthOfYear, int dayOfMonth) {
//                        int month = monthOfYear + 1;
//                        if (month < 10 && dayOfMonth < 10) {
//                            editText.setText(year + "-0" + month
//                                    + "-0" + dayOfMonth);
//                        } else if (month < 10 && dayOfMonth >= 10) {
//                            editText.setText(year + "-0" + month
//                                    + "-" + dayOfMonth);
//                        } else if (month >= 10 && dayOfMonth < 10) {
//                            editText.setText(year + "-" + month
//                                    + "-0" + dayOfMonth);
//                        } else {
//                            editText.setText(year + "-" + month
//                                    + "-" + dayOfMonth);
//                        }
//
//                    }
//                }
//                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
//                .get(Calendar.DAY_OF_MONTH)).show();
//        editText.setCompoundDrawables(null,null,null,null);
//    }
}
