package com.nathan.model;

/**
 * Created by Nathan on 16/4/6.
 */
public class Auto {
    private String autonum;
    private String autoname;
    private String purchasedate;
    private double price;
    private String tyeOfOil;
    private String status;

    public void setAutoname(String autoname) {
        this.autoname = autoname;
    }

    public void setAutonum(String autonum) {
        this.autonum = autonum;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPurchasedate(String purchasedate) {
        this.purchasedate = purchasedate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTyeOfOil(String tyeOfOil) {
        this.tyeOfOil = tyeOfOil;
    }

    public String getAutonum() {
        return autonum;
    }

    public String getAutoname() {
        return autoname;
    }

    public String getPurchasedate() {
        return purchasedate;
    }

    public double getPrice() {
        return price;
    }

    public String getTyeOfOil() {
        return tyeOfOil;
    }

    public String getStatus() {
        return status;
    }
}
