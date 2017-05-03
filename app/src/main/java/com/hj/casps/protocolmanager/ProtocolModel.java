package com.hj.casps.protocolmanager;

/**
 * Created by zy on 2017/4/25.
 */

public class ProtocolModel {
    private String name;
    private String object;
    private String transport;
    private String pay_term;
    private String begin;
    private String end;
    private boolean waiting;
    private int state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getPay_term() {
        return pay_term;
    }

    public void setPay_term(String pay_term) {
        this.pay_term = pay_term;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
