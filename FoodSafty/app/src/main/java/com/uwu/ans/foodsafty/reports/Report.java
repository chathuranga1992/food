package com.uwu.ans.foodsafty.reports;

public class Report {
    private String restaurant_name;
    private String private_address;

    public Report()
    {
    }

    public Report(String restaurant_name, String private_address) {
        this.restaurant_name = restaurant_name;
        this.private_address = private_address;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getPrivate_address() {
        return private_address;
    }

    public void setPrivate_address(String private_address) {
        this.private_address = private_address;
    }
}
