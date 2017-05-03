package com.hj.casps.overdealmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hj.casps.R;
import com.hj.casps.adapter.overdealadapter.ExexuteAdapter;
import com.hj.casps.common.Constant;
import com.hj.casps.entity.execcte.ExecuteGain;
import com.hj.casps.ui.MyDialog;
import com.hj.casps.ui.MyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2017/5/2.
 */

public class ExecuteFragment extends Fragment implements ExexuteAdapter.onClickBillsListener {

    ListView execute_bills_listview;

    private Context context;
    private List<ExecuteGain> mList;
    private List<ExecuteGain.ExecuteBills> mBills;
    private ExexuteAdapter mExexuteAdapter;
    private View view;
    private int activity_type;
    private int tab_type;
    private MyDialog myDialog;

    public ExecuteFragment(Context context, int activity_type, int tab_type) {
        this.context = context;
        this.activity_type = activity_type;
        this.tab_type = tab_type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.execute_bills_list, container, false);
        execute_bills_listview = (ListView) view.findViewById(R.id.execute_bills_listview);
        getDate(tab_type);
        initView();
        return view;
    }

    private void initView() {
        mExexuteAdapter = new ExexuteAdapter(getActivity(), mList);
        execute_bills_listview.setAdapter(mExexuteAdapter);
        mExexuteAdapter.notifyDataSetChanged();

        ExexuteAdapter.setOnClickBillsListener(this);
    }

    @Override
    public void onBillsIDItemCilckListener(int pos) {
        intentActivity(BillsSectionDetailsActivity.class, Constant.EXECUTE_BILLS_ACTIVITY_TYPE);
    }

    @Override
    public void onBtnClickListener(int pos) {
        showBillsDialog();
    }

    public void getDate(int type) {
        switch (type) {
            case Constant.STATUS_TYPE_0:
                setList("366565", "65568687425", "天宇学院", "交通大厦", 63.25, "2016.04.0", 65.3, 9836);
                break;
            case Constant.STATUS_TYPE_1:
                setList("43654555", "65658455", "国贸学院", "败诉", 63.25, "2016.04.0", 65.3, 9836);
                break;
            case Constant.STATUS_TYPE_2:
                setList("36543435", "6544585", "榆林", "沙河", 63.25, "2016.04.0", 65.3, 9836);
                break;
            case Constant.STATUS_TYPE_3:
                setList("3654898", "65444455", "职业技术学院", "北大", 63.25, "2016.04.0", 65.3, 9836);
                break;
        }
    }


    private void setList(String id, String settleCode, String mmbgetName, String mmbpayName,
                         double settleMoney, String ctrTime, double ctrMoney,
                         double gotMoney) {
        mList = new ArrayList<>();
        mBills = new ArrayList<>();
        ExecuteGain eg = new ExecuteGain();
        eg.setPagecount(Integer.valueOf(id));
        eg.setReturn_code(Integer.valueOf(id));
        for (int i = 0; i < 20; i++) {
            ExecuteGain.ExecuteBills bills = new ExecuteGain.ExecuteBills();
            bills.setId(id + i);
            bills.setSettleCode(settleCode + i);
            bills.setMmbgetName(mmbgetName + i);
            bills.setMmbpayName(mmbpayName + i);
            bills.setSettleMoney(settleMoney + i);
            bills.setCtrTime(ctrTime + i);
            bills.setCtrMoney(ctrMoney + i);
            bills.setGotMoney(gotMoney + i);
            bills.setStatus(String.valueOf((int) Math.floor(Math.random() * 6 + 4)));
            mBills.add(bills);
            eg.setmList(mBills);
            mList.add(eg);
        }
    }


    public void showBillsDialog() {
        myDialog = new MyDialog(getActivity());
        myDialog.setMessage(getString(R.string.dialog_stop_msg));
        myDialog.setYesOnclickListener(getString(R.string.True), new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                myDialog.dismiss();
                new MyToast(context, getString(R.string.toast_stop_msg));

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


    public void intentActivity(Class toClass, int ac_type) {
        Intent intent = new Intent(getActivity(), toClass);
        startActivityForResult(intent, ac_type);
    }
}
