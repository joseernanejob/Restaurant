package com.restaurant.models;

public enum OrderStatus {

    OPEN("open"),
    FINISH("finish");


    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
