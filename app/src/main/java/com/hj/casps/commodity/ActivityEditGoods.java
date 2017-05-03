package com.hj.casps.commodity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.ui.MyGridView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by YaoChen on 2017/4/11.
 * 商品编辑页面
 *
 */

public class ActivityEditGoods extends ActivityBaseHeader2 implements View.OnClickListener {
    @BindView(R.id.editGoods_select_pic)
    ImageView selectPic;
    @BindView(R.id.editGoods_time_Tv)
    TextView productDate;
    @BindView(R.id.goodsedit_gooddetail01_ll)
    LinearLayout title01;
    @BindView(R.id.goodsedit_gooddetail02_ll)
    LinearLayout title02;
    @BindView(R.id.goodsedit_gooddetail01)
    TextView title1;
    @BindView(R.id.goodsedit_gooddetail02)
    TextView title2;
    @BindView(R.id.edit_grid)
    MyGridView gridView;
    @BindView(R.id.editGoods_do_desc_tv)
    TextView top_desc;
    @BindView(R.id.editGoods_select_class_tv)
    TextView good_class;
    @BindView(R.id.editGoods_name_Et)
    EditText goodname;
    @BindView(R.id.editGoods_pro_addr_Et)
    EditText goodaddress;
    @BindView(R.id.editGoods_pro_pre_Et)
    EditText product_Et;
    @BindView(R.id.editGoods_num_Et)
    EditText product_num;
    @BindView(R.id.editGoods_brand_Et)
    EditText brand_et;
    @BindView(R.id.editGoods_stock_Et)
    EditText stock_Et;
    @BindView(R.id.editGoods_shelf_life_Et)
    EditText shelf_life_Et;
    @BindView(R.id.editGoods_Specifications_Et)
    EditText Specification_Et;
    @BindView(R.id.editGoods_Specifications_Spi)
    TextView Specification_Tv;
    @BindView(R.id.editGoods_price01_Et)
    EditText price_from_Et;
    @BindView(R.id.editGoods_price02_Et)
    EditText price_to_Et;
    @BindView(R.id.editGoods_price_Spi)
    TextView price_Tv;
    @BindView(R.id.editGoods_descr)
    EditText desc_Et;
    @BindView(R.id.editGoods_true_Btn)
    FancyButton submit_Fcybtn;
    public static Bitmap bimap;
    private List<Bitmap> mResults = new ArrayList<>();
    private GridAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bimap = BitmapFactory.decodeResource(getResources(), R.mipmap.up_sc);
        mResults.add(bimap);
        setTitle(getString(R.string.editgood));
        setContentView(R.layout.activity_editgoods);
        setTitleRight(null, null);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        selectPic.setOnClickListener(this);
        title1.setOnClickListener(this);
        title2.setOnClickListener(this);
        price_from_Et.addTextChangedListener(new TextOnChangeListener());
        price_to_Et.addTextChangedListener(new TextOnChangeListener());

        findViewById(R.id.editGoods_select_class_Ll).setOnClickListener(this);
        productDate.setOnClickListener(this);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == (mResults.size() - 1)) {
                    Intent intent = new Intent(ActivityEditGoods.this, SelectPicture01.class);
                    intent.putExtra("PicStyle", 2);
                    startActivity(intent);
                }
            }
        });

    }

    /**
     * 日历控件的显示
     */
    private void calendarShow() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(ActivityEditGoods.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                productDate.setText(new StringBuilder().append(year).append("-")
                        .append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1))
                        .append("-").append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    /**
     * 表单提交的本地判断
     *
     * @return
     */
    private boolean isCheck() {
        if (good_class.getText().length() == 0) {
            toast(getString(R.string.error_good_class));
            return false;
        }
        if (goodname.getText().length() == 0) {
            toast(getString(R.string.error_good_name));
            return false;
        }
        if (price_from_Et.getText().length() == 0) {
            toast(getString(R.string.error_good_price));
            return false;
        }
        if (price_to_Et.getText().length() == 0) {
            toast(getString(R.string.error_good_price));
            return false;
        }
        maxlengthToast(goodname);
        maxlengthToast(goodaddress);
        maxlengthToast(product_Et);
        maxlengthToast(product_num);
        maxlengthToast(brand_et);
        maxlengthToast(shelf_life_Et);
        maxlengthToast(Specification_Et);
        maxlengthToast(price_from_Et);
        maxlengthToast(price_to_Et);
        if (desc_Et.getText().length()>200){
            toast(getString(R.string.error_lengtn_200));
            return false;
        }

        return true;
    }

    private boolean maxlengthToast(EditText Et){
        if (Et.getText().length()>50){
            toast(getString(R.string.error_lengtn_50));
            return false;
        }
        return true;
    }

    /**
     * 更换textView的drawable
     *
     * @param id
     * @param tv
     */
    private void setDrawable(int id, TextView tv) {
        Drawable topDrawable = ContextCompat.getDrawable(this, id);
        topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
        tv.setCompoundDrawables(null, null, topDrawable, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editGoods_select_pic:
                Intent intent = new Intent(ActivityEditGoods.this, SelectPicture01.class);
                intent.putExtra("PicStyle", 1);
                startActivity(intent);
                break;
            case R.id.editGoods_select_class_Ll:
                startActivity(new Intent(ActivityEditGoods.this, SelectClass.class));
                break;
            case R.id.editGoods_time_Tv:
                calendarShow();
                break;
            case R.id.goodsedit_gooddetail01:
                if (title01.getVisibility() == View.VISIBLE) {
                    title01.setVisibility(View.GONE);
                    setDrawable(R.mipmap.jt2, title1);
                } else {
                    title01.setVisibility(View.VISIBLE);
                    setDrawable(R.mipmap.jt3, title1);

                }
                break;
            case R.id.goodsedit_gooddetail02:
                if (title02.getVisibility() == View.VISIBLE) {
                    title02.setVisibility(View.GONE);
                    setDrawable(R.mipmap.jt2, title2);
                } else {
                    title02.setVisibility(View.VISIBLE);
                    setDrawable(R.mipmap.jt3, title2);
                }
                break;
        }
    }

    /**
     * 选择轮播图的GridView
     */
    public class GridAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }


        public int getCount() {
            return mResults.size();
        }

        public Object getItem(int arg0) {
            return mResults.get(arg0);
        }

        public long getItemId(int arg0) {
            return arg0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.editgoods_grid_item, null);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView
                        .findViewById(R.id.editGoods_grid_select_pic);
                holder.img = (ImageView) convertView
                        .findViewById(R.id.editGoods_grid_select_pic_delete);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position == (mResults.size() - 1)) {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(
                        getResources(), R.mipmap.up_sc));
                holder.img.setVisibility(View.GONE);
            } else {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(
                        getResources(), R.mipmap.up_sc));
                holder.img.setVisibility(View.VISIBLE);
            }

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mResults.remove(position);
//                    adapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }

        public class ViewHolder {
            public ImageView image;
            public ImageView img;
        }
    }

    /**
     * 输入框只能输入最多两位小数的数字
     */
    private class TextOnChangeListener implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String temp = s.toString();
            int posDot = temp.indexOf(".");
            if (posDot <= 0) return;
            if (temp.length() - posDot - 1 > 2) {
                s.delete(posDot + 3, posDot + 4);
            }
        }
    }
}
