package com.hj.casps.commodity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by YaoChen on 2017/4/13.
 * 商品详情页
 */

public class ActivityGoodDetail extends ActivityBaseHeader2 implements View.OnClickListener {
    @BindView(R.id.goodsdetail_banner)
    Banner banner;
    @BindView(R.id.goodsdetail_name)
    TextView detail_name;
    @BindView(R.id.goodsdetail_class)
    TextView detail_class;
    @BindView(R.id.goodsdetail_time)
    TextView detail_time;
    @BindView(R.id.goodsdetail_address)
    TextView detail_addr;
    @BindView(R.id.goodsdetail_pro)
    TextView detail_product;
    @BindView(R.id.goodsdetail_num)
    TextView detail_num;
    @BindView(R.id.goodsdetail_brands)
    TextView detail_brands;
    @BindView(R.id.goodsdetail_shelf_life)
    TextView shelf_life;
    @BindView(R.id.goodsdetail_Specificationss)
    TextView Specificationss;
    @BindView(R.id.goodsdetail_price)
    TextView price;
    @BindView(R.id.goodsdetail_desc)
    TextView desc;
    @BindView(R.id.goodsdetail_true_Btn)
    FancyButton submit;
    private String[] data = new String[]{"http://139.224.58.166:8089/data/upload//images/data/2016/11/21/400x400/01-18_6_.jpg",
            "http://139.224.58.166:8089/data/upload//images/data/2016/11/21/400x400/file_2_48_1.jpg",
            "http://139.224.58.166:8089/data/upload//images/data/2016/11/21/400x400/file_93_1.jpg"};
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.detail));
        setContentView(R.layout.activity_gooddetail);
        setTitleRight(null, null);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        findViewById(R.id.goodsdetail_true_Btn).setOnClickListener(this);
        setBannerData();
        initBar(list);
    }

    //假数据
    private void setBannerData() {
        list = new ArrayList<String>();
        for (int i = 0; i < data.length; i++) {
            list.add(i, data[i]);
        }
    }

    private void initBar(List<String> data) {
        //设置图片集合
        ImageLoader imageLoader = new BannerImageLoader();
        banner.setImageLoader(imageLoader);
        banner.setOffscreenPageLimit(Integer.MAX_VALUE);
        banner.setImages(data);
//        banner.setOnBannerClickListener(new HomeOnBannerClickListener(data));
        banner.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goodsdetail_true_Btn:
                startActivity(new Intent(ActivityGoodDetail.this, ActivityEditGoods.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (banner != null) {
            banner.startAutoPlay();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (banner != null) {
            banner.stopAutoPlay();
        }
    }

}
