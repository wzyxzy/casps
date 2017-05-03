package com.hj.casps.protocolmanager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader;
import com.hj.casps.common.Constant;
import com.hj.casps.ordermanager.OrderSearch;

import java.util.ArrayList;
import java.util.List;

public class RequestProtocol extends ActivityBaseHeader {

    private TabLayout fragment_protocol_tabLayout;
    private ViewPager fragment_protocol_viewPager;
    private FragmentViewPagerAdapter adapter;
    private List<Fragment> data;
    private List<String> dataText;
    private int type;
    private int fra_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_protocol);
        initData();
        initView();
    }

    private void initData() {
        type = getIntent().getIntExtra(Constant.PROTOCOL_TYPE, Constant.protocol_0);
        fra_type = getIntent().getIntExtra(Constant.FRA_TYPE, Constant.fra_1);
        switch (fra_type) {
            case Constant.fra_1:
                data = new ArrayList<>();
                data.add(new ProtocolFragment(type, 0, 1));
                data.add(new ProtocolFragment(type, 1, 1));
                data.add(new ProtocolFragment(type, 2, 1));

                dataText = new ArrayList<>();
                dataText.add(getString(R.string.all_protocol));
                dataText.add(getString(R.string.buy_protocol));
                dataText.add(getString(R.string.sell_protocol));
                switch (type) {
                    case 0:
                        setTitle(getString(R.string.protocol_request_title));
                        break;
                    case 1:
                        setTitle(getString(R.string.protocol_require_title));
                        break;
                    case 2:
                        setTitle(getString(R.string.protocol_got_title));
                        break;
                }
                break;
            case Constant.fra_2:
                data = new ArrayList<>();
                data.add(new ProtocolFragment(type, 0, 2));
                data.add(new ProtocolFragment(type, 1, 2));
                data.add(new ProtocolFragment(type, 2, 2));

                dataText = new ArrayList<>();
                dataText.add(getString(R.string.all_protocol));
                dataText.add(getString(R.string.buy));
                dataText.add(getString(R.string.sale));
                switch (type) {
                    case 0:
                        setTitle(getString(R.string.order_new));
                        break;
                    case 1:
                        setTitle(getString(R.string.order_request));
                        break;
                    case 2:
                        setTitle(getString(R.string.order_doing));
                        break;
                }
                break;

        }


    }

    private void initView() {

        fragment_protocol_tabLayout = (TabLayout) findViewById(R.id.fragment_protocol_tabLayout);
        fragment_protocol_viewPager = (ViewPager) findViewById(R.id.fragment_protocol_viewPager);
        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), data, dataText);
        fragment_protocol_viewPager.setAdapter(adapter);

        fragment_protocol_tabLayout.setupWithViewPager(fragment_protocol_viewPager);
    }

    @Override
    protected void onNavSearchClick() {
        super.onNavSearchClick();
        switch (fra_type) {
            case Constant.fra_1:
                bundle.putInt(Constant.PROTOCOL_TYPE, type);
                intentActivity(ProtocolSearch.class, bundle);
                break;
            case Constant.fra_2:
                bundle.putInt(Constant.PROTOCOL_TYPE, type);
                intentActivity(OrderSearch.class, bundle);
                break;

        }
    }
}
