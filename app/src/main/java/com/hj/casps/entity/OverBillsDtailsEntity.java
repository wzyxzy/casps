package com.hj.casps.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2017/4/27.
 */

public class OverBillsDtailsEntity implements Serializable {
    private static final long serialVersionUID = 1886022026032428490L;
    private String sellersId;//		string		收款方id(本方付款时采用)
    private String sellersName;//	string	收款方名称(本方付款时采用)
    private String buyersId;//		string	付款方id(对方付款时采用)
    private String buyersName;//	string	付款方名称(对方付款时采用)
    private String ctrTime;//	date		约定结款时间
    private List<CommodityDetailsEntity> mList;

    public OverBillsDtailsEntity(String sellersId, String sellersName, String buyersId, String buyersName, String ctrTime, List<CommodityDetailsEntity> mList) {
        this.sellersId = sellersId;
        this.sellersName = sellersName;
        this.buyersId = buyersId;
        this.buyersName = buyersName;
        this.ctrTime = ctrTime;
        this.mList = mList;
    }

    public OverBillsDtailsEntity() {
    }

    public String getSellersId() {
        return sellersId;
    }

    public void setSellersId(String sellersId) {
        this.sellersId = sellersId;
    }

    public String getSellersName() {
        return sellersName;
    }

    public void setSellersName(String sellersName) {
        this.sellersName = sellersName;
    }

    public String getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(String buyersId) {
        this.buyersId = buyersId;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public String getCtrTime() {
        return ctrTime;
    }

    public void setCtrTime(String ctrTime) {
        this.ctrTime = ctrTime;
    }

    public List<CommodityDetailsEntity> getmList() {
        return mList;
    }

    public void setmList(List<CommodityDetailsEntity> mList) {
        this.mList = mList;
    }
}
