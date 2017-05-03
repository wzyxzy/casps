package com.hj.casps.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2017/4/27.
 * 结款单类表
 */

public class NewOpposite implements Serializable {

    private static final long serialVersionUID = 512752227812119530L;

    private String return_code;//	int	结果码，0 成功，101 无权限，201 数据库错误
    private String return_message;//		string	结果提示文本
    private String pagecount;//		int	符合条件记录总页数
    public List<OppositeInfo> mList;

    public NewOpposite(String return_code, String return_message, String pagecount, List<OppositeInfo> mList) {
        this.return_code = return_code;
        this.return_message = return_message;
        this.pagecount = pagecount;
        this.mList = mList;
    }

    public NewOpposite() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_message() {
        return return_message;
    }

    public void setReturn_message(String return_message) {
        this.return_message = return_message;
    }

    public String getPagecount() {
        return pagecount;
    }

    public void setPagecount(String pagecount) {
        this.pagecount = pagecount;
    }

    public List<OppositeInfo> getmList() {
        return mList;
    }

    public void setmList(List<OppositeInfo> mList) {
        this.mList = mList;
    }

    public static class OppositeInfo implements Serializable{
        private static final long serialVersionUID = -1488495878057420695L;
        private String id;//	string	结款单id
        private String settleCode;//	string	结款单号
        private String mmbgetName;//			string		结款对方
        private String mmbpayName;//		string		结款对方（页面上处理：会员id等于mmbpayId结款对方显示mmbgetName，否则显示mmbpayName）
        private Double settleMoney;//		Double		结款订单金额
        private String ctrTime;//		Date	我的提议时间
        private Double ctrMoney;//		Double	我的提议金额

        public OppositeInfo(String id, String settleCode, String mmbgetName, String mmbpayName, Double settleMoney, String ctrTime, Double ctrMoney) {
            this.id = id;
            this.settleCode = settleCode;
            this.mmbgetName = mmbgetName;
            this.mmbpayName = mmbpayName;
            this.settleMoney = settleMoney;
            this.ctrTime = ctrTime;
            this.ctrMoney = ctrMoney;
        }

        public OppositeInfo() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public Double getSettleMoney() {
            return settleMoney;
        }

        public void setSettleMoney(Double settleMoney) {
            this.settleMoney = settleMoney;
        }

        public String getCtrTime() {
            return ctrTime;
        }

        public void setCtrTime(String ctrTime) {
            this.ctrTime = ctrTime;
        }

        public Double getCtrMoney() {
            return ctrMoney;
        }

        public void setCtrMoney(Double ctrMoney) {
            this.ctrMoney = ctrMoney;
        }
    }

}
