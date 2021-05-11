package com.example.RetailStore.model;

import javax.persistence.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    private double totalBill;
    @OneToOne
    private BillDetails billDetails;

    public Bill() {
    }

    public Bill(User user, double totalBill, BillDetails billDetails) {
        this.user = user;
        this.totalBill = totalBill;
        this.billDetails = billDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public BillDetails getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(BillDetails billDetails) {
        this.billDetails = billDetails;
    }
}
