package com.hj.casps.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2017/4/27.
 */

public class SectionEntity implements Serializable {

    private static final long serialVersionUID = -2836705380734770109L;

    private int return_code;//	int	结果码，0 成功，101 无权限，201 数据库错误
    private String return_message;//	string	结果提示文本
    private String mmbpayName;//		string	买方
    private String settleCode;//	string	借款单号
    private double settleMoney;//	double	结款金额
    private String mmbgetName;//	string		卖方
    private String ctrTime;//	date	约定结款时间
    private double ctrMoney;//	double	约定结款金额
    private int status;//int	确认状态（1没有确认、2付款方确认、3收款方确认、4双方确认、5付款方确认终止、6收款方确认终止、7双方确认终止）
    private int statusSingn;//int	签章状态（1双方未签、2付款方已签、3收款方已签、4双方已签）
    private int statusRegist;//	int	担保登记状态（1没有登记、2申请待定、3已登记、4已拒绝）
    private List<OrderList> mOrderList;

    public SectionEntity(int return_code, String return_message, String mmbpayName, String settleCode, double settleMoney, String mmbgetName, String ctrTime, double ctrMoney, int status, int statusSingn, int statusRegist, List<OrderList> mOrderList) {
        this.return_code = return_code;
        this.return_message = return_message;
        this.mmbpayName = mmbpayName;
        this.settleCode = settleCode;
        this.settleMoney = settleMoney;
        this.mmbgetName = mmbgetName;
        this.ctrTime = ctrTime;
        this.ctrMoney = ctrMoney;
        this.status = status;
        this.statusSingn = statusSingn;
        this.statusRegist = statusRegist;
        this.mOrderList = mOrderList;
    }

    public SectionEntity() {
    }

    public int getReturn_code() {
        return return_code;
    }

    public void setReturn_code(int return_code) {
        this.return_code = return_code;
    }

    public String getReturn_message() {
        return return_message;
    }

    public void setReturn_message(String return_message) {
        this.return_message = return_message;
    }

    public String getMmbpayName() {
        return mmbpayName;
    }

    public void setMmbpayName(String mmbpayName) {
        this.mmbpayName = mmbpayName;
    }

    public String getSettleCode() {
        return settleCode;
    }

    public void setSettleCode(String settleCode) {
        this.settleCode = settleCode;
    }

    public double getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(double settleMoney) {
        this.settleMoney = settleMoney;
    }

    public String getMmbgetName() {
        return mmbgetName;
    }

    public void setMmbgetName(String mmbgetName) {
        this.mmbgetName = mmbgetName;
    }

    public String getCtrTime() {
        return ctrTime;
    }

    public void setCtrTime(String ctrTime) {
        this.ctrTime = ctrTime;
    }

    public double getCtrMoney() {
        return ctrMoney;
    }

    public void setCtrMoney(double ctrMoney) {
        this.ctrMoney = ctrMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatusSingn() {
        return statusSingn;
    }

    public void setStatusSingn(int statusSingn) {
        this.statusSingn = statusSingn;
    }

    public int getStatusRegist() {
        return statusRegist;
    }

    public void setStatusRegist(int statusRegist) {
        this.statusRegist = statusRegist;
    }

    public List<OrderList> getmOrderList() {
        return mOrderList;
    }

    public void setmOrderList(List<OrderList> mOrderList) {
        this.mOrderList = mOrderList;
    }

    public static class OrderList {
        //	返回结款单包含的订单清单列表
        public String oredertitleNumber;//	string	订单号
        public String goodsName;//	string	商品名称
        public double ordermoney;//	double	商品金额
        public double money;//		double	实际结款金额

        public OrderList(String oredertitleNumber, String goodsName, double ordermoney, double money) {
            this.oredertitleNumber = oredertitleNumber;
            this.goodsName = goodsName;
            this.ordermoney = ordermoney;
            this.money = money;
        }

        public OrderList() {
        }

        public String getOredertitleNumber() {
            return oredertitleNumber;
        }

        public void setOredertitleNumber(String oredertitleNumber) {
            this.oredertitleNumber = oredertitleNumber;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public double getOrdermoney() {
            return ordermoney;
        }

        public void setOrdermoney(double ordermoney) {
            this.ordermoney = ordermoney;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }
    }
}
