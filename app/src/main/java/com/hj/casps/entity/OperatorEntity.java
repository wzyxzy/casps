package com.hj.casps.entity;

import java.io.Serializable;

/**
 * Created by Admin on 2017/4/21.
 */

public class OperatorEntity implements Serializable {
    private static final long serialVersionUID = -3760402741218952986L;

    private String accountName;
    private String accountID;

    public OperatorEntity(String accountName, String accountID) {
        this.accountName = accountName;
        this.accountID = accountID;
    }

    public OperatorEntity() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }


}
