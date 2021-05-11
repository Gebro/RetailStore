package com.example.RetailStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.*;

@SpringBootApplication
public class RetailStoreApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(RetailStoreApplication.class, args);



    }

}
