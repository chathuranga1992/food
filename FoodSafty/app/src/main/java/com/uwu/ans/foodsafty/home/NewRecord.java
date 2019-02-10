package com.uwu.ans.foodsafty.home;

public class NewRecord {
    private String owner,private_address,nic,restaurant_name,BR_No,LA_No,LA_Year;

    public NewRecord() {
    }

    public NewRecord(String owner, String private_address, String nic, String restaurant_name, String BR_No, String LA_No, String LA_Year) {
        this.owner = owner;
        this.private_address = private_address;
        this.nic = nic;
        this.restaurant_name = restaurant_name;
        this.BR_No = BR_No;
        this.LA_No = LA_No;
        this.LA_Year = LA_Year;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPrivate_address() {
        return private_address;
    }

    public void setPrivate_address(String private_address) {
        this.private_address = private_address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getBR_No() {
        return BR_No;
    }

    public void setBR_No(String BR_No) {
        this.BR_No = BR_No;
    }

    public String getLA_No() {
        return LA_No;
    }

    public void setLA_No(String LA_No) {
        this.LA_No = LA_No;
    }

    public String getLA_Year() {
        return LA_Year;
    }

    public void setLA_Year(String LA_Year) {
        this.LA_Year = LA_Year;
    }
}
