package com.hj.casps.entity;

import java.io.Serializable;

/**
 * Created by Admin on 2017/4/28.
 */

public class QueryPendingSttle implements Serializable {
    private static final long serialVersionUID = -2431534104027029652L;
    private int id;//	string	结款单id
    private String settleCode;//string  	结款单号
    private String mmbgetName;//	string		结款对方
    private String mmbpayName;//	string		结款对方（会员id等于mmbpayId结款对方显示mmbgetName，否则显示mmbpayName）
    private double settleMoney;//Double		结款订单金额
    private String ctrTime;//	Date		对方提议时间
    private double ctrMoney;//	Double	对方提议金额
    private String myTime;//	Date		我的提议时间
    private double myMoney;//	Double	我的提议金额
    private boolean isCheck;

    public QueryPendingSttle(int id, String settleCode, String mmbgetName, String mmbpayName, double settleMoney, String ctrTime, double ctrMoney, String myTime, double myMoney, boolean isCheck) {
        this.id = id;
        this.settleCode = settleCode;
        this.mmbgetName = mmbgetName;
        this.mmbpayName = mmbpayName;
        this.settleMoney = settleMoney;
        this.ctrTime = ctrTime;
        this.ctrMoney = ctrMoney;
        this.myTime = myTime;
        this.myMoney = myMoney;
        this.isCheck = isCheck;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getMyTime() {
        return myTime;
    }

    public void setMyTime(String myTime) {
        this.myTime = myTime;
    }

    public double getMyMoney() {
        return myMoney;
    }

    public void setMyMoney(double myMoney) {
        this.myMoney = myMoney;
    }

    public QueryPendingSttle() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSettleCode() {
        return settleCode;
    }

    public void setSettleCode(String settleCode) {
        this.settleCode = settleCode;
    }

    public String getMmbgetName() {
        return mmbgetName;
    }

    public void setMmbgetName(String mmbgetName) {
        this.mmbgetName = mmbgetName;
    }

    public String getMmbpayName() {
        return mmbpayName;
    }

    public void setMmbpayName(String mmbpayName) {
        this.mmbpayName = mmbpayName;
    }

    public double getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(double settleMoney) {
        this.settleMoney = settleMoney;
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
}
