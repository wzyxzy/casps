package com.hj.casps.overdealmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.overdealadapter.CreateSectionAdapter;
import com.hj.casps.bankmanage.BillsSearchActivity;
import com.hj.casps.base.ActivityBaseHeader;
import com.hj.casps.common.Constant;
import com.hj.casps.entity.NewOpposite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * 新创建结款单列表
 */
public class CreateSectionBills extends ActivityBaseHeader implements View.OnClickListener,
        CreateSectionAdapter.OnClickSectionListener {
    @BindView(R.id.create_bills_list)
    ListView create_bills_list;
    @BindView(R.id.layout_head_left_btn)
    FancyButton layout_head_left_btn;
    @BindView(R.id.layout_head_right_tv)
    TextView layout_head_right_tv;

    private int indexMList;
    private CreateSectionAdapter createSectionAdapter;
    private NewOpposite newOpposite;
    private NewOpposite.OppositeInfo oppositeInfo;
    private List<NewOpposite.OppositeInfo> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_section_bills);
        ButterKnife.bind(this);
        getData();
        initView();
    }

    private void initView() {
        setTitle(getString(R.string.activity_create_section_bills_title));
        CreateSectionAdapter.setOnClickSectionListener(this);

        layout_head_left_btn.setText(getString(R.string.hint_tv_create_section_bills_title));
        layout_head_left_btn.setOnClickListener(this);
        layout_head_right_tv.setOnClickListener(this);

        createSectionAdapter = new CreateSectionAdapter(this, mList);
        create_bills_list.setAdapter(createSectionAdapter);
        createSectionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_head_left_btn:
                intentActivity(this, NewCreateSettlement.class);
                break;
            case R.id.layout_head_right_tv:
                break;
        }
    }

    @Override
    protected void onNavSearchClick() {
        super.onNavSearchClick();
        bundle.putInt(Constant.BUNDLE_TYPE, Constant.CREATE_SECTION_BILLS_ACTIVITY_TYPE);
        intentActivity(BillsSearchActivity.class, bundle);
    }

    @Override
    public void onClickBillsId(int pos) {
        //单据号点击
        Intent intent = new Intent(context, BillsSectionDetailsActivity.class);
        bundle.putSerializable(Constant.BUNDLE_TYPE, mList.get(pos));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void getData() {
        newOpposite = new NewOpposite();
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            oppositeInfo = new NewOpposite.OppositeInfo();
            oppositeInfo.setId("114514752188" + i);
            oppositeInfo.setSettleCode("487488955877" + i);
            oppositeInfo.setMmbgetName("结款对方" + i);
            oppositeInfo.setMmbpayName("结款对方" + i);
            oppositeInfo.setSettleMoney(536412.2 + i);
            oppositeInfo.setCtrTime("2015-04-0" + i);
            oppositeInfo.setCtrMoney(2565444.6 + i);
            mList.add(oppositeInfo);

            newOpposite.setmList(mList);
        }
    }
}
