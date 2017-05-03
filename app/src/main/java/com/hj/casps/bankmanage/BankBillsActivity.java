package com.hj.casps.bankmanage;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.casps.R;
import com.hj.casps.adapter.expressadapter.ExpressAdapte;
import com.hj.casps.adapter.payadapter.AddCardAdapte;
import com.hj.casps.base.ActivityBaseHeader;
import com.hj.casps.common.Constant;
import com.hj.casps.entity.CardInfoEntity;
import com.hj.casps.entity.ExpressInfoEntity;
import com.hj.casps.ui.MyDialog;
import com.hj.casps.ui.MyToast;

import java.util.ArrayList;
import java.util.List;

import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * 账户管理列表
 */
public class BankBillsActivity extends ActivityBaseHeader implements View.OnClickListener,
        AddCardAdapte.onClickItem,
        ExpressAdapte.onClickItem,
        OnPullListener {
    private TextView add_card_btn;
    private ListView bank_card_list;
    private AddCardAdapte addCardAdapte;
    private ExpressAdapte expressAdapter;
    private List<CardInfoEntity> mList;
    private List<ExpressInfoEntity> mExpressList;
    private int billsActivity_type = 10;
    private int indexMList = 0;
    private Bundle bundle;
    private MyDialog myDialog;
    private int activityType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_bills);
        initView();
        getBundle();
    }

    public void getBundle() {
        activityType = getIntent().getIntExtra(Constant.BUNDLE_TYPE, 0);
        switch (activityType) {
            case Constant.BANK_BILLS_ACTIVITY_TYPE:
                setBnakBills();
                break;
            case Constant.ECPRESS_ADDRESS_ACTIVITY_TYPE:
                setEcpressAddress();
                break;
        }
    }

    /**
     * 银行账户管理
     */
    private void setBnakBills() {
        setTitle(getString(R.string.activity_title_bank_bills_tv));
        addCardAdapte = new AddCardAdapte(mList, this);
        addCardAdapte.notifyDataSetChanged();
        bank_card_list.setAdapter(addCardAdapte);
        AddCardAdapte.setOnClickItem(this);
    }

    /**
     * 收发货地址
     */
    private void setEcpressAddress() {
        setTitle(getString(R.string.hint_tv_express_activity_title));
        add_card_btn.setText(getText(R.string.hint_ed_express_add_title));
        expressAdapter = new ExpressAdapte(mExpressList, this);
        expressAdapter.notifyDataSetChanged();
        bank_card_list.setAdapter(expressAdapter);
        ExpressAdapte.setOnClickItem(this);
    }

    private void initView() {
        add_card_btn = (TextView) findViewById(R.id.add_card_btn);
        add_card_btn.setOnClickListener(this);
        bank_card_list = (ListView) findViewById(R.id.bank_card_list);
        mLoader = new NestRefreshLayout(bank_card_list);
        mLoader.setOnLoadingListener(this);
        mLoader.setPullLoadEnable(true);
        mLoader.setPullRefreshEnable(true);
        getDatas();
        bundle = new Bundle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_card_btn:
                if (activityType == Constant.BANK_BILLS_ACTIVITY_TYPE) {
                    bundle.putInt(Constant.CARD_TYPE, Constant.CARD_ADD);
                } else {
                    bundle.putInt(Constant.CARD_TYPE, Constant.ADDRESS_ADD);
                }
                intentActivity(EditCardActivity.class, billsActivity_type, bundle);
                break;
        }
    }

    public void getDatas() {
        mList = new ArrayList<>();
        mExpressList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            CardInfoEntity entity = new CardInfoEntity("北京长城商贸公司" + i,
                    "99530001387" + i, "建设银行" + i,
                    "牛仁", "15155100622", "010-2368952");
            mList.add(entity);
            ExpressInfoEntity expressInfoEntity = new ExpressInfoEntity("北京昌平区百善飞机" + i,
                    "4785944721477414" + i, "国行飞机" + i,
                    "麻花特纳" + i, "15155100622", "010-2368952");
            mExpressList.add(expressInfoEntity);
        }
    }

    @Override
    public void onCilckItemEdit(int pos) {
        indexMList = pos;
        switch (activityType) {
            case Constant.BANK_BILLS_ACTIVITY_TYPE:
                CardInfoEntity cardInfoEntity = mList.get(pos);
                bundle.putSerializable(Constant.BUNDLE_TYPE, cardInfoEntity);
                bundle.putInt(Constant.CARD_TYPE, Constant.CARD_EDIT);
                break;
            case Constant.ECPRESS_ADDRESS_ACTIVITY_TYPE:
                ExpressInfoEntity expressInfoEntity = mExpressList.get(pos);
                bundle.putSerializable(Constant.BUNDLE_TYPE, expressInfoEntity);
                bundle.putInt(Constant.CARD_TYPE, Constant.ADDRESS_EDIT);
                break;
        }
        intentActivity(EditCardActivity.class, billsActivity_type, bundle);
    }

    @Override
    protected void onNavSearchClick() {
        super.onNavSearchClick();
        if (activityType == Constant.BANK_BILLS_ACTIVITY_TYPE) {
            bundle.putInt(Constant.BUNDLE_TYPE, Constant.BANK_BILLS_ACTIVITY_TYPE);
        } else {
            bundle.putInt(Constant.BUNDLE_TYPE, Constant.ECPRESS_ADDRESS_ACTIVITY_TYPE);
        }
        intentActivity(CardSearchActivity.class, bundle);
    }

    @Override
    public void onClickItemDelete(int pos) {
        indexMList = pos;
        showBillsDialog();
    }


    private void showBillsDialog() {
        myDialog = new MyDialog(this);
        myDialog.setMessage(getString(R.string.dialog_card_delete_msg));
        myDialog.setYesOnclickListener(getString(R.string.True), new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                myDialog.dismiss();
                if (activityType == Constant.BANK_BILLS_ACTIVITY_TYPE) {
                    mList.remove(indexMList);
                    new MyToast(BankBillsActivity.this, "删除银行账户成功");
                    refreshListAddCard();
                } else {
                    mExpressList.remove(indexMList);
                    new MyToast(BankBillsActivity.this, "删除成功");
                    refreshListExpress();
                }
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


    /**
     * 刷新listview中的数据
     */
    private void refreshListAddCard() {
        addCardAdapte.refreshList(mList);
    }

    /**
     * 刷新listview中的数据
     */
    private void refreshListExpress() {
        expressAdapter.refreshList(mExpressList);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * @author haijian
         * 收到返回的值判断是否成功，如果同意就将数据移除刷新列表
         */
        if (requestCode == billsActivity_type && resultCode == Constant.CARD_EDIT) {
            if (Constant.cardInfoEntity != null) {
                mList.set(indexMList, Constant.cardInfoEntity);
                judgeType();
            } else {
                //没有修改或者新增
            }
        } else if (requestCode == billsActivity_type && resultCode == Constant.CARD_ADD) {
            if (Constant.cardInfoEntity != null) {
                mList.add(0, Constant.cardInfoEntity);
                judgeType();
                Toast.makeText(this, "添加数据", Toast.LENGTH_SHORT).show();
            } else {
                //没有修改或者新增
            }
        } else if (requestCode == billsActivity_type && resultCode == Constant.ADDRESS_EDIT) {
            //修改地址
            if (Constant.expressInfoEntity != null) {
                mExpressList.set(indexMList, Constant.expressInfoEntity);
                judgeType();
            } else {
                //没有修改或者新增
            }
        } else if (requestCode == billsActivity_type && resultCode == Constant.ADDRESS_ADD) {
            //修改地址
            if (Constant.expressInfoEntity != null) {
                mExpressList.add(0, Constant.expressInfoEntity);
                judgeType();
            } else {
                //没有修改或者新增
            }
        }
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

    private void judgeType() {
        if (activityType == Constant.BANK_BILLS_ACTIVITY_TYPE) {
            refreshListAddCard();
        } else {
            refreshListExpress();
        }
    }


}
