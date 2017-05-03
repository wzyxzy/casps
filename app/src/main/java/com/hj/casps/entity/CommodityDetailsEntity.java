package com.hj.casps.entity;

import java.io.Serializable;

/**
 * Created by Admin on 2017/4/27.
 * 商品详情
 */

public class CommodityDetailsEntity implements Serializable {
    private static final long serialVersionUID = 1852034224424751779L;

    private String oredertitleNumber;//	string	订单号
    private String goodsName;//	string	商品名称
    private double awaitmoney;
    private double ordermoney;//	double	商品金额
    private double money;//		double	实际结款金额
    private boolean isCheck;//是否选中

    public CommodityDetailsEntity(String oredertitleNumber, String goodsName, double awaitmoney, double ordermoney, double money, boolean isCheck) {
        this.oredertitleNumber = oredertitleNumber;
        this.goodsName = goodsName;
        this.awaitmoney = awaitmoney;
        this.ordermoney = ordermoney;
        this.money = money;
        this.isCheck = isCheck;
    }

    public CommodityDetailsEntity() {
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

    public double getAwaitmoney() {
        return awaitmoney;
    }

    public void setAwaitmoney(double awaitmoney) {
        this.awaitmoney = awaitmoney;
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

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "CommodityDetailsEntity{" +
                "oredertitleNumber='" + oredertitleNumber + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", awaitmoney=" + awaitmoney +
                ", ordermoney=" + ordermoney +
                ", money=" + money +
                ", isCheck=" + isCheck +
                '}';
    }
}
