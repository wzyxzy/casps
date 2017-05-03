package com.hj.casps.commodity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by YaoChen on 2017/4/13.
 */

public class SelectPicture02 extends ActivityBaseHeader2 {
    private int PageFlag;
    private LinearLayout barLayout;
    private FancyButton uploadBt;
    private RecyclerView recycleView;
    private RelativeLayout topRelayout;
    private List<Map<String, Object>> data_list;
    private TextView barLayoutCancel;
    private TextView barLayoutSubmit;
    private final int RequestCodeForEditImageRes = 1028;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectpic02);
        initView();

        viewShow();
    }

    private void viewShow() {
        PageFlag = getIntent().getIntExtra("PicStyle", 0);
        if (PageFlag == 1) {
            setTitle(getString(R.string.select_title_pic));
            getData();
        } else if (PageFlag == 2) {
            setTitle(getString(R.string.select_banner_pic));
            barLayout.setVisibility(View.VISIBLE);
            getData();
        } else {
            setTitle("分类");
            topRelayout.setVisibility(View.VISIBLE);
            barLayout.setVisibility(View.VISIBLE);
            getData();
        }
    }

    private void getData() {
        BaseQuickAdapter adapter = (BaseQuickAdapter) recycleView.getAdapter();
        SelectPicture02ListEntity data1 = new SelectPicture02ListEntity();
        for (int i = 0; i < 10; i++) {
            data1.setImageName("圆白菜");
            data1.setImagePath("http://139.224.58.166:8089/data/upload//images/data/2016/11/21/400x400/file_93_1.jpg");
            adapter.addData(data1);
        }
    }

    private void initView() {
        barLayout = (LinearLayout) findViewById(R.id.selectpic02_ll);
        barLayoutCancel = (TextView) findViewById(R.id.selectpic02_cancel);
        barLayoutSubmit = (TextView) findViewById(R.id.selectpic02_true);
        topRelayout = (RelativeLayout) findViewById(R.id.selectctpic02_select_ll);
        recycleView = (RecyclerView) findViewById(R.id.selectpic02_grid);
        recycleView.setLayoutManager(new GridLayoutManager(this, 3));
        recycleView.setAdapter(new SelectPicture02Adapter());
        recycleView.addItemDecoration(new SelectPicture02ItemDecoration(this));
        recycleView.addOnItemTouchListener(new SelectPicture02ItemClick());
        uploadBt = (FancyButton) findViewById(R.id.selectctpic02_select_image);
        SelectPicture02Click l = new SelectPicture02Click();
        uploadBt.setOnClickListener(l);
        barLayoutCancel.setOnClickListener(l);
        barLayoutSubmit.setOnClickListener(l);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == RequestCodeForEditImageRes) {
                String imagePath = data.getStringExtra(SelectPicture03.ExtraImagePath);
                String imageName = data.getStringExtra(SelectPicture03.ExtraImageName);
                BaseQuickAdapter adapter = (BaseQuickAdapter) recycleView.getAdapter();
                SelectPicture02ListEntity data1 = new SelectPicture02ListEntity();
                data1.setImageName(imageName);
                data1.setImagePath(imagePath);
                adapter.addData(data1);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private class SelectPicture02Click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.selectctpic02_select_image) {
                /*选择图片，单选*/
                RxGalleryFinal.with(SelectPicture02.this)
                        .image()
                        .radio()
                        .imageLoader(ImageLoaderType.GLIDE)
                        .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageMultipleResultEvent) throws Exception {
                                log(imageMultipleResultEvent.getResult() + "张图片");
                                String imagePath = imageMultipleResultEvent.getResult().getOriginalPath();
                                onSelectImage(imagePath);
                            }

                            @Override
                            public void onCompleted() {
                                super.onCompleted();
                                Toast.makeText(getBaseContext(), "OVER", Toast.LENGTH_SHORT).show();
                            }
                        }).openGallery();
            } else if (id == R.id.selectpic02_cancel) {
                /*取消*/
                back();
            } else if (id == R.id.selectpic02_true) {
                /*确定*/
                onSubmit();
            }
        }
    }

    private void onSubmit() {
        Intent intent = new Intent();
        ArrayList<SelectPicture02ListEntity> checkListEntity = new ArrayList<>();
        SelectPicture02Adapter adapter = (SelectPicture02Adapter) recycleView.getAdapter();
        for (SelectPicture02ListEntity entity : adapter.getData()) {
            if (entity.isCheck) {
                checkListEntity.add(entity);
            }
        }
        intent.putParcelableArrayListExtra("data", checkListEntity);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void onSelectImage(String imagePath) {
        Intent intent = new Intent(this, SelectPicture03.class);
        intent.putExtra(SelectPicture03.ExtraImagePath, imagePath);
        startActivityForResult(intent, RequestCodeForEditImageRes);
    }

    private class SelectPicture02ItemClick extends SimpleClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            SelectPicture02ListEntity entity = (SelectPicture02ListEntity) adapter.getItem(position);
            entity.setCheck(!entity.isCheck);
            adapter.notifyItemChanged(position);
        }

        @Override
        public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

        }

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        }

        @Override
        public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

        }
    }

    private class SelectPicture02ListEntity implements Parcelable {
        private String imagePath;
        private String imageName;
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.imagePath);
            dest.writeString(this.imageName);
            dest.writeByte(this.isCheck ? (byte) 1 : (byte) 0);
        }

        public SelectPicture02ListEntity() {
        }

        protected SelectPicture02ListEntity(Parcel in) {
            this.imagePath = in.readString();
            this.imageName = in.readString();
            this.isCheck = in.readByte() != 0;
        }

        public final Parcelable.Creator<SelectPicture02ListEntity> CREATOR = new Parcelable.Creator<SelectPicture02ListEntity>() {
            @Override
            public SelectPicture02ListEntity createFromParcel(Parcel source) {
                return new SelectPicture02ListEntity(source);
            }

            @Override
            public SelectPicture02ListEntity[] newArray(int size) {
                return new SelectPicture02ListEntity[size];
            }
        };
    }

    private class SelectPicture02Adapter extends BaseQuickAdapter<SelectPicture02ListEntity, BaseViewHolder> {

        public SelectPicture02Adapter() {
            super(R.layout.selectpic_griditem, null);
        }

        @Override
        protected void convert(BaseViewHolder helper, SelectPicture02ListEntity item) {
            int width = getResources().getDisplayMetrics().widthPixels;
            width = (width-25 )/ 3;
            ViewGroup.LayoutParams layoutParams = helper.getConvertView().getLayoutParams();
            layoutParams.height = (int) (width * 1.1f);
            layoutParams.width=width;
            TextView textView = helper.getView(R.id.selectpic_tv);
            ImageView imageView = helper.getView(R.id.selectpic_pic_img);
            //假数据使用
//            if ((PageFlag == 1) || (PageFlag == 2)) {
//                Glide.with(SelectPicture02.this).load(item.getImagePath()).error(R.drawable.default_imgs).into(imageView);
//            } else {
                Glide.with(SelectPicture02.this).load(item.getImagePath()).into(imageView);
//            }
            textView.setText(item.getImageName());
            helper.setVisible(R.id.selectpic_ischeck, item.isCheck);

        }
    }

    /**
     * Gridlayoutmanager 分隔线
     * http://blog.csdn.net/lmj623565791/article/details/45059587
     */
    public static class SelectPicture02ItemDecoration extends RecyclerView.ItemDecoration {
        private final int[] ATTRS = new int[]{android.R.attr.listDivider};
        private Drawable mDivider;
        int intrinsicHeight;

        public SelectPicture02ItemDecoration(Context context) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            intrinsicHeight = mDivider.getIntrinsicHeight();
            a.recycle();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

            drawHorizontalLine(c, parent,state);
            drawVerticalLine(c, parent,state);

        }

        private int getSpanCount(RecyclerView parent) {
            // 列数
            int spanCount = -1;
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            }
            return spanCount;
        }

        public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state){
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++){
                final View child = parent.getChildAt(i);

                //获得child的布局信息
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
                //Log.d("wnw", left + " " + top + " "+right+"   "+bottom+" "+i);
            }
        }

        //画竖线
        public void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state){
            int top = parent.getPaddingTop();
            int bottom = parent.getHeight() - parent.getPaddingBottom();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++){
                final View child = parent.getChildAt(i);

                //获得child的布局信息
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        /**
         * 横向是否需要画分隔线
         *
         * @param parent
         * @param pos
         * @return
         */
        private boolean isColumnDrawDiv(RecyclerView parent, int pos) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutParam = (GridLayoutManager) layoutManager;
                int spanCount = gridLayoutParam.getSpanCount();
                int spanIndex = gridLayoutParam.getSpanSizeLookup().getSpanIndex(pos, spanCount);
                if (spanIndex == 0 || spanIndex == spanCount - 1) {
                    return false;
                } else {
                    return true;
                }
            }
            return false;
        }

        /**
         * 水平方向是否要画分隔线
         *
         * @param parent
         * @param pos
         * @return
         */
        private boolean isRowDrawDiv(RecyclerView parent, int pos) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                int itemCount = parent.getAdapter().getItemCount();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                int spanCount = gridLayoutManager.getSpanCount();
                int lastLineIndex = itemCount % spanCount == 0 ? (itemCount / spanCount) : (itemCount / spanCount + 1);
                if (pos >= spanCount * (lastLineIndex - 1)) {
                    return true;
                }
            }
            return true;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            int itemPosition = parent.getChildAdapterPosition(view);
            boolean isDrawRow = isRowDrawDiv(parent, itemPosition);
            boolean isDrawColumn = isColumnDrawDiv(parent, itemPosition);
            int rightPadding = 0, bottomPadding = 0;
            if (isDrawRow) {
                bottomPadding = intrinsicHeight;
            }
            if (isDrawColumn) {
                rightPadding = intrinsicHeight;
            }
            outRect.set(0, 0, rightPadding, bottomPadding);
        }
    }
}
