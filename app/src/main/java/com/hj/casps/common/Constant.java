package com.hj.casps.common;

import com.hj.casps.entity.CardInfoEntity;
import com.hj.casps.entity.ExpressInfoEntity;

/**
 * Created by zdd on 2017/4/17.
 */

public class Constant {
    //bundle的key
    public static final String BUNDLE_TYPE = "BUNDLE_TYPE";

    //判断由那个界面跳转搜索
    public static final int PAYMENT_SEARCH_TYPE = 0; //付款界面
    public static final int RECEIPT_SEARCH_TYPE = 1; //收款界面
    public static final int REFUND_SEARCH_TYPE = 2; //退款界面
    public static final int RECEIVE_REFUND_SEARCH_TYPE = 3; //收退款界面
    public static final int BANK_BILLS_ACTIVITY_TYPE = 4; //银行账户管理
    public static final int ECPRESS_ADDRESS_ACTIVITY_TYPE = 5; //收货地址管理
    public static final int ECPRESS_SEND_ACTIVITY_TYPE = 6; //发货
    public static final int ECPRESS_QUIT_ACTIVITY_TYPE = 7; //退货
    public static final int ECPRESS_QUIT_HARVEST_ACTIVITY_TYPE = 8; //退货
    public static final int CREATE_SECTION_BILLS_ACTIVITY_TYPE = 9; //创建结款单
    public static final int ADD_SECTION_BILLS_ACTIVITY_TYPE = 10; //添加订单
    public static final int WAIT_CHECK_BILLS_ACTIVITY_TYPE = 11; //待审批结款单
    public static final int EXECUTE_BILLS_ACTIVITY_TYPE = 12; //执行中结款单
    public static final int REGISTER_BILLS_ACTIVITY_TYPE = 13; //结款单登记担保列表


    //操作银行卡的类型
    public static final String CARD_TYPE = "CARD_TYPE";
    public static final int CARD_EDIT = 20;//银行卡编辑
    public static final int CARD_ADD = 21;//添加银行卡
    public static final int ADDRESS_EDIT = 22;//收获地址编辑
    public static final int ADDRESS_ADD = 23;//添加收获地址
    public static final int ADDCOMMODITY_ADD = 24;//创建结款但

    //订单列表布局
    public static final int type_one = 1;
    public static final int type_two = 2;
    public static final int type_three = 3;

    public static CardInfoEntity cardInfoEntity = null;
    public static ExpressInfoEntity expressInfoEntity = null;
    //合作协议布局
    public static final String PROTOCOL_TYPE = "PROTOCOL_TYPE";
    public static final String FRA_TYPE = "FRA_TYPE";

    public static final int protocol_0 = 0;
    public static final int protocol_1 = 1;
    public static final int protocol_2 = 2;
    public static final int fra_1 = 1;
    public static final int fra_2 = 2;
    //订单管理布局
    public static final String ORDER_TYPE = "ORDER_TYPE";
    public static final int order_type_buy = 0;
    public static final int order_type_sell = 1;


    /**
     * 结款单详情
     * 确认状态（1没有确认、
     * 2付款方确认、
     * 3收款方确认、
     * 4双方确认、
     * 5付款方确认终止、
     * 6收款方确认终止、
     * 7双方确认终止）
     */

    public static final int STATUS_TYPE_0 = 0;
    public static final int STATUS_TYPE_1 = 1;
    public static final int STATUS_TYPE_2 = 2;
    public static final int STATUS_TYPE_3 = 3;
    public static final int STATUS_TYPE_4 = 4;
    public static final int STATUS_TYPE_5 = 5;
    public static final int STATUS_TYPE_6 = 6;
    public static final int STATUS_TYPE_7 = 7;

}
