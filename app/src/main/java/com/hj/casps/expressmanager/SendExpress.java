package com.hj.casps.expressmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.casps.R;
import com.hj.casps.adapter.expressadapter.SendAdapter;
import com.hj.casps.bankmanage.BillsDetailsActivity;
import com.hj.casps.bankmanage.BillsSearchActivity;
import com.hj.casps.base.ActivityBaseHeader;
import com.hj.casps.common.Constant;
import com.hj.casps.entity.PayMnetInfo;
import com.hj.casps.ui.MyDialog;
import com.hj.casps.ui.MyToast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * 发货
 */
public class SendExpress extends ActivityBaseHeader implements OnPullListener,
        View.OnClickListener,
        SendAdapter.onCheckedkType {

    private SendAdapter sendAdapter;

    private ListView payment_list;
    private List<PayMnetInfo> mList;
    private LinearLayout layout_all;
    private TextView offline_payment_btn, tv_operation_explain, offline_payment_reset;
    private MyDialog myDialog;
    private int goodsCount = 0;
    private double goodsMoeny;
    private CheckBox select_all_ck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        getDatas();

        initView();

    }

    private void initView() {
        setTitle(getString(R.string.activity_send_express_harvest_title));

        SendAdapter.setOnCheckedkType(this);

        select_all_ck = (CheckBox) findViewById(R.id.select_all_ck);
        payment_list = (ListView) findViewById(R.id.payment_list);
        offline_payment_btn = (TextView) findViewById(R.id.offline_payment_btn);

        offline_payment_btn.setText(getText(R.string.activity_send_express_harvest_title));
        offline_payment_btn.setOnClickListener(this);
        tv_operation_explain = (TextView) findViewById(R.id.tv_operation_explain);
        tv_operation_explain.setOnClickListener(this);
        layout_all = (LinearLayout) findViewById(R.id.layout_all);
        layout_all.setOnClickListener(this);
        offline_payment_reset = (TextView) findViewById(R.id.offline_payment_reset);
        offline_payment_reset.setVisibility(View.VISIBLE);
        offline_payment_reset.setBackgroundResource(R.color.reset_bg);

        sendAdapter = new SendAdapter(this, mList);
        sendAdapter.notifyDataSetChanged();
        payment_list.setAdapter(sendAdapter);

        mLoader = new NestRefreshLayout(payment_list);
        mLoader.setOnLoadingListener(this);
        mLoader.setPullLoadEnable(true);
        mLoader.setPullRefreshEnable(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.offline_payment_btn:
                buyAll();
                break;
            case R.id.layout_all:
                if (select_all_ck.isChecked()) {
                    select_all_ck.setChecked(false);
                    selectAll(false);
                } else {
                    select_all_ck.setChecked(true);
                    selectAll(true);
                }
                break;
            case R.id.tv_operation_explain:
                //操作说明
                break;
        }
    }


    private void buyAll() {
        if (goodsCount > 0) {
            showBillsDialog(goodsCount);
        } else {
            Toast.makeText(this, "至少选择一条数据", Toast.LENGTH_SHORT).show();
        }
    }

    public void showBillsDialog(final int bills) {
        myDialog = new MyDialog(this);
        myDialog.setMessage(getString(R.string.dialog_send_express_msg));
//        myDialog.setReceiptHint(bills + "", "" + goodsMoeny, true);
        myDialog.setYesOnclickListener(getString(R.string.True), new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                myDialog.dismiss();
                new MyToast(context, "发货成功" + bills + "条");
                deleteBills();
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

    @Override
    protected void onNavSearchClick() {
        super.onNavSearchClick();
        bundle.putInt(Constant.BUNDLE_TYPE, Constant.ECPRESS_SEND_ACTIVITY_TYPE);
        intentActivity(context, BillsSearchActivity.class, bundle);
    }

    @Override
    public void onCheckedY(int pos) {
        goodsCount++;
        goodsMoeny += Double.valueOf(mList.get(pos).getAwait_money());
        log("总金额：" + goodsMoeny);
    }

    @Override
    public void onCheckedN(int pos) {
        if (goodsCount > 0)
            goodsCount--;
        if (goodsMoeny > 0)
            goodsMoeny -= Double.valueOf(mList.get(pos).getAwait_money());
    }

    @Override
    public void onBillsIDItemCilckListener(int pos) {
        Intent intent = new Intent(context, BillsDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.BUNDLE_TYPE, mList.get(pos));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void selectAll(boolean isck) {
        for (int i = 0; i < mList.size(); i++) {
            // 改变boolean
            mList.get(i).setChecked(isck);
            // 如果为选中
            if (mList.get(i).isChecked()) {
                goodsCount++;
                goodsMoeny += Double.valueOf(mList.get(i).getAwait_money());
            } else {
                goodsCount = 0;
                goodsMoeny = 0;
            }
            sendAdapter.notifyDataSetChanged();
        }
    }

    private void deleteBills() {
        Iterator it = mList.iterator();
        while (it.hasNext()) {
            PayMnetInfo pInfo = (PayMnetInfo) it.next();
            // 判断
            if (pInfo.isChecked()) {
                // 从集合中删除上一次next方法返回的元素
                it.remove();
            }
        }
        // 刷新
        sendAdapter.notifyDataSetChanged();
        goodsCount = 0;
        goodsMoeny = 0;
        select_all_ck.setChecked(false);
    }

    /**
     * 加数据
     */
    public void getDatas() {
        mList = new ArrayList<>();
        for (int a = 0; a < 20; a++) {
            PayMnetInfo pInfo = new PayMnetInfo();
            pInfo.setBillsId("121212" + a);
            pInfo.setBillsType("销售" + a);
            pInfo.setFlowId("先货后款");
            pInfo.setBuyer("奥森学院");
            pInfo.setContractTime("2017-04-26");
            pInfo.setPaymentTime("2017-04-26");
            pInfo.setDeliveryStarTime("2017-04-26");
            pInfo.setDeliveryEndTime("2017-04-26");
            pInfo.setTotalMoney("2000" + a);
            pInfo.setPayId("暂无");
            pInfo.setPayId("中信银行");
            pInfo.setHarvestAddress("北京昌平区");
            pInfo.setAlready_money("300" + a);
            pInfo.setAwait_money("1700" + a);
            pInfo.setNow_money("1700" + a);
            pInfo.setPayment_remark("没有备注");
            mList.add(pInfo);
        }
    }
}