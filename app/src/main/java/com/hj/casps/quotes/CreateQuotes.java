package com.hj.casps.quotes;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import java.util.Calendar;

public class CreateQuotes extends ActivityBaseHeader2 implements View.OnClickListener {

    private TextView quote_create_info;
    private EditText product_name;
    private Spinner product_type;
    private ImageView chooose_product_pic;
    private EditText product_number;
    private EditText product_price_from;
    private EditText product_price_to;
    private Spinner product_price_type;
    private EditText product_time_from;
    private EditText product_time_to;
    private EditText product_more;
    private Button create_quotes_commit;
    private String[] quotesTypeItems;
    private String[] priceTypeItems;
    private Calendar c = Calendar.getInstance();
    private ArrayAdapter<String> stringArrayAdapter;
    private boolean getData;
    private QuoteModel quoteModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.quotes_create));
        setContentView(R.layout.activity_create_quotes);
        initData();
        initView();
        titleRight.setVisibility(View.GONE);
    }

    private void initData() {
        //假数据
        quotesTypeItems = new String[]{"请选择类型", "报价类型1", "报价类型2", "报价类型3", "报价类型4"};
        priceTypeItems = new String[]{"￥", "$", "欧元", "英镑", "日元"};

        quoteModel = getIntent().getParcelableExtra("data");

    }

    private void initView() {
        quote_create_info = (TextView) findViewById(R.id.quote_create_info);
        product_name = (EditText) findViewById(R.id.product_name);
        product_type = (Spinner) findViewById(R.id.product_type);
        chooose_product_pic = (ImageView) findViewById(R.id.chooose_product_pic);
        product_number = (EditText) findViewById(R.id.product_number);
        product_price_from = (EditText) findViewById(R.id.product_price_from);
        product_price_to = (EditText) findViewById(R.id.product_price_to);
        product_price_type = (Spinner) findViewById(R.id.product_price_type);
        product_time_from = (EditText) findViewById(R.id.product_time_from);
        product_time_to = (EditText) findViewById(R.id.product_time_to);
        product_more = (EditText) findViewById(R.id.product_more);
        create_quotes_commit = (Button) findViewById(R.id.create_quotes_commit);

        quote_create_info.setOnClickListener(this);
        create_quotes_commit.setOnClickListener(this);
        product_name.setOnClickListener(this);
        chooose_product_pic.setOnClickListener(this);
        product_time_from.setOnClickListener(this);
        product_time_to.setOnClickListener(this);
        stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotesTypeItems);
        product_type.setAdapter(stringArrayAdapter);
        stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, priceTypeItems);
        product_price_type.setAdapter(stringArrayAdapter);
        if (quoteModel!=null){
            product_name.setText(quoteModel.getName());
            product_number.setText(quoteModel.getNumber());
            product_price_from.setText(quoteModel.getPrice().split("-")[0]);
            product_price_to.setText(quoteModel.getPrice().split("-")[1]);
            product_time_from.setText(quoteModel.getPeriod().split("至")[0]);
            if (!product_time_from.getText().toString().isEmpty()){
                product_time_from.setCompoundDrawables(null, null, null, null);

            }
            product_time_to.setText(quoteModel.getPeriod().split("至")[1]);
            if (!product_time_to.getText().toString().isEmpty()){
                product_time_to.setCompoundDrawables(null, null, null, null);

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quote_create_info:

                break;
            case R.id.create_quotes_commit:
                submit();
                break;
            case R.id.product_name:
                break;
            case R.id.chooose_product_pic:
                break;
            case R.id.product_time_from:
                showCalendar(product_time_from);
                break;
            case R.id.product_time_to:
                showCalendar(product_time_to);

                break;
        }
    }

    private void submit() {
        // validate
        String name = product_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String number = product_number.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(this, "number不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String from = product_price_from.getText().toString().trim();
        if (TextUtils.isEmpty(from)) {
            Toast.makeText(this, "from不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String to = product_price_to.getText().toString().trim();
        if (TextUtils.isEmpty(to)) {
            Toast.makeText(this, "to不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String product_from = product_time_from.getText().toString().trim();
        if (TextUtils.isEmpty(from)) {
            Toast.makeText(this, "from不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String product_to = product_time_to.getText().toString().trim();
        if (TextUtils.isEmpty(to)) {
            Toast.makeText(this, "to不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String more = product_more.getText().toString().trim();
        if (TextUtils.isEmpty(more)) {
            Toast.makeText(this, "more不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


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
//        editText.setCompoundDrawables(null, null, null, null);
//    }
}
