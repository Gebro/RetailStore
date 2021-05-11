package com.example.RetailStore.service;

import com.example.RetailStore.dto.BillDto;
import com.example.RetailStore.model.Bill;
import com.example.RetailStore.model.BillDetails;
import com.example.RetailStore.model.User;
import com.example.RetailStore.model.UserType;
import com.example.RetailStore.repository.BillDetailsRepository;
import com.example.RetailStore.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import static java.util.Calendar.*;
import static java.util.Calendar.DATE;

@Service
public class BillService {
    @Autowired
    BillRepository billRepository;
    @Autowired
    UserService userService;
    @Autowired
    BillDetailsService billDetailsService;

    BillDto billDto;

    private final double percentageDiscountEmployee = 0.3;
    private final double percentageDiscountAffiliate = 0.1;
    private final double percentageDiscountCustomer = 0.05;

    public int getDiffYears(String memberSince) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date memberSinceDate = formatter.parse(memberSince);
        Calendar a = getCalendar(memberSinceDate);
        Calendar b = getCalendar(new Date());
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    public double getDiscount(double notGroceries, UserType type, String memberSince) throws ParseException {
        double discount;
        if (type == UserType.Employee) {
            discount = percentageDiscountEmployee * notGroceries;

        } else if (type == UserType.Affiliate) {
            discount = percentageDiscountAffiliate * notGroceries;

        } else {
            if (getDiffYears(memberSince) >= 2) {
                discount = percentageDiscountCustomer * notGroceries;

            } else {
                discount = 0;
            }

        }

        return discount;
    }

    public double getExtraDiscount(double cart_total) {
        int n = (int) (cart_total / 100);
        double total_discount = n * 5;

        return total_discount;
    }

    public double getTotalDiscount(double cart_total, UserType type, String memberSince) throws ParseException {
        return getDiscount(cart_total, type, memberSince) + getExtraDiscount(cart_total);
    }


    public Optional<Bill> findById(Long id){
        return billRepository.findById(id);
    }

    public Bill createBill(String name, UserType type,
                           String memberSince,double totalBill,
                           double groceries,double notGroceries){
        User user = userService.createUser(name,type,memberSince);
        BillDetails billDetails=billDetailsService.createBillDetails(groceries,notGroceries);
        return createBill(user,totalBill,billDetails);

    }
    public Bill createBill(User user,double totalBill,
                           BillDetails billDetails){
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setBillDetails(billDetails);
        bill.setTotalBill(totalBill);
        bill = this.billRepository.save(bill);
        return bill;

    }
    public ResponseEntity<String> handlingRequest(BillDto billDto) throws ParseException {

        createBill(billDto.getName(), billDto.getType(),
                billDto.getMemberSince(), billDto.getTotalBill(),
                billDto.getGroceries(), billDto.getNotGroceries());

        if (billDto.getMemberSince() == null)
            return new ResponseEntity<>("Please add how many years you are a customer!",
                    HttpStatus.BAD_REQUEST);
        else {
            double total_discount = getTotalDiscount(billDto.getNotGroceries()
                    , billDto.getType(), billDto.getMemberSince());
            double total_payment = billDto.getTotalBill() - total_discount;
            return new ResponseEntity<>("You have: " + total_discount +
                    "$ discount, your total payment is: "
                    + total_payment + "$",
                    HttpStatus.OK);
        }


    }
}
