package com.hj.casps.overdealmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.overdealadapter.CreateSettlDetailsAdapter;
import com.hj.casps.adapter.overdealadapter.ViewHolderCreaSettlTwo;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.common.Constant;
import com.hj.casps.entity.CommodityDetailsEntity;
import com.hj.casps.entity.OverBillsDtailsEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * 创建结款单详情
 */
public class NewCreateSettlDetails extends ActivityBaseHeader2 implements View.OnClickListener,
        OnPullListener, ViewHolderCreaSettlTwo.onCheckedkType {
    @BindView(R.id.settl_recyclerview)
    RecyclerView settl_recyclerview;

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


    private CreateSettlDetailsAdapter createSettlDetailsAdapter;
    private List<OverBillsDtailsEntity> overList;
    public static List<CommodityDetailsEntity> commList;
    private List<String> title;
    private int ac_type = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_create_settl_details);
        ButterKnife.bind(this);
        getData();

        initView();
    }

    private void initView() {
        setTitle(getString(R.string.hint_tv_create_section_bills_title));
        layout_bottom_layout_1.setOnClickListener(this);
        layout_bottom_check_1.setOnClickListener(this);
        layout_bottom_tv_2.setOnClickListener(this);
        layout_bottom_tv_3.setOnClickListener(this);
        layout_bottom_tv_4.setOnClickListener(this);

        ViewHolderCreaSettlTwo.setOnCheckedkType(this);
        findViewById(R.id.layout_head_left_btn).setVisibility(View.GONE);

        createSettlDetailsAdapter = new CreateSettlDetailsAdapter(this, overList, commList, title);
        settl_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        settl_recyclerview.setAdapter(createSettlDetailsAdapter);

        mLoader = new NestRefreshLayout(settl_recyclerview);
        mLoader.setOnLoadingListener(this);
        mLoader.setPullLoadEnable(true);
        mLoader.setPullRefreshEnable(true);

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
//                    selectAll(true);
                }
                break;
            case R.id.layout_bottom_tv_2:
                break;
            case R.id.layout_bottom_tv_3:
                intentActivity(AddCommodityInfo.class, ac_type);
                break;
            case R.id.layout_bottom_tv_4:
                break;
        }
    }

    private void getData() {
        overList = new ArrayList<>();
        commList = new ArrayList<>();
        title = new ArrayList<>();
        OverBillsDtailsEntity oEntity = new OverBillsDtailsEntity();
        oEntity.setSellersId("6995544785");
        oEntity.setSellersName("长城");
        oEntity.setBuyersId("f12312312");
        oEntity.setBuyersName("哈啊哈哈服");
        oEntity.setCtrTime("2016-04-04");
        overList.add(oEntity);
        title.add("订单清单");
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ac_type && resultCode == Constant.ADDCOMMODITY_ADD) {
            if (commList.size() > 0)
                createSettlDetailsAdapter.refreshList(commList);
        }
    }

    @Override
    public void onCheckedY(int pos) {
        toastSHORT("" + pos);
    }

    @Override
    public void onCheckedN(int pos) {
        toastSHORT("" + pos);
    }

    private void selectAll(boolean isck) {
        for (int i = 0; i < commList.size(); i++) {
            // 改变boolean
            commList.get(i).setCheck(isck);
        }
        if (commList.size() > 0)
            createSettlDetailsAdapter.refreshList(commList);
    }
}
