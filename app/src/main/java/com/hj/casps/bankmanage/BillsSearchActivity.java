package com.hj.casps.bankmanage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.common.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 付款收款查询公用的页面
 */
public class BillsSearchActivity extends ActivityBaseHeader2 implements View.OnClickListener {
    private int activity_type;


    @BindView(R.id.bills_search_tv1_title)
    TextView bills_search_tv1_title;
    @BindView(R.id.ed_search_payment_id)
    EditText ed_search_payment_id;
    @BindView(R.id.bills_search_tv2_title)
    TextView bills_search_tv2_title;
    @BindView(R.id.ed_search_payment_goods_name)
    EditText ed_search_payment_goods_name;
    @BindView(R.id.tv_payment_bank_name)
    TextView tv_payment_bank_name;
    @BindView(R.id.search_payment_bank_name)
    EditText search_payment_bank_name;
    @BindView(R.id.layout_payment_bank_name)
    LinearLayout layout_payment_bank_name;
    @BindView(R.id.layout_payment_bank_3)
    LinearLayout layout_payment_bank_3;
    @BindView(R.id.search_tv_bills_title_3)
    TextView search_tv_bills_title_3;
    @BindView(R.id.search_tv_bills_content_3)
    TextView search_tv_bills_content_3;
    @BindView(R.id.layout_payment_bank_6)
    LinearLayout layout_payment_bank_6;
    @BindView(R.id.search_tv_bills_title_6)
    TextView search_tv_bills_title_6;
    @BindView(R.id.search_ed_bills_content_6)
    EditText search_ed_bills_content_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_search);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setTitle(getString(R.string.title_activity_card__search));
        titleRight.setVisibility(View.GONE);
        activity_type = getIntent().getIntExtra(Constant.BUNDLE_TYPE, 0);


        switch (activity_type) {
            case Constant.PAYMENT_SEARCH_TYPE:
                toastSHORT("付款查询界面");
                break;
            case Constant.RECEIPT_SEARCH_TYPE:
                toastSHORT("收款查询界面");
                break;
            case Constant.REFUND_SEARCH_TYPE:
                toastSHORT("退款查询界面");
                break;
            case Constant.RECEIVE_REFUND_SEARCH_TYPE:
                toastSHORT("收退款查询界面");
                tv_payment_bank_name.setText(getText(R.string.tv_receive_refund_title));
                search_payment_bank_name.setHint(getText(R.string.hint_receive_refund_title));
                break;
            case Constant.ECPRESS_ADDRESS_ACTIVITY_TYPE:
                toastSHORT("收货查询界面");
                layout_payment_bank_name.setVisibility(View.GONE);
                break;
            case Constant.ECPRESS_SEND_ACTIVITY_TYPE:
                toastSHORT("发货查询界面");
                layout_payment_bank_name.setVisibility(View.GONE);
                break;
            case Constant.ECPRESS_QUIT_ACTIVITY_TYPE:
                toastSHORT("退货查询界面");
                layout_payment_bank_name.setVisibility(View.GONE);
                break;
            case Constant.ECPRESS_QUIT_HARVEST_ACTIVITY_TYPE:
                toastSHORT("退货签收查询界面");
                layout_payment_bank_name.setVisibility(View.GONE);
                break;
            case Constant.CREATE_SECTION_BILLS_ACTIVITY_TYPE:
                toastSHORT("创建结款单查询界面");
                layout_payment_bank_name.setVisibility(View.GONE);
                bills_search_tv1_title.setText(getText(R.string.hint_search_execyte_title_1));
                ed_search_payment_id.setHint(getText(R.string.hint_ed_over_bills_id_title));

                bills_search_tv2_title.setText(getText(R.string.hint_search_execyte_title_3));
                ed_search_payment_goods_name.setHint(getText(R.string.hint_ed_over_bills_id_title));
                break;
            case Constant.ADD_SECTION_BILLS_ACTIVITY_TYPE:
                toastSHORT("添加订单查询界面");
                layout_payment_bank_name.setVisibility(View.GONE);
                break;
            case Constant.WAIT_CHECK_BILLS_ACTIVITY_TYPE:
                toastSHORT("待审批结款单查询界面");
                layout_payment_bank_name.setVisibility(View.GONE);
                bills_search_tv1_title.setText(getText(R.string.hint_search_execyte_title_1));
                ed_search_payment_id.setHint(getText(R.string.hint_ed_over_bills_id_title));

                bills_search_tv2_title.setText(getText(R.string.hint_search_execyte_title_3));
                ed_search_payment_goods_name.setHint(getText(R.string.hint_ed_over_bills_id_title));
                break;
            case Constant.EXECUTE_BILLS_ACTIVITY_TYPE:
                toastSHORT("执行中结款单");
                layout_payment_bank_3.setVisibility(View.VISIBLE);
                layout_payment_bank_6.setVisibility(View.VISIBLE);
                bills_search_tv1_title.setText(getText(R.string.hint_search_execyte_title_1));
                ed_search_payment_id.setHint(getText(R.string.hint_ed_over_bills_id_title));

                bills_search_tv2_title.setText(getText(R.string.hint_search_execyte_title_3));
                ed_search_payment_goods_name.setHint(getText(R.string.hint_ed_over_bills_id_title));

                search_tv_bills_title_3.setText(getText(R.string.hint_search_execyte_title_5));
                search_tv_bills_content_3.setText(getText(R.string.hint_tv_over_bills_id_title));

                tv_payment_bank_name.setText(getText(R.string.hint_search_execyte_title_6));
                search_payment_bank_name.setFocusable(false);
                search_payment_bank_name.setHint(getText(R.string.hint_search_execyte_title_7));
                search_payment_bank_name.setOnClickListener(this);

                search_tv_bills_title_6.setText(getText(R.string.hint_search_execyte_title_8));
                search_ed_bills_content_6.setFocusable(false);
                search_ed_bills_content_6.setHint(getText(R.string.hint_search_execyte_title_9));
                search_ed_bills_content_6.setOnClickListener(this);

                break;
            case Constant.REGISTER_BILLS_ACTIVITY_TYPE:
                toastSHORT("结款单登记担保列表");
                layout_payment_bank_3.setVisibility(View.VISIBLE);
                layout_payment_bank_6.setVisibility(View.VISIBLE);
                bills_search_tv1_title.setText(getText(R.string.hint_search_execyte_title_1));
                ed_search_payment_id.setHint(getText(R.string.hint_ed_over_bills_id_title));

                bills_search_tv2_title.setText(getText(R.string.hint_search_execyte_title_3));
                ed_search_payment_goods_name.setHint(getText(R.string.hint_ed_over_bills_id_title));

                search_tv_bills_title_3.setText(getText(R.string.hint_search_execyte_title_5));
                search_tv_bills_content_3.setText(getText(R.string.hint_tv_over_bills_id_title));

                tv_payment_bank_name.setText(getText(R.string.hint_search_execyte_title_6));
                search_payment_bank_name.setOnClickListener(this);
                search_payment_bank_name.setFocusable(false);
                search_payment_bank_name.setHint(getText(R.string.hint_search_execyte_title_7));

                search_tv_bills_title_6.setText(getText(R.string.hint_search_execyte_title_8));
                search_ed_bills_content_6.setOnClickListener(this);
                search_ed_bills_content_6.setFocusable(false);
                search_ed_bills_content_6.setHint(getText(R.string.hint_search_execyte_title_9));


                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_payment_bank_name:
                showCalendar(search_payment_bank_name);
                break;
            case R.id.search_ed_bills_content_6:
                showCalendar(search_ed_bills_content_6);
                break;
        }
    }
}
