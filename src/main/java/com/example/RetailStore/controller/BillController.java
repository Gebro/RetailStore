package com.example.RetailStore.controller;


import com.example.RetailStore.dto.BillDto;
import com.example.RetailStore.model.Bill;
import com.example.RetailStore.model.User;
import com.example.RetailStore.service.BillService;
import com.example.RetailStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("api/bill")
public class BillController {

    @Autowired
    BillService billService;

    @GetMapping("/findById/{id}")
    public Optional<Bill> findById(@PathVariable Long id) {
        return billService.findById(id);
    }

    @PostMapping("/addBill")
    public ResponseEntity<String> addBill(@RequestBody BillDto billDto) throws ParseException {

      return billService.handlingRequest(billDto);


    }
}

