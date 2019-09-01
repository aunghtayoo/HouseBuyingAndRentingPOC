package com.padc.homework.housebuyingandrentingpoc.data.vos;

import java.math.BigDecimal;

public class HouseInfoVO {

    /*private String houseImageUrl;
    private String name;
    private String description;
    private int price;
    private String address;
    private int squareFeet;
    private double latitude;
    private double longitude;*/

    private String house_image_url;
    private String name;
    private String description;
    private double price;
    private String address;
    private int square_feet;
    private double latitude;
    private double longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getHouse_image_url() {
        return house_image_url;
    }

    public void setHouse_image_url(String house_image_url) {
        this.house_image_url = house_image_url;
    }

    public int getSquare_feet() {
        return square_feet;
    }

    public void setSquare_feet(int square_feet) {
        this.square_feet = square_feet;
    }
}
