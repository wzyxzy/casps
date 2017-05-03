package com.hj.casps.overdealmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.overdealadapter.CheckWaitAdapter;
import com.hj.casps.bankmanage.BillsSearchActivity;
import com.hj.casps.base.ActivityBaseHeader;
import com.hj.casps.common.Constant;
import com.hj.casps.entity.QueryPendingSttle;
import com.hj.casps.ui.MyDialog;
import com.hj.casps.ui.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * 待审批结款单
 */
public class CheckWaitBills extends ActivityBaseHeader implements View.OnClickListener,
        CheckWaitAdapter.onCheckedkType {
    @BindView(R.id.check_bills_list)
    ListView check_bills_list;

    @BindView(R.id.layout_head_left_btn)
    FancyButton layout_head_left_btn;

    @BindView(R.id.layout_bottom_layout_1)
    LinearLayout layout_bottom_layout_1;

    @BindView(R.id.layout_bottom_check_1)
    CheckBox layout_bottom_check_1;

    @BindView(R.id.layout_bottom_tv_2)
    TextView layout_bottom_tv_2;

    @BindView(R.id.layout_bottom_tv_3)
    TextView layout_bottom_tv_3;

    @BindView(R.id.layout_bottom_tv_4)
    TextView layout_bottom_tv_4;

    private CheckWaitAdapter cAdapter;
    private List<QueryPendingSttle> mList;
    private MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_wait_bills);
        ButterKnife.bind(this);

        getDatas();
        initView();
    }

    private void initView() {
        layout_head_left_btn.setVisibility(View.GONE);
        setTitle(getString(R.string.activity_wiat_check_bills_title));
        layout_bottom_layout_1.setOnClickListener(this);
        layout_bottom_check_1.setOnClickListener(this);
        layout_bottom_tv_2.setOnClickListener(this);
        layout_bottom_tv_3.setOnClickListener(this);
        layout_bottom_tv_4.setOnClickListener(this);
        layout_bottom_tv_2.setText(R.string.hint_wait_layout_title_3);
        layout_bottom_tv_3.setText(R.string.cooperate_agree);
        layout_bottom_tv_4.setText(R.string.cooperate_deny);

        CheckWaitAdapter.setOnCheckedkType(this);

        cAdapter = new CheckWaitAdapter(this, mList);
        check_bills_list.setAdapter(cAdapter);
        cAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_bottom_layout_1:
                if (layout_bottom_check_1.isChecked()) {
                    layout_bottom_check_1.setChecked(false);
                    selectAll(false);
                } else {
                    layout_bottom_check_1.setChecked(true);
                    selectAll(true);
                }
                break;
            case R.id.layout_bottom_tv_2:

                break;
            case R.id.layout_bottom_tv_3:
                showBillsDialog(getString(R.string.dialog_wait_layout_title_5));
                break;
            case R.id.layout_bottom_tv_4:
                showBillsDialog(getString(R.string.dialog_wait_layout_title_4));
                break;
        }
    }

    @Override
    protected void onNavSearchClick() {
        super.onNavSearchClick();
        //单据号点击
        bundle.putInt(Constant.BUNDLE_TYPE, Constant.WAIT_CHECK_BILLS_ACTIVITY_TYPE);
        intentActivity(context, BillsSearchActivity.class, bundle);
    }

    @Override
    public void onCheckedY(int pos) {

    }

    @Override
    public void onCheckedN(int pos) {

    }

    @Override
    public void onBillsIDItemCilckListener(int pos) {
        intentActivity(BillsSectionDetailsActivity.class, Constant.WAIT_CHECK_BILLS_ACTIVITY_TYPE);
    }

    public void getDatas() {
        mList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            QueryPendingSttle qSttle = new QueryPendingSttle();
            qSttle.setId(985471131 + i);
            qSttle.setSettleCode("687444781145" + i);
            qSttle.setMmbgetName("天坛学院" + i);
            qSttle.setMmbpayName("交通大学" + i);
            qSttle.setSettleMoney(6543.25 + i);
            qSttle.setCtrTime("2016.04.0" + i);
            qSttle.setCtrMoney(65854 + i);
            mList.add(qSttle);
        }
    }


    public void showBillsDialog(String msg) {
        myDialog = new MyDialog(this);
        myDialog.setMessage(msg);
        myDialog.setYesOnclickListener(getString(R.string.True), new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                myDialog.dismiss();
                new MyToast(context, "付款成功");
                ;
            }
        });
        myDialog.setNoOnclickListener(getString(R.string.cancel), new MyDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }


    private void selectAll(boolean isck) {
        for (int i = 0; i < mList.size(); i++) {
            // 改变boolean
            mList.get(i).setCheck(isck);
            cAdapter.notifyDataSetChanged();
        }
    }
}
