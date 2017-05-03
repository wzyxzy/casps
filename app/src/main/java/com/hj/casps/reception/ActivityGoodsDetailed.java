package com.hj.casps.reception;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.ui.MyToast;
import com.youth.banner.Banner;

/**
 * Created by YaoChen on 2017/4/18.
 */

public class ActivityGoodsDetailed extends ActivityBaseHeader2 implements View.OnClickListener {
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gooddetailed);
        setTitleRight(null, null);
        setTitle(getString(R.string.detailed));
        initView();
    }

    private void initView() {
        banner = (Banner) findViewById(R.id.goodsdetailed_banner);
        banner.setOnClickListener(this);
        findViewById(R.id.goodsdetailed_addshopcart).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goodsdetailed_banner:
                startActivity(new Intent(ActivityGoodsDetailed.this, ActivityPriceGoodsDetail.class));
                break;
            case R.id.goodsdetailed_addshopcart:
                new MyToast(ActivityGoodsDetailed.this, "加入购物车成功");
                break;
        }
    }
}
