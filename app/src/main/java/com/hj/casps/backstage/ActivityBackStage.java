package com.hj.casps.backstage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hj.casps.R;
import com.hj.casps.bankmanage.BankBillsActivity;
import com.hj.casps.bankmanage.PaymentActivity;
import com.hj.casps.bankmanage.ReceiptActivity;
import com.hj.casps.bankmanage.ReceiveRefundMoenyActivity;
import com.hj.casps.bankmanage.RefundActivity;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.commodity.ActivityManageGoods;
import com.hj.casps.common.Constant;
import com.hj.casps.cooperate.CooperateContents;
import com.hj.casps.cooperate.CooperateDirectory;
import com.hj.casps.cooperate.CooperateRequest;
import com.hj.casps.cooperate.GroupManager;
import com.hj.casps.expressmanager.HarvestExpress;
import com.hj.casps.expressmanager.QuitExpress;
import com.hj.casps.expressmanager.QuitHarvestExpress;
import com.hj.casps.expressmanager.SendExpress;
import com.hj.casps.operatormanager.OperatorAdd;
import com.hj.casps.operatormanager.OperatorListActivity;
import com.hj.casps.operatormanager.OperatorPassWord;
import com.hj.casps.ordermanager.BuyCart;
import com.hj.casps.overdealmanager.CheckWaitBills;
import com.hj.casps.overdealmanager.CreateSectionBills;
import com.hj.casps.overdealmanager.ExecuteBills;
import com.hj.casps.overdealmanager.RegisterAssureBills;
import com.hj.casps.protocolmanager.RequestProtocol;
import com.hj.casps.quotes.CreateQuotes;
import com.hj.casps.quotes.QuoteQuery;
import com.hj.casps.util.LogToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YaoChen on 2017/4/17.
 */

public class ActivityBackStage extends ActivityBaseHeader2 {
    RecyclerView recyclerView;
    private String[][] level_0 = {{"基础设置", "商品管理"},
            {"报价管理", "报价列表", "创建报价"},
            {"关系管理", "业务合作会员目录", "关系会员管理", "待审批申请", "群组管理"},
            {"协议管理", "已提交合作协议", "待审批合作协议", "已完成合作协议"},
            {"订单管理", "新建订单列表", "待审批订单列表", "执行中订单列表", "采购拣单车", "销售拣单车"},
            {"收发货管理", "地址管理", "收货", "发货", "退货", "退货签收"},
            {"收付款管理", "银行账号", "付款", "收款", "退款", "收退款"},
            {"结款单管理", "新建结款单", "待审批结款单", "执行中结款单", "登记担保资源"},
            {"操作员管理", "操作员列表", "操作员添加", "修改密码"}};
    private int img[] = {R.mipmap.icon_t1, R.mipmap.icon_t2, R.mipmap.icon_t3, R.mipmap.icon_t4, R.mipmap.icon_t5, R.mipmap.icon_t6,
            R.mipmap.icon_t7, R.mipmap.icon_t8, R.mipmap.icon_t9};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backstage);
        setTitle(getString(R.string.app_name_big));
        initView();

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.backstage_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BaseListNavAdapter(generateData()));
    }

    private ArrayList<LevelEntity> generateData() {
        ArrayList<LevelEntity> res = new ArrayList<>();
        for (int i = 0; i < level_0.length; i++) {
            LevelEntity lv0 = new LevelEntity(BaseListNavAdapter.TYPE_LEVEL_0, level_0[i][0], img[i]);
            for (int j = 1; j < level_0[i].length; j++) {
                LevelEntity lv1 = new LevelEntity(BaseListNavAdapter.TYPE_LEVEL_0, level_0[i][j]);
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        return res;
    }

    private class BaseListNavAdapter extends BaseMultiItemQuickAdapter<LevelEntity, BaseViewHolder> {
        public static final int TYPE_LEVEL_0 = 0;
        public static final int TYPE_LEVEL_1 = 1;

        public BaseListNavAdapter(List<LevelEntity> data) {
            super(data);
            addItemType(TYPE_LEVEL_0, R.layout.activity_baseheader_left_item1);
            addItemType(TYPE_LEVEL_1, R.layout.activity_baseheader_left_item2);
        }

        @Override
        protected void convert(BaseViewHolder holder, LevelEntity item) {
            switch (holder.getItemViewType()) {
                case TYPE_LEVEL_0:
                    convertParent(holder, item);
                    break;
                case TYPE_LEVEL_1:
                    convertChild(holder, item);
                    break;
            }
        }


        private void convertParent(final BaseViewHolder holder, final LevelEntity lv0) {

            holder.setText(R.id.item_name, lv0.getName());
            holder.setVisible(R.id.item_img, true);
            holder.setImageResource(R.id.item_img, lv0.getId());
//            holder.setTextColor(R.id.item_name, lv0.isExpanded() ?
//                    mContext.getResources().getColor(R.color.blue) :
//                    mContext.getResources().getColor(R.color.black));
            holder.setImageResource(R.id.item_iv, lv0.isExpanded() ? R.mipmap.jt3 : R.mipmap.jt1);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    LogToastUtils.log(getClass().getSimpleName(), "convertParent:" + pos);
                    if (lv0.isExpanded()) {
                        collapse(pos);
                    } else {
                         /*要先伸开，否则此位置就不对应了*/
                        expand(pos);
                        int lastExpand = -1;
                        for (int i = getHeaderLayoutCount(); i < getItemCount(); i++) {
                            LevelEntity entity = getItem(i - getHeaderLayoutCount());
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

        private void convertChild(final BaseViewHolder holder, final LevelEntity lv1) {
            holder.setText(R.id.item_name, lv1.getName());
//            boolean parentIsExpanded = lv1.getParent() != null && lv1.getParent().isExpanded();
//            holder.setVisible(R.id.right_side_view, parentIsExpanded ? false : true);
//            holder.setTextColor(R.id.title, parentIsExpanded ?
//                    mContext.getResources().getColor(R.color.blue) :
//                    mContext.getResources().getColor(R.color.black));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    jumpActivity(lv1.getName(), ActivityBackStage.this);
//                    String helpUrl = lv1.getHelp_url();
//                    String helpName = lv1.getHelp_name();
//                    Log.d(TAG, "convertChild->" + pos + " ->" + helpUrl);
                }


            });
        }

    }

    public void jumpActivity(String name, Context context) {
        switch (name) {
            case "商品管理":
                context.startActivity(new Intent(context, ActivityManageGoods.class));
                break;
            case "报价列表":
                context.startActivity(new Intent(context, QuoteQuery.class));
                break;
            case "创建报价":
                context.startActivity(new Intent(context, CreateQuotes.class));
                break;
            case "新建结款单":
                context.startActivity(new Intent(context, CreateSectionBills.class));
                break;
            case "待审批结款单":
                context.startActivity(new Intent(context, CheckWaitBills.class));
                break;
            case "执行中结款单":
                context.startActivity(new Intent(context, ExecuteBills.class));
                break;
            case "登记担保资源":
                context.startActivity(new Intent(context, RegisterAssureBills.class));
                break;
            case "银行账号":
                bundle.putInt(Constant.BUNDLE_TYPE, Constant.BANK_BILLS_ACTIVITY_TYPE);
                intentActivity(BankBillsActivity.class, bundle);
                break;
            case "地址管理":
                bundle.putInt(Constant.BUNDLE_TYPE, Constant.ECPRESS_ADDRESS_ACTIVITY_TYPE);
                intentActivity(BankBillsActivity.class, bundle);
                break;
            case "收货":
                context.startActivity(new Intent(context, HarvestExpress.class));
                break;
            case "发货":
                context.startActivity(new Intent(context, SendExpress.class));
                break;
            case "退货":
                context.startActivity(new Intent(context, QuitExpress.class));
                break;
            case "退货签收":
                context.startActivity(new Intent(context, QuitHarvestExpress.class));
                break;
            case "付款":
                context.startActivity(new Intent(context, PaymentActivity.class));
                break;
            case "收款":
                context.startActivity(new Intent(context, ReceiptActivity.class));
                break;
            case "退款":
                context.startActivity(new Intent(context, RefundActivity.class));
                break;
            case "收退款":
                context.startActivity(new Intent(context, ReceiveRefundMoenyActivity.class));
                break;
            case "操作员列表":
                context.startActivity(new Intent(context, OperatorListActivity.class));
                break;
            case "操作员添加":
                context.startActivity(new Intent(context, OperatorAdd.class));
                break;
            case "修改密码":
                context.startActivity(new Intent(context, OperatorPassWord.class));
                break;
            case "业务合作会员目录":
                context.startActivity(new Intent(context, CooperateDirectory.class));
                break;
            case "关系会员管理":
                context.startActivity(new Intent(context, CooperateContents.class));
                break;
            case "待审批申请":
                context.startActivity(new Intent(context, CooperateRequest.class));
                break;
            case "群组管理":
                context.startActivity(new Intent(context, GroupManager.class));
                break;
            case "已提交合作协议":
                bundle.putInt(Constant.PROTOCOL_TYPE, Constant.protocol_0);
                bundle.putInt(Constant.FRA_TYPE, Constant.fra_1);
                intentActivity(RequestProtocol.class, bundle);
                break;
            case "待审批合作协议":
                bundle.putInt(Constant.PROTOCOL_TYPE, Constant.protocol_1);
                bundle.putInt(Constant.FRA_TYPE, Constant.fra_1);
                intentActivity(RequestProtocol.class, bundle);
                break;
            case "已完成合作协议":
                bundle.putInt(Constant.PROTOCOL_TYPE, Constant.protocol_2);
                bundle.putInt(Constant.FRA_TYPE, Constant.fra_1);
                intentActivity(RequestProtocol.class, bundle);
                break;
            case "采购拣单车":
                bundle.putInt(Constant.ORDER_TYPE, Constant.order_type_buy);
                intentActivity(BuyCart.class, bundle);
                break;
            case "销售拣单车":
                bundle.putInt(Constant.ORDER_TYPE, Constant.order_type_sell);
                intentActivity(BuyCart.class, bundle);
                break;
            case "新建订单列表":
                bundle.putInt(Constant.PROTOCOL_TYPE, Constant.protocol_0);
                bundle.putInt(Constant.FRA_TYPE, Constant.fra_2);
                intentActivity(RequestProtocol.class, bundle);
                break;
            case "待审批订单列表":
                bundle.putInt(Constant.PROTOCOL_TYPE, Constant.protocol_1);
                bundle.putInt(Constant.FRA_TYPE, Constant.fra_2);
                intentActivity(RequestProtocol.class, bundle);
                break;
            case "执行中订单列表":
                bundle.putInt(Constant.PROTOCOL_TYPE, Constant.protocol_2);
                bundle.putInt(Constant.FRA_TYPE, Constant.fra_2);
                intentActivity(RequestProtocol.class, bundle);
                break;

        }
    }

    private class LevelEntity extends AbstractExpandableItem implements MultiItemEntity {
        private String name;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private int id;

        /**
         * TYPE_LEVEL_0 = 1;
         * TYPE_LEVEL_1 = 2;
         *
         * @param type 只能为
         */
        public LevelEntity(int type) {
            this.type = type;
        }

        public LevelEntity(int type, String string) {
            this.type = type;
            this.name = string;
        }

        public LevelEntity(int type, String string, int id) {
            this.type = type;
            this.name = string;
            this.id = id;

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
}
