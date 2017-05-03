package com.hj.casps.quotes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zy on 2017/4/20.
 */

public class QuoteModel implements Parcelable{
    private String serial_no;
    private String name;
    private String quote_status;
    private String person;
    private String number;
    private String price;
    private String period;

    protected QuoteModel(Parcel in) {
        serial_no = in.readString();
        name = in.readString();
        quote_status = in.readString();
        person = in.readString();
        number = in.readString();
        price = in.readString();
        period = in.readString();
    }

    public static final Creator<QuoteModel> CREATOR = new Creator<QuoteModel>() {
        @Override
        public QuoteModel createFromParcel(Parcel in) {
            return new QuoteModel(in);
        }

        @Override
        public QuoteModel[] newArray(int size) {
            return new QuoteModel[size];
        }
    };

    public QuoteModel() {

    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuote_status() {
        return quote_status;
    }

    public void setQuote_status(String quote_status) {
        this.quote_status = quote_status;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "QuoteModel{" +
                "serial_no='" + serial_no + '\'' +
                ", name='" + name + '\'' +
                ", quote_status='" + quote_status + '\'' +
                ", person='" + person + '\'' +
                ", number='" + number + '\'' +
                ", price='" + price + '\'' +
                ", period='" + period + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(serial_no);
        parcel.writeString(name);
        parcel.writeString(quote_status);
        parcel.writeString(person);
        parcel.writeString(number);
        parcel.writeString(price);
        parcel.writeString(period);
    }
}
