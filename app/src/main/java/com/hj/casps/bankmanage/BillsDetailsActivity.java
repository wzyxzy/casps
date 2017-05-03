package com.hj.casps.bankmanage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hj.casps.R;
import com.hj.casps.adapter.payadapter.BillsDetailsAdapter;
import com.hj.casps.adapter.wrapper.LoadMoreWrapper;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.common.Constant;
import com.hj.casps.entity.PayMnetInfo;

import java.util.ArrayList;
import java.util.List;

import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * 订单详情页
 */
public class BillsDetailsActivity extends ActivityBaseHeader2 implements OnPullListener {
    private PayMnetInfo payMnetInfo;
    private RecyclerView bills_details_rlview;
    private BillsDetailsAdapter mRecyclerAdapter;

    List<PayMnetInfo> listOne;
    List<PayMnetInfo.CommodityIndo> listTwo;
    List<String> listTitle;
    LoadMoreWrapper mLoadMoreWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_aetails);
        initView();
        getBundleDatas();
    }

    private void initView() {
        setTitle(getString(R.string.activity_bills_details_title));
        titleRight.setVisibility(View.GONE);

        bills_details_rlview = (RecyclerView) findViewById(R.id.bills_details_rlview);
        bills_details_rlview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mLoader = new NestRefreshLayout(bills_details_rlview);
        mLoader.setOnLoadingListener(this);
        mLoader.setPullLoadEnable(true);
        mLoader.setPullRefreshEnable(true);

    }

    public void getBundleDatas() {
        payMnetInfo = (PayMnetInfo) getIntent().getSerializableExtra(Constant.BUNDLE_TYPE);

        listOne = new ArrayList<>();
        listOne.add(payMnetInfo);
        listTwo = new ArrayList<>();
        listTitle = new ArrayList<>();
        listTitle.add("商品清单");
        setDatas();
        mRecyclerAdapter = new BillsDetailsAdapter(this, listOne, listTwo, listTitle);
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

    private void setDatas() {
        for (int i = 0; i < 10; i++) {
            PayMnetInfo.CommodityIndo comm = new PayMnetInfo.CommodityIndo();
            comm.setGoodsName("窝瓜" + i);
            comm.setGoodsWaitCount(400 + i + "");
            comm.setGoodsSumMoney(800 + i + "");
            comm.setGoodsHarvestCount(400 + i + "");
            comm.setGoodsOneMoney(6 + i + "");
            comm.setGoodsWaitMoney(600 + i + "");
            comm.setGoodsSumCount(50 + i + "");
            comm.setGoodsWaitCount(400 + i + "");
            comm.setGoodsHarvestMoney(400 + i + "");
            listTwo.add(comm);
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

}
