package com.example.RetailStore.service;

import com.example.RetailStore.model.Bill;
import com.example.RetailStore.model.BillDetails;
import com.example.RetailStore.model.User;
import com.example.RetailStore.repository.BillDetailsRepository;
import com.example.RetailStore.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillDetailsService {

    @Autowired
    BillDetailsRepository billDetailsRepository;

    public BillDetails createBillDetails(double groceries, double notGroceries){
        BillDetails billDetails = new BillDetails();
        billDetails.setGroceries(groceries);
        billDetails.setNotGroceries(notGroceries);
        billDetails = this.billDetailsRepository.save(billDetails);
        return billDetails;

    }

}
