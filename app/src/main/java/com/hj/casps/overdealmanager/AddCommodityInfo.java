package com.hj.casps.overdealmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.overdealadapter.AddCommodityAdapter;
import com.hj.casps.bankmanage.BillsSearchActivity;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.common.Constant;
import com.hj.casps.entity.CommodityDetailsEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

public class AddCommodityInfo extends ActivityBaseHeader2 implements View.OnClickListener,
        OnPullListener, AddCommodityAdapter.onCheckedkType {
    @BindView(R.id.add_commodity_info)
    ListView add_commodity_info;
    @BindView(R.id.add_affirm_btn)
    TextView add_affirm_btn;
    @BindView(R.id.add_details_all)
    LinearLayout add_details_all;
    @BindView(R.id.add_details_all_ck)
    CheckBox add_details_all_ck;

    private AddCommodityAdapter addAdapter;
    private List<CommodityDetailsEntity> mList;
    private List<CommodityDetailsEntity> mList2;
    private List<Integer> indexPos;//判断选中的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_commodity_info);
        ButterKnife.bind(this);

        getDatas();

        initView();
    }

    private void initView() {
        setTitle(getString(R.string.hint_tv_add_bills_title));
        AddCommodityAdapter.setOnCheckedkType(this);
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setEnabled(true);
        mLoader = new NestRefreshLayout(add_commodity_info);
        mLoader.setOnLoadingListener(this);
        mLoader.setPullLoadEnable(true);
        mLoader.setPullRefreshEnable(true);
        add_affirm_btn.setOnClickListener(this);
        add_details_all.setOnClickListener(this);
        add_details_all_ck.setOnClickListener(this);

        addAdapter = new AddCommodityAdapter(this, mList);
        add_commodity_info.setAdapter(addAdapter);
        addAdapter.notifyDataSetChanged();

        indexPos = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_affirm_btn:
                deleteCommList();
                break;
            case R.id.add_details_all:
                if (add_details_all_ck.isChecked()) {
                    add_details_all_ck.setChecked(false);
                    selectAll(false);
                } else {
                    add_details_all_ck.setChecked(true);
                    selectAll(true);
                }
                break;
        }
    }

    private void deleteCommList() {
        NewCreateSettlDetails.commList.clear();
        setResult(Constant.ADDCOMMODITY_ADD);
        if (indexPos.size() > 0) {
            for (int i = 0; i < indexPos.size(); i++) {
                mList.remove(indexPos.get(i));
            }
        }
        NewCreateSettlDetails.commList = mList2;
        addAdapter.refreshList(mList);
        finish();
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

    public void getDatas() {
        mList = new ArrayList<>();
        mList2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CommodityDetailsEntity comm = new CommodityDetailsEntity();
            comm.setOredertitleNumber("9899841215" + i);
            comm.setGoodsName("商品名" + i);
            comm.setAwaitmoney(894445.45 + i);
            comm.setMoney(2256.54 + i);
            comm.setOrdermoney(9887.0 + i);
            mList.add(comm);
        }
        mList2.clear();
    }

    @Override
    public void onCheckedY(int pos) {
        indexPos.add(pos);
        mList2.add(mList.get(pos));
        Log.v("Yshow", NewCreateSettlDetails.commList.toString());
    }

    @Override
    public void onCheckedN(int pos) {
        mList2.remove(mList.get(pos));
        indexPos.remove(pos);
        Log.v("Yshow", NewCreateSettlDetails.commList.toString());
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        bundle.putInt(Constant.BUNDLE_TYPE, Constant.ADD_SECTION_BILLS_ACTIVITY_TYPE);
        intentActivity(context, BillsSearchActivity.class, bundle);
    }

    private void selectAll(boolean isck) {
        for (int i = 0; i < mList.size(); i++) {
            // 改变boolean
            mList.get(i).setCheck(isck);
            if (mList.get(i).isCheck()) {
                mList2.add(mList.get(i));
            } else {
                mList2.remove(mList.get(i));
            }
            addAdapter.notifyDataSetChanged();
        }
    }
}
