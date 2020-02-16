package com.example.rideshare.model;

public class ridepost {
    private String _id;
    private String pickuppoint;
    private  String destination;
    private  String journeydate;
    private String time;
    private String paidornot;
    private String cost;
    public users acepted_by;

    public ridepost(  String pickuppoint, String destination, String journeydate, String time, String paidornot, String cost) {

        this.pickuppoint = pickuppoint;
        this.destination = destination;
        this.journeydate = journeydate;
        this.time = time;
        this.paidornot = paidornot;
        this.cost = cost;
    }


    private users getAcepted_by() {
        return acepted_by;
    }

    public void setAcepted_by(users acepted_by) {
        this.acepted_by = acepted_by;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public String getPickuppoint() {
        return pickuppoint;
    }

    public void setPickuppoint(String pickuppoint) {
        this.pickuppoint = pickuppoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getJourneydate() {
        return journeydate;
    }

    public void setJourneydate(String journeydate) {
        this.journeydate = journeydate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPaidornot() {
        return paidornot;
    }

    public void setPaidornot(String paidornot) {
        this.paidornot = paidornot;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
