package com.hj.casps.ordermanager;

/**
 * Created by zy on 2017/4/27.
 */

public class OrderBuyModel {
    private String type;
    private String name;
    private String contents;
    private int num;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
