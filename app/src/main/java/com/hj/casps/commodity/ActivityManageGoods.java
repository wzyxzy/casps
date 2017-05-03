package com.hj.casps.commodity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by YaoChen on 2017/4/12.
 */

public class ActivityManageGoods extends ActivityBaseHeader implements View.OnClickListener {
    @BindView(R.id.managegoods_list)
    ListView listView;
    @BindView(R.id.managegoods_grid)
    GridView gridView;
    @BindView(R.id.managegoods_add_Btn)
    FancyButton top_add_btn;
    @BindView(R.id.managegoods_do_desc_tv)
    TextView top_desc_Tv;
    private List<Map<String, Object>> mdata;
    private LeftAdapter listadapter;
    //假数据
    private List<Map<String, Object>> data_list;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc,};
    private String[] iconName = {"蔬菜", "果品", "畜类", "禽类", "禽类", "水产", "蛋类"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.goodmanage));
        setContentView(R.layout.activity_managegoods);
        setTitleRight(null, null);
        ButterKnife.bind(this);
        initView();
        findViewById(R.id.managegoods_top_Re).setVisibility(View.VISIBLE);
        //假数据
        mdata = getData();
        listadapter = new LeftAdapter(this);
        listView.setAdapter(listadapter);
        data_list = new ArrayList<Map<String, Object>>();
        getData01();
        String[] from = {"image", "text"};
        int[] to = {R.id.managegoods_grid_right_pic, R.id.managegoods_grid_right_tv};
        gridView.setAdapter(new SimpleAdapter(this, data_list, R.layout.managegoods_right_grid_item, from, to));
    }

    private void initView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listadapter.changeSelected(position);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ActivityManageGoods.this, ActivityGoodsClass.class));
            }
        });
        top_add_btn.setOnClickListener(this);
        top_desc_Tv.setOnClickListener(this);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "副食品");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "粮油");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "调味品");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "加工食品");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "文具办公");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "日用品");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "工具材料");
        list.add(map);
        return list;

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.managegoods_add_Btn:
                startActivity(new Intent(ActivityManageGoods.this, ActivityEditGoods.class));
                break;
            case R.id.managegoods_do_desc_tv:
                Log.i("desc", "操作说明");
                break;
        }
    }

    public final class ViewHolder {
        public TextView title;
    }

    public class LeftAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private int mSelect = 0;

        public void changeSelected(int positon) { //刷新方法
            if (positon != mSelect) {
                mSelect = positon;
                notifyDataSetChanged();
            }
        }

        public LeftAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mdata.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.managegoods_left_list_item, null);
                holder.title = (TextView) convertView.findViewById(R.id.managegoods_list_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (mSelect == position) {
                holder.title.setBackgroundResource(R.color.white);  //选中项背景
                holder.title.setTextColor(getResources().getColor(R.color.title_bg));
            } else {
                holder.title.setBackgroundResource(R.color.main_bg);  //其他项背景
                holder.title.setTextColor(getResources().getColor(R.color.black));
            }
            holder.title.setText((String) mdata.get(position).get("title"));
            return convertView;
        }

    }
}
