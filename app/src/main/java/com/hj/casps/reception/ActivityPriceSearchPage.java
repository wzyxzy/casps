package com.hj.casps.reception;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YaoChen on 2017/4/18.
 */

public class ActivityPriceSearchPage extends ActivityBaseHeader2 {
    private ListView listView;
    private List<Map<String, Object>> mdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleRight(null, null);
        setTitle(getString(R.string.search));
        setContentView(R.layout.activity_search_price);
        initView();
        //假数据
        mdata = getData();
        listView.setAdapter(new SimpleAdapter(this, getData(), R.layout.listitem_check_sample,
                new String[]{"title"},
                new int[]{R.id.sample_text}));
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.search_price_name_list);

    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "业务合作会员");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "关注会员");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "所在群组");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "本省公开报价");
        list.add(map);

        return list;

    }

}
