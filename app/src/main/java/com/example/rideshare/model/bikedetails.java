package com.example.rideshare.model;

public class bikedetails {
    private String model;
    private  String color;
    private  String plateno;

    public bikedetails(String model, String color, String plateno) {
        this.model = model;
        this.color = color;
        this.plateno = plateno;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlateno() {
        return plateno;
    }

    public void setPlateno(String plateno) {
        this.plateno = plateno;
    }
}
