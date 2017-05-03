package com.hj.casps.reception;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader;
import com.hj.casps.ui.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * Created by YaoChen on 2017/4/14.
 */

public class ActivityPriceSearch extends ActivityBaseHeader implements View.OnClickListener, OnPullListener {
    private GridView gridview;
    private ImageView totop;
    AbsRefreshLayout mLoader;
    private ScrollView scrollView;
    private TextView styleTv;
    private int styleid;


    //假数据
    private List<Map<String, Object>> data_list;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc,};
    private String[] iconName = {"冷冻大肉", "冷冻大肉", "冷冻大肉", "冷冻大肉", "冷冻大肉", "冷冻大肉", "冷冻大肉", "冷冻大肉", "冷冻大肉", "冷冻大肉", "冷冻大肉", "冷冻大肉"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.prices_search));
        setContentView(R.layout.activity_price_search);

        initView();
        //假数据
        data_list = new ArrayList<Map<String, Object>>();
        getData01();
        String[] from = {"image", "text"};
        int[] to = {R.id.price_search_grid_img, R.id.price_search_grid_name};
        gridview.setAdapter(new SimpleAdapter(this, data_list, R.layout.pricesearch_grid_item, from, to));

    }

    private void initView() {
        gridview = (MyGridView) findViewById(R.id.price_search_grid);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ActivityPriceSearch.this, ActivityGoodsDetailed.class));
            }
        });
        styleTv=(TextView)findViewById(R.id.price_search_class_tv);
        styleid = getIntent().getIntExtra("Style", 0);

        if (styleid == 0) {
            styleTv.setText(getString(R.string.buy));
        } else {
            styleTv.setText(getString(R.string.sale));
        }
        totop = (ImageView) findViewById(R.id.price_search_totop);
        totop.setOnClickListener(this);
        scrollView = (ScrollView) findViewById(R.id.price_search_Ll);
        mLoader = new NestRefreshLayout(scrollView);
        mLoader.setOnLoadingListener(this);
        mLoader.setPullLoadEnable(true);
        mLoader.setPullRefreshEnable(true);

    }

    public List<Map<String, Object>> getData01() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }

    @Override
    protected void onNavSearchClick() {
        startActivity(new Intent(ActivityPriceSearch.this, ActivityPriceSearchPage.class));
        super.onNavSearchClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.price_search_totop:
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                break;
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
