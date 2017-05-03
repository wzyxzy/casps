package com.hj.casps.entity;

import java.io.Serializable;

/**
 * Created by Admin on 2017/4/18.
 */

public class PayMnetInfo implements Serializable {
    private static final long serialVersionUID = 4188980549406589896L;
    //订单号
    private String billsId;
    //类型
    private String billsType;
    //流程
    private String flowId;
    //买方
    private String buyer;
    //签约时间
    private String contractTime;
    //付款时间
    private String paymentTime;
    //送货开始时间
    private String deliveryStarTime;
    //送货结束时间
    private String deliveryEndTime;
    //总金额
    private String totalMoney;
    //支付账号
    private String payId;
    //收款账号
    private String buyId;
    //收获地址
    private String harvestAddress;
    //已付金额
    private String already_money;
    //待付金额
    private String await_money;
    //本次还款
    private String now_money;
    //备注
    private String payment_remark;

    //商品清单
    private CommodityIndo commodityIndo;

    //是否选中
    private boolean isChecked;

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public String toString() {
        return "PayMnetInfo{" +
                "billsId='" + billsId + '\'' +
                ", billsType='" + billsType + '\'' +
                ", flowId='" + flowId + '\'' +
                ", buyer='" + buyer + '\'' +
                ", contractTime='" + contractTime + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", deliveryStarTime='" + deliveryStarTime + '\'' +
                ", deliveryEndTime='" + deliveryEndTime + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", payId='" + payId + '\'' +
                ", buyId='" + buyId + '\'' +
                ", harvestAddress='" + harvestAddress + '\'' +
                ", already_money='" + already_money + '\'' +
                ", await_money='" + await_money + '\'' +
                ", now_money='" + now_money + '\'' +
                ", payment_remark='" + payment_remark + '\'' +
                ", commodityIndo=" + commodityIndo +
                ", isChecked=" + isChecked +
                '}';
    }


    public PayMnetInfo(String billsId, String billsType, String flowId, String buyer, String contractTime, String paymentTime, String deliveryStarTime, String deliveryEndTime, String totalMoney, String payId, String buyId, String harvestAddress, String already_money, String await_money, String now_money, String payment_remark, CommodityIndo commodityIndo) {
        this.billsId = billsId;
        this.billsType = billsType;
        this.flowId = flowId;
        this.buyer = buyer;
        this.contractTime = contractTime;
        this.paymentTime = paymentTime;
        this.deliveryStarTime = deliveryStarTime;
        this.deliveryEndTime = deliveryEndTime;
        this.totalMoney = totalMoney;
        this.payId = payId;
        this.buyId = buyId;
        this.harvestAddress = harvestAddress;
        this.already_money = already_money;
        this.await_money = await_money;
        this.now_money = now_money;
        this.payment_remark = payment_remark;
        this.commodityIndo = commodityIndo;
    }

    public PayMnetInfo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBillsId() {
        return billsId;
    }

    public void setBillsId(String billsId) {
        this.billsId = billsId;
    }

    public String getBillsType() {
        return billsType;
    }

    public void setBillsType(String billsType) {
        this.billsType = billsType;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getContractTime() {
        return contractTime;
    }

    public void setContractTime(String contractTime) {
        this.contractTime = contractTime;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getDeliveryStarTime() {
        return deliveryStarTime;
    }

    public void setDeliveryStarTime(String deliveryStarTime) {
        this.deliveryStarTime = deliveryStarTime;
    }

    public String getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(String deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public String getHarvestAddress() {
        return harvestAddress;
    }

    public void setHarvestAddress(String harvestAddress) {
        this.harvestAddress = harvestAddress;
    }

    public String getAlready_money() {
        return already_money;
    }

    public void setAlready_money(String already_money) {
        this.already_money = already_money;
    }

    public String getAwait_money() {
        return await_money;
    }

    public void setAwait_money(String await_money) {
        this.await_money = await_money;
    }

    public String getNow_money() {
        return now_money;
    }

    public void setNow_money(String now_money) {
        this.now_money = now_money;
    }

    public String getPayment_remark() {
        return payment_remark;
    }

    public void setPayment_remark(String payment_remark) {
        this.payment_remark = payment_remark;
    }

    public CommodityIndo getCommodityIndo() {
        return commodityIndo;
    }

    public void setCommodityIndo(CommodityIndo commodityIndo) {
        this.commodityIndo = commodityIndo;
    }

    public static class CommodityIndo {
        private String goodsName;
        private String goodsWaitCount;
        private String goodsSumMoney;
        private String goodsHarvestCount;
        private String goodsOneMoney;
        private String goodsWaitMoney;
        private String goodsSumCount;
        private String goodsHarvestMoney;

        public CommodityIndo(String goodsName, String goodsWaitCount, String goodsSumMoney, String goodsHarvestCount, String goodsOneMoney, String goodsWaitMoney, String goodsSumCount, String goodsHarvestMoney) {
            this.goodsName = goodsName;
            this.goodsWaitCount = goodsWaitCount;
            this.goodsSumMoney = goodsSumMoney;
            this.goodsHarvestCount = goodsHarvestCount;
            this.goodsOneMoney = goodsOneMoney;
            this.goodsWaitMoney = goodsWaitMoney;
            this.goodsSumCount = goodsSumCount;
            this.goodsHarvestMoney = goodsHarvestMoney;
        }

        public CommodityIndo() {
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsWaitCount() {
            return goodsWaitCount;
        }

        public void setGoodsWaitCount(String goodsWaitCount) {
            this.goodsWaitCount = goodsWaitCount;
        }

        public String getGoodsSumMoney() {
            return goodsSumMoney;
        }

        public void setGoodsSumMoney(String goodsSumMoney) {
            this.goodsSumMoney = goodsSumMoney;
        }

        public String getGoodsHarvestCount() {
            return goodsHarvestCount;
        }

        public void setGoodsHarvestCount(String goodsHarvestCount) {
            this.goodsHarvestCount = goodsHarvestCount;
        }

        public String getGoodsOneMoney() {
            return goodsOneMoney;
        }

        public void setGoodsOneMoney(String goodsOneMoney) {
            this.goodsOneMoney = goodsOneMoney;
        }

        public String getGoodsWaitMoney() {
            return goodsWaitMoney;
        }

        public void setGoodsWaitMoney(String goodsWaitMoney) {
            this.goodsWaitMoney = goodsWaitMoney;
        }

        public String getGoodsSumCount() {
            return goodsSumCount;
        }

        public void setGoodsSumCount(String goodsSumCount) {
            this.goodsSumCount = goodsSumCount;
        }

        public String getGoodsHarvestMoney() {
            return goodsHarvestMoney;
        }

        public void setGoodsHarvestMoney(String goodsHarvestMoney) {
            this.goodsHarvestMoney = goodsHarvestMoney;
        }
    }
}
