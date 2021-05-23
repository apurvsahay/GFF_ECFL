package com.example.gffecfl.Objects;

import java.io.Serializable;

public class Players implements Serializable {
    String name;
    String country;
    String position;
    String basePrice;
    String sellingPrice;
    String points;

    public Players(String name, String country, String position, String basePrice, String sellingPrice, String points) {
        this.name = name;
        this.country = country;
        this.position = position;
        this.basePrice = basePrice;
        this.sellingPrice = sellingPrice;
        this.points = points;
    }

    public Players(){
        this.name = "";
        this.country = "";
        this.position = "";
        this.basePrice = "";
        this.sellingPrice = "NA";
        this.points = "0";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
