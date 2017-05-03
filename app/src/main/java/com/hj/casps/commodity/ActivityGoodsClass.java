package com.hj.casps.commodity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by YaoChen on 2017/4/13.
 */

public class ActivityGoodsClass extends ActivityBaseHeader2 implements View.OnClickListener {
    @BindView(R.id.Goodsclass_grid)
    GridView gridView;
    @BindView(R.id.Goodsclass_add_Btn)
    FancyButton top_add_btn;
    @BindView(R.id.Goodsclass_do_desc_tv)
    TextView top_desc_Tv;
    private PopupWindow sharepopupWindow;
    private View contentView;

    //假数据
    private List<Map<String, Object>> data_list;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc, R.mipmap.up_sc,};
    private String[] iconName = {"蔬菜", "果品", "畜类", "禽类", "禽类", "水产", "蛋类"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsclass);
        ButterKnife.bind(this);
        initView();
        setTitleRight(null, null);
        //假数据
        data_list = new ArrayList<Map<String, Object>>();
        getData01();
        String[] from = {"image", "text"};
        int[] to = {R.id.selectpic_pic_img, R.id.selectpic_tv};
        gridView.setAdapter(new GoodClassListAdapter(this, data_list, R.layout.selectpic_griditem, from, to));
    }

    private void initView() {
        showSharePopupWindow();
        top_add_btn.setOnClickListener(this);
        top_desc_Tv.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!sharepopupWindow.isShowing()) {
                    sharepopupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
                }
            }
        });

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

    private void showSharePopupWindow() {
        contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_goodsclass, null);
        contentView.findViewById(R.id.gooddeclass_pop_do_view).setOnClickListener(this);
        contentView.findViewById(R.id.gooddeclass_pop_do_cancel).setOnClickListener(this);
        contentView.findViewById(R.id.gooddeclass_do_edit).setOnClickListener(this);
        contentView.findViewById(R.id.gooddeclass_do_detail).setOnClickListener(this);

        sharepopupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        sharepopupWindow.setAnimationStyle(R.style.take_photo_anim);
        sharepopupWindow.setTouchable(true);
        sharepopupWindow.setOutsideTouchable(true);
        sharepopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gooddeclass_pop_do_view:
                sharepopupWindow.dismiss();
                break;
            case R.id.gooddeclass_pop_do_cancel:
                sharepopupWindow.dismiss();
                break;
            case R.id.gooddeclass_do_edit:
                sharepopupWindow.dismiss();
                startActivity(new Intent(ActivityGoodsClass.this, ActivityEditGoods.class));
                break;
            case R.id.gooddeclass_do_detail:
                sharepopupWindow.dismiss();
                startActivity(new Intent(ActivityGoodsClass.this, ActivityGoodDetail.class));
                break;
            case R.id.Goodsclass_add_Btn:
                startActivity(new Intent(ActivityGoodsClass.this, ActivityEditGoods.class));
                break;
            case R.id.Goodsclass_do_desc_tv:
                Log.i("desc", "操作说明");
                break;
        }

    }

    class GoodClassListAdapter extends SimpleAdapter {

        public GoodClassListAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            int width = getResources().getDisplayMetrics().widthPixels;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                width -= gridView.getHorizontalSpacing() * 2;
            }
            width = width / 3;
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = (int) (width * 1.1f);
            view.findViewById(R.id.selectpic_ischeck).setVisibility(View.GONE);
            return view;
        }
    }
}
