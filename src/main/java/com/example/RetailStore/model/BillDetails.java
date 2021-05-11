package com.example.RetailStore.model;

import javax.persistence.*;

@Entity
public class BillDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double groceries;
    private double notGroceries;

    public BillDetails() {
    }

    public BillDetails(double groceries, double notGroceries) {
        this.groceries = groceries;
        this.notGroceries = notGroceries;
    }

    public double getGroceries() {
        return groceries;
    }

    public void setGroceries(double groceries) {
        this.groceries = groceries;
    }

    public double getNotGroceries() {
        return notGroceries;
    }

    public void setNotGroceries(double notGroceries) {
        this.notGroceries = notGroceries;
    }
}
