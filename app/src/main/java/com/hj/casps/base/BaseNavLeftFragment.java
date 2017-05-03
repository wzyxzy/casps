package com.hj.casps.base;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hj.casps.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by YaoChen Administrator on 2017/4/14.
 */

public class BaseNavLeftFragment extends FragmentBase {

    private Unbinder butterKnife;
    @BindView(R.id.base_header_left_rv)
    RecyclerView recyclerView;
    @BindView(R.id.base_header_left_cart_num)
    TextView cartNumTv;
    @BindView(R.id.base_header_left_user_name_tv)
    TextView userNameTv;
    @BindView(R.id.base_header_left_user_iv)
    ImageView userAvatorIv;
    @BindView(R.id.base_nav_menu_bt1)
    FancyButton bt1;
    @BindView(R.id.base_nav_menu_bt2)
    FancyButton bt2;
    @BindView(R.id.base_nav_menu_bt3)
    FancyButton bt3;
    BaseLeftListener listener;
    private String[][] level_0 = {{"基础设置","商品管理"},
            {"报价管理","报价列表","创建报价"},
            {"关系管理","业务合作会员目录","关系会员管理","待审批申请","群组管理"},
            {"协议管理","已提交合作协议","待审批合作协议","已完成合作协议"},
            {"订单管理","新建订单列表","待审批订单列表","执行中订单列表","采购拣单车","销售拣单车"},
            {"收发货管理","地址管理","收货","发货","退货","退货签收"},
            {"收付款管理","银行账号","付款","收款","退款","收退款"},
            {"结款单管理","新建结款单","待审批结款单","执行中结款单","登记担保资源"},
            {"操作员管理","操作员列表","操作员添加","修改密码"}};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_basehead_left_fragment_layout, container, false);
        butterKnife = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    @OnClick({R.id.base_header_left_cart_layout, R.id.base_header_left_exit, R.id.base_header_back,
            R.id.base_header_left_user_iv, R.id.base_header_left_user_name_tv})
    void onSimpleClick(View v) {
        if (listener != null) {
            final int vId = v.getId();
            if (vId == R.id.base_header_left_cart_layout) {
                listener.onCartClick(v);
            } else if (vId == R.id.base_header_left_exit) {
                listener.onExitClick(v);
            } else if (vId == R.id.base_header_back) {
                listener.onBackSysClick(v);
            } else if (vId == R.id.base_header_left_user_iv ||
                    vId == R.id.base_header_left_user_name_tv) {
                listener.onUserClick(v);
            }

        }
    }

    @OnClick({R.id.base_nav_menu_bt1, R.id.base_nav_menu_bt2, R.id.base_nav_menu_bt3})
    void onBtClick(View v) {
        if (listener != null) {
            final int vId = v.getId();
            int index = 0;
            if (vId == R.id.base_nav_menu_bt1) {
                index = 0;
            } else if (vId == R.id.base_nav_menu_bt2) {
                index = 1;
            } else if (vId == R.id.base_nav_menu_bt3) {
                index = 2;
            }
            listener.onBtClick(v, index);
        }
    }

    private void initView() {
        FragmentActivity activity = getActivity();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new BaseListNavAdapter(generateData()));
//        recyclerView.addItemDecoration(new BaseListDividerItemDecoration(activity));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        butterKnife.unbind();
    }

    private ArrayList<BaseLeftLevelEntity> generateData() {
        ArrayList<BaseLeftLevelEntity> res = new ArrayList<>();
        for (int i = 0; i < level_0.length; i++) {
            BaseLeftLevelEntity lv0 = new BaseLeftLevelEntity(level_0[i][0]);
            lv0.setParent(null);
            for (int j = 1; j < level_0[i].length; j++) {
                BaseLeftLevelEntity lv1 = new BaseLeftLevelEntity(level_0[i][j]);
                lv1.setParent(lv0);
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        return res;
    }

    void setData(BaseLeftViewData data, List<BaseLeftLevelEntity> listEntity) {
        Glide.with(getActivity()).load(data.useravator).into(userAvatorIv);
        userNameTv.setText(data.userName);
        bt1.setText(data.bt1Str);
        bt2.setText(data.bt2Str);
        if (TextUtils.isEmpty(data.bt3Str)) {
            bt3.setVisibility(View.GONE);
        } else {
            bt3.setVisibility(View.VISIBLE);
            bt3.setText(data.bt3Str);
        }
        recyclerView.setAdapter(new BaseListNavAdapter(listEntity));
    }

    void setCartNun(int num) {
        cartNumTv.setText(num + "");
    }

    public class BaseLeftViewData {
        private String useravator;
        private String userName;
        private String bt1Str;
        private String bt2Str;
        private String bt3Str;

        public void setUseravator(String useravator) {
            this.useravator = useravator;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setBt1Str(String bt1Str) {
            this.bt1Str = bt1Str;
        }

        public void setBt2Str(String bt2Str) {
            this.bt2Str = bt2Str;
        }

        public void setBt3Str(String bt3Str) {
            this.bt3Str = bt3Str;
        }
    }

    void setListener(BaseLeftListener listener) {
        this.listener = listener;
    }

    interface BaseLeftListener {
        /*购物车*/
        void onCartClick(View v);

        /*退出*/
        void onExitClick(View v);

        /*返回系统*/
        void onBackSysClick(View v);

        /*用户头像姓名点击事件*/
        void onUserClick(View v);

        /*列表中点击事件*/
        void onListItemClick(BaseLeftLevelEntity levelEntity);

        /*采购 等事件*/
        void onBtClick(View v, int index);
    }

    private class BaseListNavAdapter extends BaseMultiItemQuickAdapter<BaseLeftLevelEntity, BaseViewHolder> {
        public static final int TYPE_LEVEL_0 = 0;
        public static final int TYPE_LEVEL_1 = 1;

        public BaseListNavAdapter(List<BaseLeftLevelEntity> data) {
            super(data);
            addItemType(TYPE_LEVEL_0, R.layout.activity_baseheader_left_item1);
            addItemType(TYPE_LEVEL_1, R.layout.activity_baseheader_left_item2);
        }

        @Override
        protected void convert(BaseViewHolder holder, BaseLeftLevelEntity item) {
            switch (holder.getItemViewType()) {
                case TYPE_LEVEL_0:
                    convertParent(holder, item);
                    break;
                case TYPE_LEVEL_1:
                    convertChild(holder, item);
                    break;
            }
        }

        private void convertParent(final BaseViewHolder holder, final BaseLeftLevelEntity lv0) {
            holder.setText(R.id.item_name, lv0.getName());
            holder.setImageResource(R.id.item_iv, lv0.isExpanded() ? R.mipmap.jt3 : R.mipmap.jt1);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    log("convertParent:" + pos);
                    if (lv0.isExpanded()) {
                        collapse(pos);
                    } else {
                         /*要先伸开，否则此位置就不对应了*/
                        expand(pos);
                        int lastExpand = -1;
                        for (int i = getHeaderLayoutCount(); i < getItemCount(); i++) {
                            BaseLeftLevelEntity entity = getItem(i - getHeaderLayoutCount());
                            if (entity.isExpanded() && entity != lv0) {
                                lastExpand = i;
                                break;
                            }
                        }
                        if (lastExpand > -1) {
                            collapse(lastExpand);
                        }
                    }
                }
            });
        }

        private void convertChild(final BaseViewHolder holder, final BaseLeftLevelEntity lv1) {
            holder.setText(R.id.item_name, lv1.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    if (listener != null) {
                        listener.onListItemClick(lv1);
                    }
                }


            });
        }

    }

    /**
     * 左侧导航的适配器
     */
    public class BaseLeftLevelEntity extends AbstractExpandableItem implements MultiItemEntity {
        private String name;
        private BaseLeftLevelEntity parent;


        public BaseLeftLevelEntity getParent() {
            return parent;
        }

        public void setParent(BaseLeftLevelEntity parent) {
            this.parent = parent;
        }

        public BaseLeftLevelEntity(String string) {
            this.name = string;
        }

        public String getName() {
            return name;
        }


        @Override
        public int getLevel() {
            return hasSubItem() ? BaseListNavAdapter.TYPE_LEVEL_0 : BaseListNavAdapter.TYPE_LEVEL_1;
        }

        @Override
        public int getItemType() {
            return getLevel();
        }
    }

    /**
     * 二级目录不显示分隔线
     * 水平分隔线
     */
    private class BaseListDividerItemDecoration extends RecyclerView.ItemDecoration {
        private final int[] ATTRS = new int[]{android.R.attr.listDivider};
        private Drawable mDivider;

        public BaseListDividerItemDecoration(Activity activity) {
            final TypedArray a = activity.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            if (checkLevelOne(view, parent)) {
                super.getItemOffsets(outRect, view, parent, state);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }

        /**
         * @param view
         * @param parent
         * @return true 应该绘制分隔线 一级菜单未展开的 二级菜单展开的最后一个
         */
        private boolean checkLevelOne(View view, RecyclerView parent) {
            int adapterPosition = parent.getChildAdapterPosition(view);
            BaseListNavAdapter adapter = (BaseListNavAdapter) parent.getAdapter();
            BaseLeftLevelEntity levelEntity = adapter.getItem(adapterPosition);
            if (levelEntity != null) {
                /*一级元素 且收缩的*/
                if (levelEntity.hasSubItem() && !levelEntity.isExpanded()) {
                    return true;
                }
                /*二级元素 且最后一个元素时*/
                if (!levelEntity.hasSubItem() && levelEntity.getParent() != null) {
                    BaseLeftLevelEntity levelEntityParent = levelEntity.getParent();
                    List subItems = levelEntityParent.getSubItems();
                    Object lastEntity = levelEntityParent.getSubItem(subItems.size() - 1);
                    if (levelEntity == lastEntity) {
                        return true;
                    }
                }

            }
            return false;
        }

        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
            final int childSize = parent.getChildCount();
            for (int i = 0; i < childSize; i++) {
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + layoutParams.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                if (mDivider != null && checkLevelOne(child, parent)) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }
            }
        }

    }
}
