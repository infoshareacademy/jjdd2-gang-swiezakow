package com.infoshareacademy.model;


public class DetailedStatisticsModel {

    private String date = "";
    private Integer visits = 0;
    private Integer feature1Quantity = 0;
    private Integer feature2Quantity = 0;
    private Integer feature3Quantity = 0;
    private Integer feature4Quantity = 0;

    public DetailedStatisticsModel(String date) {
        this.date = date;
    }

    public Integer getVisits() {
        return visits;
    }

    public String getDate() {
        return date;
    }

    public Integer getFeature1Quantity() {
        return feature1Quantity;
    }

    public Integer getFeature2Quantity() {
        return feature2Quantity;
    }

    public Integer getFeature3Quantity() {
        return feature3Quantity;
    }

    public Integer getFeature4Quantity() {
        return feature4Quantity;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public void setFeature1Quantity(Integer feature1Quantity) {
        this.feature1Quantity = feature1Quantity;
    }

    public void setFeature2Quantity(Integer feature2Quantity) {
        this.feature2Quantity = feature2Quantity;
    }

    public void setFeature3Quantity(Integer feature3Quantity) {
        this.feature3Quantity = feature3Quantity;
    }

    public void setFeature4Quantity(Integer feature4Quantity) {
        this.feature4Quantity = feature4Quantity;
    }

    @Override
    public String toString() {
        return date +
                ",  " + visits +
                ",  " + feature1Quantity +
                ",  " + feature2Quantity +
                ",  " + feature3Quantity +
                ",  " + feature4Quantity;
    }
}