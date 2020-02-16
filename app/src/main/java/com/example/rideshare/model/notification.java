package com.example.rideshare.model;

public class notification {
    private  String request_by;

        private String ridepost;

    public notification(String request_by, String ridepost) {
        this.request_by = request_by;
        this.ridepost = ridepost;
    }

    public String getRequest_by() {
        return request_by;
    }

    public void setRequest_by(String request_by) {
        this.request_by = request_by;
    }

    public String getRidepost() {
        return ridepost;
    }

    public void setRidepost(String ridepost) {
        this.ridepost = ridepost;
    }

}
