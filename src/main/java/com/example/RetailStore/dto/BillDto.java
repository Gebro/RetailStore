package com.example.RetailStore.dto;

import com.example.RetailStore.model.UserType;

public class BillDto {
    private String name;
    private UserType type;
    private String memberSince;
    private double totalBill;
    private double groceries;
    private double notGroceries;

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public String getMemberSince() {
        return memberSince;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public double getGroceries() {
        return groceries;
    }

    public double getNotGroceries() {
        return notGroceries;
    }
}
