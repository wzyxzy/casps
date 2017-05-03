package com.hj.casps.commodity;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YaoChen on 2017/4/13.
 */

public class SelectClass extends ActivityBaseHeader2 {
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.select_class));
        setContentView(R.layout.activity_selectclass);
        setTitleRight(null,null);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GoodListAdapter(generateData()));
        recyclerView.addItemDecoration(new GoodDividerItemDecoration(this));
        recyclerView.addOnItemTouchListener(new GoodSimpleItemClick());
    }

    private ArrayList<GoodLevelEntity> generateData() {
        ArrayList<GoodLevelEntity> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GoodLevelEntity lv0 = new GoodLevelEntity(GoodListAdapter.TYPE_LEVEL_0, i + "");
            lv0.setParent(null);
            for (int j = 0; j < 5; j++) {
                GoodLevelEntity lv1 = new GoodLevelEntity(GoodListAdapter.TYPE_LEVEL_1, i + "-" + j);
                lv1.setParent(lv0);
                for (int x = 0, size = 5; x < size; x++) {
                    GoodLevelEntity lv2 = new GoodLevelEntity(GoodListAdapter.TYPE_LEVEL_2, i + "-" + j + "-" + x);
                    lv1.addSubItem(lv2);
                    lv2.setParent(lv1);
                }
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        return res;
    }

    private class GoodListAdapter extends BaseMultiItemQuickAdapter<GoodLevelEntity, BaseViewHolder> {
        public static final int TYPE_LEVEL_0 = 0;
        public static final int TYPE_LEVEL_1 = 1;
        public static final int TYPE_LEVEL_2 = 2;

        public GoodListAdapter(List<GoodLevelEntity> data) {
            super(data);
            addItemType(TYPE_LEVEL_0, R.layout.activity_baseheader_left_item1);
            addItemType(TYPE_LEVEL_1, R.layout.activity_baseheader_left_item1);
            addItemType(TYPE_LEVEL_2, R.layout.activity_baseheader_left_item2);
        }

        @Override
        protected void convert(BaseViewHolder holder, GoodLevelEntity item) {
            switch (holder.getItemViewType()) {
                case TYPE_LEVEL_0:
                    convertLevel0(holder, item);
                    break;
                case TYPE_LEVEL_1:
                    convertLevel1(holder, item);
                    break;
                case TYPE_LEVEL_2:
                    convertLevel2(holder, item);
                    break;
            }
        }

        /*一级*/
        private void convertLevel0(final BaseViewHolder holder, final GoodLevelEntity lv0) {
            holder.setText(R.id.item_name, lv0.getName());
            holder.setImageResource(R.id.item_iv, lv0.isExpanded() ? R.mipmap.jt3 : R.mipmap.jt1);
        }

        /*二级*/
        private void convertLevel1(final BaseViewHolder holder, final GoodLevelEntity lv1) {
            holder.setText(R.id.item_name, lv1.getName());
            holder.setTextColor(R.id.item_name, getResources().getColor(R.color.grey));
            holder.getView(R.id.item_name).setPadding(20, 0, 0, 0);
            if (lv1.hasSubItem()) {
                holder.setVisible(R.id.item_iv, true);
                holder.setImageResource(R.id.item_iv, lv1.isExpanded() ? R.mipmap.jt3 : R.mipmap.jt1);
            } else {
                holder.setVisible(R.id.item_iv, false);
            }
        }

        /*三级*/
        private void convertLevel2(final BaseViewHolder holder, final GoodLevelEntity lv2) {
            holder.setText(R.id.item_name, lv2.getName());
            int leftPadding = 40;
            if (lv2.getParent().getLevel() == TYPE_LEVEL_0) {
                leftPadding = 20;
            }
            holder.getView(R.id.item_name).setPadding(leftPadding, 0, 0, 0);
        }

    }

    private void onItemClick1(GoodLevelEntity lv1) {
        log("onItemClick() called with: " + "lv1 = [" + lv1 + "]");
    }

    /**
     * 左侧导航的适配器
     */
    public class GoodLevelEntity extends AbstractExpandableItem implements MultiItemEntity {
        private final int type;
        private String name;
        private GoodLevelEntity parent;


        public GoodLevelEntity getParent() {
            return parent;
        }

        public void setParent(GoodLevelEntity parent) {
            this.parent = parent;
        }

        /**
         * @param type   只能为 GoodListAdapter中的type level常量
         * @param string
         */
        public GoodLevelEntity(int type, String string) {
            this.type = type;
            this.name = string;
        }

        @Override
        public void setExpanded(boolean expanded) {
            super.setExpanded(expanded);
            if (hasSubItem() && !expanded) {
                for (int j = 0, size = getSubItems().size(); j < size; j++) {
                    GoodLevelEntity levelChild = (GoodLevelEntity) getSubItems().get(j);
                    levelChild.setExpanded(false);
                }
            }
        }

        public String getName() {
            return name;
        }


        @Override
        public int getLevel() {
            return type;
        }

        @Override
        public int getItemType() {
            return getLevel();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GoodLevelEntity)) return false;

            GoodLevelEntity that = (GoodLevelEntity) o;

            if (type != that.type) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return parent != null ? parent.equals(that.parent) : that.parent == null;

        }

        @Override
        public int hashCode() {
            int result = type;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

    /**
     * 二级目录不显示分隔线
     * 水平分隔线
     */
    private class GoodDividerItemDecoration extends RecyclerView.ItemDecoration {
        private final int[] ATTRS = new int[]{android.R.attr.listDivider};
        private Drawable mDivider;

        public GoodDividerItemDecoration(Activity activity) {
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
            GoodListAdapter adapter = (GoodListAdapter) parent.getAdapter();
            GoodLevelEntity levelEntity = adapter.getItem(adapterPosition);
            if (levelEntity != null) {
                /*一级元素 且收缩的*/
                if (levelEntity.hasSubItem() && !levelEntity.isExpanded()) {
                    return true;
                }
                /*二级元素 且最后一个元素时*/
                if (!levelEntity.hasSubItem() && levelEntity.getParent() != null) {
                    GoodLevelEntity levelEntityParent = levelEntity.getParent();
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

    private class GoodSimpleItemClick extends SimpleClickListener {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int pos) {
            log("onItemClick() called with: " + "adapter = [" + adapter + "], view = [" + view + "], position = [" + pos + "]");
            GoodLevelEntity entity = (GoodLevelEntity) adapter.getItem(pos);
            if (!entity.hasSubItem()) {
                onItemClick1(entity);
                return;
            }
            if (entity.isExpanded()) {
                if (entity.getLevel() == GoodListAdapter.TYPE_LEVEL_0) {
                    collapseLevel0All(adapter, entity);
                } else {
                    adapter.collapse(pos, false);
                }
            } else {
                 /*关闭之前展开的*/
                if (entity.getLevel() == GoodListAdapter.TYPE_LEVEL_0) {
                    expandLevel0(pos, adapter, entity);
                } else {
//                    adapter.expand(pos, false);
                    expandLevel1(pos, adapter, entity);
                }
            }
        }

        /**
         * 二级目录点击
         *
         * @param pos
         * @param adapter
         * @param entity  二级的bean
         */
        private void expandLevel1(int pos, BaseQuickAdapter adapter, GoodLevelEntity entity) {
            adapter.expand(pos);
            entity.setExpanded(true);
            GoodLevelEntity levelEntity1 = entity.getParent();
            List<GoodLevelEntity> levelEntity2List = levelEntity1.getSubItems();
            for (int i = levelEntity2List.size() - 1; i > -1; i--) {
                GoodLevelEntity levelEntity2 = levelEntity2List.get(i);/*二级目录*/
                if (levelEntity2 != null && entity != levelEntity2) {
                    adapter.getData().removeAll(levelEntity2.getSubItems()); /*删除三级目录*/
                    levelEntity2.setExpanded(false);
                }
            }
            adapter.notifyDataSetChanged();
        }

        /**
         * 一级目录收缩时
         * 二级目录也收缩
         *
         * @param adapter
         * @param entity  一级目录
         */
        private void collapseLevel0All(BaseQuickAdapter adapter, GoodLevelEntity entity) {
            List<GoodLevelEntity> levelEntity2List = entity.getSubItems();
            for (int i = levelEntity2List.size() - 1; i > -1; i--) {
                GoodLevelEntity levelEntity2 = levelEntity2List.get(i);/*二级目录*/
                if (levelEntity2 != null && levelEntity2.isExpanded()) {
                    adapter.getData().removeAll(levelEntity2.getSubItems()); /*删除三级目录*/
                }
                levelEntity2.setExpanded(false);
                adapter.getData().removeAll(levelEntity2List);
            }
            entity.setExpanded(false);
            adapter.notifyDataSetChanged();
        }

        /**
         * @param pos
         * @param adapter
         * @param entity  除些之外的entity全部关闭
         */
        private void expandLevel0(int pos, BaseQuickAdapter adapter, GoodLevelEntity entity) {
            adapter.getData().addAll(pos + 1, entity.getSubItems());

            for (int i = adapter.getItemCount() - 1; i > -1 && i < adapter.getData().size(); i--) {
                GoodLevelEntity entityItem = (GoodLevelEntity) adapter.getItem(i); /*一级*/
                if (entityItem.getLevel() == GoodListAdapter.TYPE_LEVEL_0) {
                    if (entity == entityItem) {
                        entity.setExpanded(true);
                    } else {
                        entityItem.setExpanded(false);
                        List<GoodLevelEntity> levelEntity2List = entityItem.getSubItems(); /*二级*/
                        for (GoodLevelEntity levelEntity2 : levelEntity2List) {
                            if (levelEntity2.getSubItems() != null) {
                                levelEntity2.setExpanded(false);
                                adapter.getData().removeAll(levelEntity2.getSubItems()); /*删除三级*/
                            }
                        }
                        adapter.getData().removeAll(levelEntity2List);/*删除二级*/
                    }
                }

            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
            log("onItemLongClick() called with: " + "adapter = [" + adapter + "], view = [" + view + "], position = [" + position + "]");
        }

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            log("onItemChildClick() called with: " + "adapter = [" + adapter + "], view = [" + view + "], position = [" + position + "]");
        }

        @Override
        public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
            log("onItemChildLongClick() called with: " + "adapter = [" + adapter + "], view = [" + view + "], position = [" + position + "]");
        }
    }

    {
    }
}
