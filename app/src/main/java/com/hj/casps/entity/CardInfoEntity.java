package com.hj.casps.entity;

import java.io.Serializable;

/**
 * Created by zdd on 2017/4/17.
 * 银行卡类型
 */

public class CardInfoEntity implements Serializable {

    private static final long serialVersionUID = 2301374058198547961L;

    //账户ID
    private String id;
    //账户全称
    private String accountname;
    //账户号码
    private String accountno;
    //银行名称
    private String bankname;
    //联系人
    private String contact;
    //手机号
    private String mobilephone;
    //电话
    private String phone;

    public CardInfoEntity(String accountname, String accountno, String bankname, String contact, String mobilephone, String phone) {

        this.accountname = accountname;
        this.accountno = accountno;
        this.bankname = bankname;
        this.contact = contact;
        this.mobilephone = mobilephone;
        this.phone = phone;
    }

    public CardInfoEntity() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CardInfoEntity{" +
                "id='" + id + '\'' +
                ", accountname='" + accountname + '\'' +
                ", accountno='" + accountno + '\'' +
                ", bankname='" + bankname + '\'' +
                ", contact='" + contact + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
