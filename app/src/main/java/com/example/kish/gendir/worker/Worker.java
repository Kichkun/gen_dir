package com.example.kish.gendir.worker;

import com.example.kish.gendir.model.Payment;

import java.util.List;

public interface Worker {

    List<Payment> getListPayment();

    void drop(Payment payment);

    void deny(Payment payment);


}
