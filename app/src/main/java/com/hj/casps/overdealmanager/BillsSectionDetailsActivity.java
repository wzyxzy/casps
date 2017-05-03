package com.hj.casps.overdealmanager;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hj.casps.R;
import com.hj.casps.adapter.overdealadapter.SectionDetailsAdapter;
import com.hj.casps.adapter.wrapper.LoadMoreWrapper;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.entity.SectionEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * 订单详情页
 */
public class BillsSectionDetailsActivity extends ActivityBaseHeader2 implements OnPullListener {
    private SectionEntity sectionEntity;

    @BindView(R.id.bills_details_rlview)
    RecyclerView bills_details_rlview;
    private SectionDetailsAdapter mRecyclerAdapter;

    List<SectionEntity> listOne;
    List<SectionEntity.OrderList> listTow;
    List<String> listTitle;
    LoadMoreWrapper mLoadMoreWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_aetails);
        ButterKnife.bind(this);
        getDatas();

        initView();
        getBundleDatas();
    }

    private void initView() {
        setTitle(getString(R.string.activity_over_bills_details_title));
        titleRight.setVisibility(View.GONE);

        bills_details_rlview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mLoader = new NestRefreshLayout(bills_details_rlview);
        mLoader.setOnLoadingListener(this);
        mLoader.setPullLoadEnable(true);
        mLoader.setPullRefreshEnable(true);

    }

    public void getBundleDatas() {
//        sectionEntity = (SectionEntity) getIntent().getSerializableExtra(Constant.BUNDLE_TYPE);

        listOne = new ArrayList<>();
        listOne.add(sectionEntity);
        listTitle = new ArrayList<>();
        listTitle.add("结款单清单");

        mRecyclerAdapter = new SectionDetailsAdapter(this, listOne, listTow, listTitle);
        mLoadMoreWrapper = new LoadMoreWrapper(this, mRecyclerAdapter);
        mLoadMoreWrapper.setOnLoadListener(new LoadMoreWrapper.OnLoadListener() {
            @Override
            public void onRetry() {
                //重试处理
            }

            @Override
            public void onLoadMore() {
                //加载更多
                //在加载数据的时候调用一下 showLoadMore()，
                // 数据加载出错的时候调用一下showLoadError()，
                // 数据加载完成的时候调用 showLoadComplete()。
                //加载结束调用disableLoadMore();
                mLoadMoreWrapper.showLoadMore();
            }
        });
        bills_details_rlview.setAdapter(mLoadMoreWrapper);
        mRecyclerAdapter.notifyDataSetChanged();
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


    /**
     * 加数据
     */
    public void getDatas() {
        sectionEntity = new SectionEntity();
        listOne = new ArrayList<>();
        listTow = new ArrayList<>();
        for (int a = 0; a < 20; a++) {
            sectionEntity.setReturn_code(a);
            sectionEntity.setReturn_message("返回成功" + a);
            sectionEntity.setMmbpayName("买方" + a);
            sectionEntity.setSettleCode("9874563654123" + a);
            sectionEntity.setSettleMoney(600.5 + a);
            sectionEntity.setMmbgetName("卖方" + a);
            sectionEntity.setCtrTime("2016-04-0" + a);
            sectionEntity.setCtrMoney(6548.36 + a);
            sectionEntity.setStatus((int) Math.floor(Math.random() * 7 + 1));
            sectionEntity.setStatusSingn((int) Math.floor(Math.random() * 4 + 1));
            sectionEntity.setStatusRegist((int) Math.floor(Math.random() * 4 + 1));

            SectionEntity.OrderList orderList = new SectionEntity.OrderList();
            orderList.setOredertitleNumber("12546987456245" + a);
            orderList.setGoodsName("商品" + a);
            orderList.setOrdermoney(5463.2 + a);
            orderList.setMoney(96324.9 + a);
            listTow.add(orderList);
            sectionEntity.setmOrderList(listTow);

            listOne.add(sectionEntity);
        }
    }
}
