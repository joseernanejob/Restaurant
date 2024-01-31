package com.restaurant.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public enum Permission {

    ADMIN("admin"),
    USER("user");

    private String role;

    Permission(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
